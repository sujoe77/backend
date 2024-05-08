package com.pineapple.openhft;

import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.values.Values;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IPC_Test {
    static ChronicleMap<LongValue, CharSequence> persistedCountryMap = null;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @BeforeAll
    public static void init() {
        try {
            persistedCountryMap = ChronicleMap.of(LongValue.class, CharSequence.class)
                    .name("country-map")
                    .entries(50)
                    .averageValue("America")
                    .createPersistedTo(new File(System.getProperty("user.home") + "/country-details.dat"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void putValues() {
        List<String> countries = List.of(
                "Denmark", "Finland", "Norway"
        );
        long i = 4;
        LongValue key = Values.newHeapInstance(LongValue.class);
        for (String country : countries) {
            key.setValue(i++);
            persistedCountryMap.put(key, country);
        }
    }

    @Test
    public void givenGetUsingQuery_whenCalled_shouldReturnResult() {
        LongValue key = Values.newHeapInstance(LongValue.class);
        StringBuilder country = new StringBuilder();
        List<String> countries = List.of(
                "Romania", "India", "China", "Denmark", "Finland", "Norway"
        );
        long value = 0;
        for (String countryExpected : countries) {
            key.setValue(++value);
            persistedCountryMap.getUsing(key, country);
            assertEquals(countryExpected, country.toString());
        }
    }

    private static void printLatency(LongValue key) {
        int i = 0;
        key.setValue(1);
        final int length = 100;
        long[] latency = new long[length];
        while (i < length) {
            final long start = System.nanoTime();
            persistedCountryMap.get(key);
            latency[i++] = System.nanoTime() - start;
        }
        for (long duration : latency) {
            System.out.println(duration);
        }
    }
}
