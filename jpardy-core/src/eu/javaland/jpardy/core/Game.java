package eu.javaland.jpardy.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Game {

	private List<Player> players = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();


	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void addCategroy(Category category) {
		categories.add(category);
	}

	public boolean isReady() {
		boolean ready = players.size() >= 2;
		for (Player p : players) {
			ready = ready && p.isReady();
		}
		return ready;
	}
	
	public static Game fromFile(String filename) {
		XStream xStream = getXstream();
		File file = new File(filename);
		if (!file.canRead()) throw new RuntimeException("cannot read from file: "+filename);
		Game game = (Game) xStream.fromXML(file);
		return game;
	}

	public static XStream getXstream() {
		XStream xStream = new XStream(new StaxDriver());
		xStream.alias("game",Game.class);
		xStream.alias("player",Player.class);
		xStream.alias("category",Category.class);
		xStream.alias("field",Field.class);
//		xStream.alias("game",Game.class);
		xStream.addImplicitCollection(Category.class, "fields");
		return xStream;
	}

}