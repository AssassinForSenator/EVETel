package dataStructures;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class MapHelpers {

	public static <K, V> LinkedList<Map.Entry<K, V>> sortByValueLinkedListAsc(
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

	public static <K, V> LinkedList<Map.Entry<K, V>> sortbyValueLinkedListDsc(
			Map<K, V> unsortedMap) {
		LinkedList<Map.Entry<K, V>> list = sortByValueLinkedListAsc(unsortedMap);
		Collections.reverse(list);
		printList(list);
		return list;

	}

	public static <K, V> void printList(LinkedList<Map.Entry<K, V>> list) {
		for (Entry<K, V> E : list) {
			System.out
					.println("Key: " + E.getKey() + " Value: " + E.getValue());
		}
	}
}