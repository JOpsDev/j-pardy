package eu.javaland.jpardy;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.javaland.jpardy.core.Field;
import eu.javaland.jpardy.core.Player;

public class PlayerTest {

	@Test
	public void create() {
		new Player();
	}
	
	@Test
	public void emptyTest() {
		Player p = new Player();
		assertEquals("N.N.", p.getNickname());
	}

	@Test
	public void nameTest() {
		Player p = new Player();
		p.setNickname("Peter Pan");
		assertEquals("Peter Pan", p.getNickname());
	}
	
	@Test
	public void constructorTest() {
		Player p = new Player("Zoink");
		assertEquals("Zoink", p.getNickname());
	}
	
	@Test
	public void readyTest() {
		Player p = new Player();
		assertFalse(p.isReady());
		p.setNickname("x");
		assertTrue(p.isReady());
	}
	
	@Test
	public void testAdd() throws Exception {
		Field field = new Field(123, "A", "q");
		Player p = new Player();
		assertEquals(0, p.getPoints());
		p.addPoints(field);
		assertEquals(123, p.getPoints());
	}
	
	@Test
	public void testSub() throws Exception {
		Field field = new Field(100, "A", "q");
		Field field2 = new Field(70, "A", "q");
		Player p = new Player();
		assertEquals(0, p.getPoints());
		p.addPoints(field);
		assertEquals(100, p.getPoints());
		p.subtractPoints(field2);
		assertEquals(30, p.getPoints());
		p.subtractPoints(field2);
		assertEquals(-40, p.getPoints());
	}
	
}
