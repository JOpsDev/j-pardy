package eu.javaland.jpardy;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.javaland.jpardy.core.Game;
import eu.javaland.jpardy.core.Player;

public class HomerunTest {

	@Test
	public void test() {
		Game game = Game.fromFile("test-games/test.jp");
		game.addPlayer(new Player("P1"));
		game.addPlayer(new Player("P2"));
		game.addPlayer(new Player("P3"));
		assertTrue(game.isReady());
		
	}

}
