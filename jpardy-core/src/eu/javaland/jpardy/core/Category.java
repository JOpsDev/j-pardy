package eu.javaland.jpardy.core;

import java.util.Set;
import java.util.TreeSet;

public class Category {

	private String displayName;
	private Set<Field> fields = new TreeSet<>();

	public Category(String displayName) {
		this.displayName= displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void addField(Field field) {
		fields.add(field);
	}

}
