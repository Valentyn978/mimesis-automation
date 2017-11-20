package com;

import java.util.*;

public class JustChecking {
	public static void main(String[] args) {
		List<String> l1 = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
		Map<Integer, String> m1 = new HashMap<Integer, String>(){{
			put(1, "One");
			put(2, "Two");
			put(3, "Three");
		}};
		Set<String> s1 = new HashSet<>(Arrays.asList("One", "Two", "Three"));

		String list = "List";
		String set = "Map";
		if (contains(m1, l1).equals(s1)){
			System.out.printf(list + " is equals to " + set);
		} else {
			System.out.printf(list + " and " + set + " does not equals");
		}
	}

	public static Collection<String> contains(Map<Integer, String> map, List<String> list) {

		Collection<String> values = map.values();
		Set<String> result = new HashSet<>();
		for (String listItem : list) {
			if (values.contains(listItem)) {
				result.add(listItem);
			}
		}
		return result;
	}
}
