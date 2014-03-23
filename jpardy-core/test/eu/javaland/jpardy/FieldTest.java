package eu.javaland.jpardy;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.javaland.jpardy.core.Field;
import eu.javaland.jpardy.core.FieldStatus;
import eu.javaland.jpardy.core.Player;

public class FieldTest {

	@Test
	public void testSolvedBy() {
		Field field = new Field(100,"A","Q");
		assertNull(field.getSolvedBy());
		Player p1 = new Player();
		field.reveal();
		field.markSolved(p1);
		assertEquals(p1, field.getSolvedBy());
		assertEquals(FieldStatus.OPEN_SOLVED, field.getStatus());
	}

	@Test
	public void testUnsolved() {
		Field field = new Field(100,"A","Q");
		assertNull(field.getSolvedBy());
		field.reveal();
		field.markUnsolved();
		assertNull(field.getSolvedBy());
		assertEquals(FieldStatus.OPEN_UNSOLVED, field.getStatus());
	}

}
