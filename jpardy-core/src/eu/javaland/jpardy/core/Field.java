package eu.javaland.jpardy.core;

import eu.javaland.jpardy.InvalidGameStateException;

public class Field implements Comparable<Field>{
	private int points;
	private String answer;
	private String question;
	private FieldStatus status;
	private Player solvedBy;
	

	public Field(int points, String answer, String question) {
		this.points = points;
		this.answer = answer;
		this.question = question;
		this.status = FieldStatus.HIDDEN;
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
	public Player getSolvedBy() {
		return solvedBy;
	}



	@Override
	public int compareTo(Field f) {
		return points - f.getPoints();
	}


	public FieldStatus getStatus() {
		return status ;
	}
	
	public boolean isHidden() {
		return FieldStatus.HIDDEN.equals(status);
	}


	public void reveal() {
		if (status != FieldStatus.HIDDEN) throw new InvalidInputException("field '"+answer+"' has already been revealed");
		status = FieldStatus.REVEALED;
	}


	public void check() throws InvalidGameStateException {
		if (points == 0) throw new InvalidGameStateException("field '"+answer+"' has no points assigned (=0)");
		if (answer == null || answer.isEmpty()) throw new InvalidGameStateException("field has null or empty string set as an answer");
		if (status == null) throw new InvalidGameStateException("field '"+answer+"' has null as its status");
	}


	public void markSolved(Player solvedBy) {
		if (status != FieldStatus.REVEALED) throw new InvalidInputException("field '"+answer+"' is in wrong state: "+status);
		status = FieldStatus.OPEN_SOLVED;
		this.solvedBy = solvedBy;
		
	}


	public void markUnsolved() {
		if (status != FieldStatus.REVEALED) throw new InvalidInputException("field '"+answer+"' is in wrong state: "+status);
		status = FieldStatus.OPEN_UNSOLVED;
		this.solvedBy = null;
	}
	
}
