package eu.javaland.jpardy;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import eu.javaland.jpardy.core.Category;
import eu.javaland.jpardy.core.Field;
import eu.javaland.jpardy.core.FieldStatus;
import eu.javaland.jpardy.core.Game;
import eu.javaland.jpardy.core.Player;

public class HomerunTest {

	@Test
	public void test() throws Exception {
		Game game = Game.fromFile("test-games/test.jp");
		Player p1 = new Player("P1");
		game.addPlayer(p1);
		Player p2 = new Player("P2");
		game.addPlayer(p2);
		Player p3 = new Player("P3");
		game.addPlayer(p3);
		assertTrue(game.isReady());
		

		// P1 solves cat.3 for 200
		Field field = game.showField(3,200);
		assertNotNull(field);
		assertEquals(200, field.getPoints());
		assertEquals(FieldStatus.REVEALED, field.getStatus());
		
		game.solveField(field,p1);
		assertEquals(200, p1.getPoints());
		assertEquals(FieldStatus.OPEN, field.getStatus());
		
		// P2 solves cat.2 for 300
		field = game.showField(2, 300);
		assertEquals(0, p2.getPoints());
		assertEquals(300, field.getPoints());
		game.solveField(field, p2);
		assertEquals(300, p2.getPoints());
		
		// P2 misses cat.2 for 400, P3 solves it
		field = game.showField(2, 400);
		assertEquals(0, p3.getPoints());
		game.missedField(field, p2);
		assertEquals(-100, p2.getPoints());
		game.solveField(field, p3);
		assertEquals(400, p3.getPoints());
		
		// solve all the rest
		int p1Pts = p1.getPoints();
		while (game.isRunning()) {
			List<Category> categories = game.getCategories();
			for (Category category : categories) {
				Set<Field> fields = category.getFields();
				for (Field actField : fields) {
					if (actField.isHidden()) {
						actField.reveal();
						game.solveField(actField, p1);
						p1Pts += actField.getPoints();
					}
				}
			}
		}
		assertEquals(p1Pts, p1.getPoints());
		
		
	}

}
