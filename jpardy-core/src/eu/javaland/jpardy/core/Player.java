package eu.javaland.jpardy.core;

public class Player {
	private String nickname = null;
	private int points = 0;
	private int number;
	
	
	public Player() {
	}


	public Player(String nickname) {
		this.nickname = nickname;
	}

	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getNickname() {
		return nickname == null ? "N.N.": nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}


	public boolean isReady() {
		return nickname != null;
	}


	public void addPoints(Field field) {
		points += field.getPoints();
	}


	public void subtractPoints(Field field) {
		points -= field.getPoints();
	}
	

}
