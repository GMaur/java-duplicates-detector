package com.gmaur.tool.java.duplicatedetector;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class RepeatedFinderShould {

	@Test
	public void find_duplicates_in_string_and_integer_constants() throws IllegalAccessException {
		RepeatedFinder sut = new RepeatedFinder(RepeatedConstants.class);
		MultiMap<Object, String> duplicates = sut.findDuplicates();

		for(Object current : duplicates.all()){
			System.out.println("found value = '" + current+"', with variables = " + duplicates.get(current));
		}

		assertThat(duplicates.size(), is(2));
		assertThat(duplicates.get("A"), hasItems("A", "A_1", "A_2"));
		assertThat(duplicates.get(3), hasItems("_3", "THREE"));
	}

	@Test
	public void not_find_duplicates() throws IllegalAccessException {
		RepeatedFinder sut = new RepeatedFinder(NoConstants.class);
		MultiMap<Object, String> duplicates = sut.findDuplicates();

		assertThat(duplicates.size(), is(0));
	}

}