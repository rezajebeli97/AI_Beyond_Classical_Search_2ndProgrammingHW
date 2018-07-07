import java.util.ArrayList;

public interface Problem {

	public State initialState();

	public ArrayList<Action> actions(State state);
	
	public State result(State state , Action action);
	
	public double f(State state);

	public boolean equal(State s1, State s2);

	public double fValueForFinalState();
	
	public ArrayList<State> initialStates();			//just for genetic algorithm initialization

	public double maximumPossibleF();

	public double borderF();
}