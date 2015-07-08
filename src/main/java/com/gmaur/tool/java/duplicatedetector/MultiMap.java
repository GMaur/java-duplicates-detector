/**
 * $Id$
 *
 * @author alvgarcia
 * @date 08/07/2015 16:22
 * Copyright (C) 2015 Scytl Secure Electronic Voting SA
 * All rights reserved.
 */

package com.gmaur.tool.java.duplicatedetector;

import java.util.*;

public class MultiMap<K, V> {

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
