package eu.javaland.jpardy.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import eu.javaland.jpardy.InvalidGameStateException;

public class Game {

	private List<Player> players = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();

	private Integer numberOfRows = null;
	

	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
		player.setNumber(players.size());
	}
	
	public void addCategroy(Category category) {
		categories.add(category);
	}

	public List<Category> getCategories() {
		return categories;
	}

	public boolean isReady() {
		boolean ready = players.size() >= 2;
		for (Player p : players) {
			ready = ready && p.isReady();
		}
		return ready;
	}
	
	public static Game fromFile(String filename) throws InvalidGameStateException {
		XStream xStream = getXstream();
		File file = new File(filename);
		if (!file.canRead()) throw new RuntimeException("cannot read from file: "+filename);
		Game game = (Game) xStream.fromXML(file);
		
		game.check();
		
		return game;
	}

	public void check() throws InvalidGameStateException {
		if (categories == null) throw new InvalidGameStateException("the list for categories is null");
		if (categories.isEmpty()) throw new InvalidGameStateException("no categories defined (list is empty)");
		for (Category category : categories) {
			category.check();
		}
		getRowCount();
	}

	public static XStream getXstream() {
		XStream xStream = new XStream(new StaxDriver());
		xStream.alias("game",Game.class);
		xStream.alias("player",Player.class);
		xStream.alias("category",Category.class);
		xStream.alias("field",Field.class);
		xStream.addImplicitCollection(Category.class, "fields");
		return xStream;
	}

	public Field showField(int categoryIndex, int fieldPoints) {
		if (categoryIndex< 1 || categoryIndex > categories.size()) throw new InvalidInputException("the category number has to be beetween 1 and "+categories.size()+" but was "+categoryIndex);
		Category category = categories.get(categoryIndex-1);
		Field field = category.getField(fieldPoints);
		field.reveal();
		return field;
		
	}

	public void solveField(Field field, Player player) {
		field.markSolved(player);
		player.addPoints(field);
		
	}

	public void missedField(Field field, Player player) {
		player.subtractPoints(field);
		
	}

	public boolean isRunning() {
		for (Category category : categories) {
			if (!category.allFieldsOpen()) {
				return true;
			}
		}
		return false;
	}

	public int getCategoryCount() {
		return categories.size();
	}
	
	public int getRowCount()  {
		if (numberOfRows != null) return numberOfRows;
		if (categories.isEmpty()) throw new IllegalStateException("there are no categories added from which I could count the number of rows/fields");
		int rowcount = categories.get(0).getFieldCount();
		for (Category category : categories) {
			if (category.getFieldCount() != rowcount) throw new IllegalStateException("all categories must have the the same number of rows/fields");
		}
		numberOfRows = rowcount;
		return rowcount;
	}
	
	public Field getField(int categoryNo, int rowNo) {
		return categories.get(categoryNo-1).getFields().get(rowNo-1);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("---------------\n");
		s.append("Players ("+players.size()+"):\n");
		for (Player player : players) {
			s.append(player.getNumber()+" "+player.getNickname()+": "+player.getPoints()+"\n");
		}
		s.append("\nGame state: ready "+isReady()+"  running "+isRunning()+"\n");
		int catNo = getCategoryCount();
		int rowNo = getRowCount();
		for (int x = 0; x < catNo; x++) {
			String catName = categories.get(x).getDisplayName();
			s.append("["+catName + "] ");
		}
		s.append("\n\n");
		for (int y = 1; y <= rowNo; y++) {
			for (int x = 1; x <= catNo; x++) {
				Field field = getField(x, y);
				s.append("["+field.getPoints()+"|"+field.getAnswer()+"] ");
				
			}
			s.append("\n");
			for (int x = 1; x <= catNo; x++) {
				Field field = getField(x, y);
				String statustext;
				FieldStatus status = field.getStatus();
				if (FieldStatus.OPEN_SOLVED.equals(status)) {
					Player solvedBy = field.getSolvedBy();
					statustext = solvedBy == null ? "solvedBy is null" : solvedBy.getNickname();
				} else {
					statustext = status == null ? "status is null" : status.toString();
				}
				s.append("["+statustext+"] ");
			}
			s.append("\n\n");		}
		return s.toString();
	}

	public void markUnsolved(Field field) {
		field.markUnsolved();
	}

}


