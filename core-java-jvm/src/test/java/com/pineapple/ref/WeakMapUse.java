package com.pineapple.ref;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakMapUse {
    public static void main(String[] args) throws InterruptedException {
        WeakMapUse wu = new WeakMapUse();
        wu.testMap();
    }

    public void testMap() throws InterruptedException {
        WeakHashMap<MyObject, String> map = new WeakHashMap<>();
        MyObject aKey = new MyObject();
        map.put(aKey, "a");
        putMap(map);
        showMap(map);
        System.gc();
        Thread.sleep(1000);
        showMap(map);
    }

    private void putMap(Map<MyObject, String> map) {
        MyObject bKey = new MyObject();
        map.put(bKey, "b");
    }

    private static <K, V> void showMap(Map<K, V> map) {
        System.out.println("map size is: " + map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("entry is: " + entry);
        }
    }
}
