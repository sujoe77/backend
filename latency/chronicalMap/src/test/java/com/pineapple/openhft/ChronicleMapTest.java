package com.pineapple.openhft;

import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ExternalMapQueryContext;
import net.openhft.chronicle.map.MapEntry;
import net.openhft.chronicle.values.Values;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronicleMapTest {

    static ChronicleMap<LongValue, CharSequence> persistedCountryMap = null;

    static ChronicleMap<LongValue, CharSequence> inMemoryCountryMap = null;

    static ChronicleMap<Integer, Set<Integer>> multiMap = null;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @BeforeAll
    public static void init() {
        try {
            inMemoryCountryMap = ChronicleMap.of(LongValue.class, CharSequence.class)
                    .name("country-map")
                    .entries(50)
                    .averageValue("America")
                    .create();

            persistedCountryMap = ChronicleMap.of(LongValue.class, CharSequence.class)
                    .name("country-map")
                    .entries(50)
                    .averageValue("America")
                    .createPersistedTo(new File(System.getProperty("user.home") + "/country-details.dat"));

            Set<Integer> averageValue = IntStream.of(1, 2)
                    .boxed()
                    .collect(Collectors.toSet());
            multiMap = ChronicleMap.of(Integer.class, (Class<Set<Integer>>) (Class) Set.class)
                    .name("multi-map")
                    .entries(50)
                    .averageValue(averageValue)
                    .create();

            LongValue qatarKey = Values.newHeapInstance(LongValue.class);
            qatarKey.setValue(1);
            inMemoryCountryMap.put(qatarKey, "Qatar");

            LongValue key = Values.newHeapInstance(LongValue.class);
            key.setValue(1);
            persistedCountryMap.put(key, "Romania");
            key.setValue(2);
            persistedCountryMap.put(key, "India");

            Set<Integer> set1 = new HashSet<>();
            set1.add(1);
            set1.add(2);
            multiMap.put(1, set1);

            Set<Integer> set2 = new HashSet<>();
            set2.add(3);
            multiMap.put(2, set2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenGetQuery_whenCalled_shouldReturnResult() {
        LongValue key = Values.newHeapInstance(LongValue.class);
        key.setValue(1);
        CharSequence country = inMemoryCountryMap.get(key);
        assertEquals(country.toString(), "Qatar");
    }

    @Test
    public void givenGetUsingQuery_whenCalled_shouldReturnResult() {
        LongValue key = Values.newHeapInstance(LongValue.class);
        StringBuilder country = new StringBuilder();
        key.setValue(1);
        persistedCountryMap.getUsing(key, country);
        assertEquals(country.toString(), "Romania");
        key.setValue(2);
        persistedCountryMap.getUsing(key, country);
        assertEquals(country.toString(), "India");
    }

    @Test
    public void givenMultipleKeyQuery_whenProcessed_shouldChangeTheValue() {
        try (ExternalMapQueryContext<Integer, Set<Integer>, ?> fistContext = multiMap.queryContext(1)) {
            try (ExternalMapQueryContext<Integer, Set<Integer>, ?> secondContext = multiMap.queryContext(2)) {
                fistContext.updateLock()
                        .lock();
                secondContext.updateLock()
                        .lock();
                MapEntry<Integer, Set<Integer>> firstEntry = fistContext.entry();
                Set<Integer> firstSet = firstEntry.value()
                        .get();
                firstSet.remove(2);
                MapEntry<Integer, Set<Integer>> secondEntry = secondContext.entry();
                Set<Integer> secondSet = secondEntry.value()
                        .get();
                secondSet.add(4);
                firstEntry.doReplaceValue(fistContext.wrapValueAsData(firstSet));
                secondEntry.doReplaceValue(secondContext.wrapValueAsData(secondSet));
            }
        } finally {
            assertEquals(multiMap.get(1).size(), 1);
            assertEquals(multiMap.get(2).size(), 2);
        }
    }

    @AfterAll
    public static void finish() {
        persistedCountryMap.close();
        inMemoryCountryMap.close();
        multiMap.close();
    }
}