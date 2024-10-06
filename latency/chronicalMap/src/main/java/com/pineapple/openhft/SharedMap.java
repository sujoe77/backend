package com.pineapple.openhft;

import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.values.Values;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SharedMap {
    public static final String COUNTRY_DETAILS = /*System.getProperty("user.home") +*/ "data/country-details.dat";
    private static ChronicleMap<LongValue, CharSequence> persistedCountryMap = null;

    public static void main(String[] args) throws InterruptedException, IOException {
        init();
        int i = 0;
        boolean exit = false;
        while (!exit) {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String line = reader.readLine();

            // Printing the read line
            System.out.println(line);
            if ("e".equals(line)) {
                exit = true;
                System.out.println("Exit!!");
            }
        }
        finish();
    }

    private static void init() throws IOException {
        persistedCountryMap = ChronicleMap.of(LongValue.class, CharSequence.class)
                .name("country-map")
                .entries(50)
                .averageValue("America")
                .createPersistedTo(new File(COUNTRY_DETAILS));
        LongValue key = Values.newHeapInstance(LongValue.class);
        key.setValue(1);
        persistedCountryMap.put(key, "Romania");
        key.setValue(2);
        persistedCountryMap.put(key, "India");
        key.setValue(3);
        persistedCountryMap.put(key, "China");
    }

    private static void finish() {
        persistedCountryMap.close();
    }
}
