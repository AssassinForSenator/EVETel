package killStats;

import dataStructures.ItemIdList;

public class ExosMain {

	public static void main(String[] args) {
		ItemIdList.getInstance();
		System.out.println(ItemIdList.lookup(50));
		System.out.println(ItemIdList.lookup(50));

	}
}
