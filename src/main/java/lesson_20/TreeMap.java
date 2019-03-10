package lesson_20;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class TreeMap<T> extends LinkedHashMap<T, TreeMap<T>> {

    public void put(T[] path) {
        LinkedList<T> list = new LinkedList<>();
        for (T key : path) {
            list.add(key);
        }
        put(list);
    }

    private void put(LinkedList<T> path) {
        if (path.isEmpty()) {
            return;
        }
        T key = path.removeFirst();
        TreeMap<T> val = get(key);
        if (val == null) {
            val = new TreeMap<>();
            put(key, val);
        }
        val.put(path);
    }

    public void print() {
        print("", this);
    }

    private void print(String prefix, TreeMap<T> map) {
        Set item = map.keySet();
        for (Object o : item) {
            String key = (String) o;
            TreeMap<T> tTreeMap = map.get(key);
            if (tTreeMap.size() == 0) {
                System.out.println(prefix +  "└── " + key);
                continue;
            }
            System.out.println(prefix +  "├── " + key);

            if (tTreeMap.size() > 0) {
                print(prefix +  "│   " , tTreeMap);
            }
        }
    }
}