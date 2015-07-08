package com.gmaur.tool.java.duplicatedetector;

import java.util.List;


import java.util.List;


import java.lang.reflect.Field;
import java.util.*;

public class RepeatedFinder {

	private final Class aClass;

	public RepeatedFinder(Class aClass) {
		this.aClass = aClass;
	}

	public MultiMap findDuplicates() throws IllegalAccessException {
		return generateMapFromValueToVariables(aClass).valuesWithMoreThanOneVariable();
	}

	private ValueToNameRepository generateMapFromValueToVariables(Class<?> clazz) throws IllegalAccessException {
		ValueToNameRepository valueToNames = new ValueToNameRepository();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Object value = field.get(new Object());
			valueToNames.add(value, field.getName());
		}
		return valueToNames;
	}

	private class ValueToNameRepository {
		private MultiMap<Object, String> values = new MultiMap();

		public MultiMap valuesWithMoreThanOneVariable() {
			MultiMap<Object, String> result = new MultiMap<>();
			for(Object current : values.all()) {
				List variablesWithSameValue = values.getValuesFor(current);
				if (variablesWithSameValue.size() > 1) {
					result.put(current, variablesWithSameValue);
				}
			}
			return result;
		}

		public void add(Object value, String variable) {
			values.add(value, variable);
		}
	}

	public static class MultiMap<K, V> {

		private final Map<K, List<V>> values = new HashMap<>();

		public Iterable<K> all() {
			return values.keySet();
		}

		public List<V> getValuesFor(K current) {
			return values.get(current);
		}

		public void put(K value, List<V> variables) {
			values.put(value, variables);
		}

		public List<V> get(K value) {
			return Collections.unmodifiableList(values.get(value));
		}

		public int size() {
			return values.entrySet().size();
		}

		public void add(K value, V variable) {
			values.putIfAbsent(value, new ArrayList<>());
			getValuesFor(value).add(variable);
		}
	}
}
