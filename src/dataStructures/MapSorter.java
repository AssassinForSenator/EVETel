package dataStructures;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class MapSorter {

	public static <K, V> LinkedList<Map.Entry<K, V>> sortByValue(
			Map<K, V> unsortedMap) {

		LinkedList<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				unsortedMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return ((Comparable<V>) o1.getValue()).compareTo(o2.getValue());
			}
		});
		return list;
	}
}