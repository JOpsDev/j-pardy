package eu.javaland.jpardy;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import eu.javaland.jpardy.core.Category;
import eu.javaland.jpardy.core.Field;

public class CategoryTest {

	@Test
	public void testAllFieldsOpen() {
		Category category = new Category("myName");
		Field field1 = new Field(1,"a","q");
		category.addField(field1);
		Field field2 = new Field(2,"a","q");
		category.addField(field2);
		Field field3 = new Field(3,"a","q");
		category.addField(field3);
		
		assertFalse(category.allFieldsOpen());
		field1.reveal();
		field1.markSolved(null);
		assertFalse(category.allFieldsOpen());
		field2.reveal();
		field2.markSolved(null);
		assertFalse(category.allFieldsOpen());
		field3.reveal();
		field3.markUnsolved();
		
		assertTrue(category.allFieldsOpen());
	}
	
	@Test
	public void testFieldCount() {
		Category category = new Category("myName");
		assertEquals(0, category.getFieldCount());
		Field field1 = new Field(1,"a","q");
		category.addField(field1);
		assertEquals(1, category.getFieldCount());
		Field field2 = new Field(2,"a","q");
		category.addField(field2);
		assertEquals(2, category.getFieldCount());
	}
	
	
	@Test
	public void testFieldsAccess() {
		Category category = new Category("myName");
		Field field1 = new Field(2,"a","q");
		category.addField(field1);
		Field field2 = new Field(1,"a","q");
		category.addField(field2);
		Collection<Field> fields = category.getFields();
		Iterator<Field> iterator = fields.iterator();
		assertEquals(field2, iterator.next());
		assertEquals(field1, iterator.next());
		
		
	}
}
