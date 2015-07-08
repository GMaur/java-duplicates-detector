package com.gmaur.tool.java.duplicatedetector;

import java.util.List;


import java.lang.reflect.Field;

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
		Object object = new Object();
		for (Field field : fields) {
			Object value = field.get(object);
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

}
