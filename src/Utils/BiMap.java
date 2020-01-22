package Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BiMap<K,V> implements Map<K,V> {

    public BiMap() {
        map = new HashMap<>();
        inversedMap = new HashMap<>();
    }

    public BiMap(Map<K, V> map) {
        this.map = new HashMap<>(map);
        inversedMap = new HashMap<>();
        map.forEach((K, V) -> inversedMap.put(V, K));
    }

    private HashMap<K,V> map;
    private HashMap<V,K> inversedMap;

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return map.containsValue(o);
    }

    @Override
    public V get(Object o) {
        return map.get(o);
    }

    @Override
    public V put(K k, V v) {
        inversedMap.put(v, k);
        return map.put(k, v);
    }

    @Override
    public V remove(Object o) {
        V v = map.remove(o);
        inversedMap.remove(v);
        return v;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        map.forEach(this::put);
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    public K getKey(V v) {
        return inversedMap.get(v);
    }

}
