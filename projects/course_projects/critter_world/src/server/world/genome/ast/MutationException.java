package server.world.genome.ast;

public class MutationException extends RuntimeException {

	private static final long serialVersionUID = 7492586816591051942L;

	protected String msg;

	public MutationException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return "Mutation error: " + msg;
	}
}
