package com.dutproject.coffee360admin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {
	public static int pareToInt(String s, int defaultValue) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static List<String> split(String sTags, String delimiter) {
		String[] pieces = sTags.split(delimiter);
		return new ArrayList<String>(Arrays.asList(pieces));
	}

	public static int[] convertListIntegerToInt(List<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			array[i] = list.get(i);
		}
		return array;
	}
}
