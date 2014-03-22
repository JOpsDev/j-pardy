package eu.javaland.jpardy;

public class InvalidGameStateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidGameStateException(String message) {
		super(message);
	}

}
