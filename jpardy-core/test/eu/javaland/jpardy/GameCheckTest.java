package eu.javaland.jpardy;

import org.junit.Test;

import eu.javaland.jpardy.core.Game;

public class GameCheckTest {

	@Test(expected=InvalidGameStateException.class)
	public void testBogus1() throws InvalidGameStateException {
		Game.fromFile("test-games/bogus1.jp");
	}

	
	@Test
	public void testOk() throws InvalidGameStateException {
		Game game = Game.fromFile("test-games/test.jp");
		game.check(); // I know, should already have been checked before
	}

}
