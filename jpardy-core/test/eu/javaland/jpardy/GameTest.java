package eu.javaland.jpardy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import eu.javaland.jpardy.core.Category;
import eu.javaland.jpardy.core.Field;
import eu.javaland.jpardy.core.Game;
import eu.javaland.jpardy.core.Player;

public class GameTest {

	@Test
	public void test() {
		Game game = new Game();
		assertEquals(0, game.getPlayers().size());
		
		Player p1 = new Player();
		game.addPlayer(p1);
		assertFalse(game.isReady());
		
		Player p2 = new Player();
		game.addPlayer(p2);
		assertFalse(game.isReady());

		p1.setNickname("one");
		assertFalse(game.isReady());

		p2.setNickname("two");
		assertTrue(game.isReady());
		
		Player p3 = new Player();
		game.addPlayer(p3);
		assertFalse(game.isReady());
		
		p3.setNickname("three");
		assertTrue(game.isReady());
		
		Category category = new Category("Java-Neuerungen");
		game.addCategroy(category);
		
		Field field = new Field(100,"answer 1","question 1?");
		category.addField(field);

		field = new Field(200,"answer 2","question 2?");
		category.addField(field);
	}
}
