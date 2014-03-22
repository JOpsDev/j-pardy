package eu.javaland.jpardy;

import static org.junit.Assert.*;

import org.junit.Test;

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
	
}
