import java.util.ArrayList;

public class Problem8Queen implements Problem{

	@Override
	public State initialState() {
		return new State8Queen(1,1,1,1,1,1,1,1);
	}

	@Override
	public ArrayList<Action> actions(State state_inter) {
		State8Queen  state = (State8Queen) state_inter;
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				Action action = new Action8Queen(i, j);
				actions.add(action);
			}
		}
		return actions;
	}

	@Override
	public State result(State state_inter, Action action_inter) {
		State8Queen state = (State8Queen)state_inter;
		Action8Queen action = (Action8Queen)action_inter;
		State8Queen nxtState = new State8Queen(state.x[1], state.x[2], state.x[3], state.x[4], state.x[5], state.x[6], state.x[7], state.x[8]);
		nxtState.x[action.queenNumber] = action.columnNumber;
		return nxtState;
	}

	@Override
	public double f(State state_inter) {
		State8Queen state = (State8Queen)state_inter;
		
		int conflicts = 0;
		for (int i = 1; i <= 8 ; i++) {
			for (int j = 1; j <= 8; j++) {
				if (j!=i && (state.x[j] == state.x[i] || (Math.abs(state.x[j] - state.x[i]) == Math.abs(j - i)) ) ) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		State8Queen s1 = (State8Queen)s1_inter;
		State8Queen s2 = (State8Queen)s2_inter;
		for (int i = 1; i <= 8; i++) {
			if (s1.x[i] != s2.x[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double fValueForFinalState() {
		return 0;
	}

	@Override
	public ArrayList<State> initialStates() {
		ArrayList<State> initialStates = new ArrayList<State>();
		int numberOfPopulation = 20;
		for (int i = 1; i <= numberOfPopulation; i++) {
			int[] rand = new int[9];
			for (int j = 1; j <= 8; j++) {
				rand[j] = (int) (Math.random() * 8) + 1;
			}
			State8Queen state = new State8Queen(rand[1] , rand[2] , rand[3] , rand[4] , rand[5] , rand[6]  , rand[7] , rand[8]);
			initialStates.add(state);
		}
		return initialStates;
	}

	@Override
	public double maximumPossibleF() {
		return 56;
	}

	@Override
	public double borderF() {
		return 15;
	}

}
