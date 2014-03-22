package eu.javaland.jpardy.core;

public class Field implements Comparable<Field>{
	private int points;
	private String answer;
	private String question;
	
	public Field(int points, String answer, String question) {
		this.points = points;
		this.answer = answer;
		this.question = question;
	}
	
	
	public int getPoints() {
		return points;
	}
	public String getAnswer() {
		return answer;
	}
	public String getQuestion() {
		return question;
	}


	@Override
	public int compareTo(Field f) {
		return points - f.getPoints();
	}
	
}
