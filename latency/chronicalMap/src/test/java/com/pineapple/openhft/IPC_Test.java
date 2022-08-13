package com.pineapple.openhft;

import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.values.Values;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

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
    public void givenGetUsingQuery_whenCalled_shouldReturnResult() {
        LongValue key = Values.newHeapInstance(LongValue.class);
        StringBuilder country = new StringBuilder();
        int i = 0;
        key.setValue(1);
        final int length = 100;
        long[] latency = new long[length];
        while (i < length) {
            final long start = System.nanoTime();
            persistedCountryMap.get(key);
            latency[i++] = System.nanoTime() - start;
        }
        for(long duration : latency){
            System.out.println(duration);
        }
        assertEquals(country.toString(), "Romania");
        key.setValue(2);
        persistedCountryMap.getUsing(key, country);
        assertEquals(country.toString(), "India");
        key.setValue(3);
        System.out.println(country.toString());
    }
}
