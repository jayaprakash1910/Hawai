package org.framework.utilities;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class ArrayUtilsHelper {

	public static ArrayList<String> convertMapToArrayList(Map<String, String> map) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			SortedSet<String> keys = new TreeSet<String>(map.keySet());
			for (String key : keys) {
				String value = map.get(key);
				arrayList.add(key + "::" + value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
}
