package eu.javaland.jpardy.core;

import java.util.Set;
import java.util.TreeSet;

import eu.javaland.jpardy.InvalidGameStateException;

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

	public Field getField(int fieldPoints) throws InvalidInputException {
		for (Field field : fields) {
			if (field.getPoints() == fieldPoints) {
				return field;
			}
		}
		throw new InvalidInputException("a field with "+fieldPoints+" points could not be found in category '"+displayName+"'");
	}

	public void check() throws InvalidGameStateException {
		if (displayName == null) throw new InvalidGameStateException("category has no displayable name");
		if (fields == null) throw new InvalidGameStateException("category '"+displayName+"' has fields null");
		if (fields.isEmpty()) throw new InvalidGameStateException("category '"+displayName+"' has an empty list of fields");
		
		for (Field field : fields) {
			field.check();
		}
	}

}
