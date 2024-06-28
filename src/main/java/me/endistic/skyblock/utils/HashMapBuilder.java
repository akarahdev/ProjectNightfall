package me.endistic.skyblock.utils;

import java.util.HashMap;

public class HashMapBuilder<K, V> {
    public HashMap<K, V> internal;

    public HashMapBuilder<K, V> put(K key, V value) {
        internal.put(key, value);
        return this;
    }

    public HashMap<K, V> get() {
        return this.internal;
    }
}
