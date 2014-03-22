package eu.javaland.jpardy;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.javaland.jpardy.core.Field;
import eu.javaland.jpardy.core.FieldStatus;
import eu.javaland.jpardy.core.Game;
import eu.javaland.jpardy.core.Player;

public class HomerunTest {

	@Test
	public void test() throws Exception {
		Game game = Game.fromFile("test-games/test.jp");
		game.addPlayer(new Player("P1"));
		game.addPlayer(new Player("P2"));
		game.addPlayer(new Player("P3"));
		assertTrue(game.isReady());
		
		
		Field field = game.showField(3,200);
		assertNotNull(field);
		assertEquals(200, field.getPoints());
		
		assertEquals(FieldStatus.REVEALED, field.getStatus());
		
	}

}
