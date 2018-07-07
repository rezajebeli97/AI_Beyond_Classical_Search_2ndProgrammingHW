import java.util.ArrayList;

public class Problem3 implements Problem {

	@Override
	public State initialState() {
		char[] left = new char[13];
		char[] right = new char[13];
		for (int i = 0; i < 13; i++) {
			left[i] = (char) (i + 97);
		}
		for (int i = 13; i < 26; i++) {
			right[i-13] = (char) (i + 97);
		}
		return new State3(left, right);
	}

	@Override
	public ArrayList<Action> actions(State state) {
		ArrayList<Action> actions = new ArrayList<Action>();

		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				actions.add(new Action3(i, j));
			}
		}
		return actions;
	}

	@Override
	public State result(State state_inter, Action action_inter) {
		State3 state = (State3) state_inter;
		Action3 action = (Action3) action_inter;

		char[] left = new char[13];
		char[] right = new char[13];
		for (int i = 0; i < 13; i++) {
			left[i] = state.getLeftChars()[i];
		}
		for (int i = 0; i < 13; i++) {
			right[i] = state.getRightChars()[i];
		}

		int lIndex = action.getLeftIndex();
		int rIndex = action.getRightIndex();
		char c = left[lIndex];
		left[lIndex] = right[rIndex];
		right[rIndex] = c;

		return new State3(left, right);
	}

	@Override
	public double f(State state_inter) {
		State3 state = (State3) state_inter;
		char[] left = state.getLeftChars();
		char[] right = state.getRightChars();
		int E = 20, T = 19, A = 18, I = 17, N = 16, O = 15, S = 14, H = 13, R = 12;
		double leftWeight = 0, rightWeight = 0;
		for (int i = 0; i < 13; i++) {
			switch (left[i]) {
			case 'e':
				leftWeight += E;
				break;
			case 't':
				leftWeight += T;
				break;
			case 'a':
				leftWeight += A;
				break;
			case 'i':
				leftWeight += I;
				break;
			case 'n':
				leftWeight += N;
				break;
			case 'o':
				leftWeight += O;
				break;
			case 's':
				leftWeight += S;
				break;
			case 'h':
				leftWeight += H;
				break;
			case 'r':
				leftWeight += R;
				break;

			default:
				break;
			}
		}

		for (int i = 0; i < 13; i++) {
			switch (right[i]) {
			case 'e':
				rightWeight += E;
				break;
			case 't':
				rightWeight += T;
				break;
			case 'a':
				rightWeight += A;
				break;
			case 'i':
				rightWeight += I;
				break;
			case 'n':
				rightWeight += N;
				break;
			case 'o':
				rightWeight += O;
				break;
			case 's':
				rightWeight += S;
				break;
			case 'h':
				rightWeight += H;
				break;
			case 'r':
				rightWeight += R;
				break;

			default:
				break;
			}
		}

		int cost = 0;
		if (has(left, 't', 'h')) {
			cost += 10;
		}
		if (has(left, 'e', 'r')) {
			cost += 10;
		}
		if (has(left, 'o', 'n')) {
			cost += 10;
		}
		if (has(left, 'a', 'n')) {
			cost += 10;
		}
		if (has(left, 'r', 'e')) {
			cost += 10;
		}
		if (has(left, 'h', 'e')) {
			cost += 10;
		}
		if (has(left, 'i', 'n')) {
			cost += 10;
		}
		if (has(left, 'e', 'd')) {
			cost += 10;
		}
		return cost + (Math.abs(leftWeight - rightWeight));
	}

	private boolean has(char[] left, char c, char d) {
		boolean hasC = false;
		for (int i = 0; i < left.length; i++) {
			if (left[i] == c) {
				hasC = true;
				break;
			}
		}
		boolean hasD = false;
		for (int i = 0; i < left.length; i++) {
			if (left[i] == d) {
				hasD = true;
				break;
			}
		}
		if (hasC && hasD) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		State3 s1 = (State3)s1_inter;
		State3 s2 = (State3)s2_inter;
		for (int i = 0; i < 26; i++) {
			char c = (char) (i + 97);
			char whereInFather = leftOrRight( s1 , c );
			char whereInMother = leftOrRight( s2 , c);
			if (whereInFather != whereInMother) {
				return false;
			}
		}
		return true;
	}
	
	private char leftOrRight(State3 state, char c) {
		for (int i = 0; i < state.getLeftChars().length; i++) {
			if (state.getLeftChars()[i] == c) {
				return 'l';
			}
			if (state.getRightChars()[i] == c) {
				return 'r';
			}
		}
		return 0;
	}

	@Override
	public double fValueForFinalState() {
		return 0;
	}

	@Override
	public ArrayList<State> initialStates() {
		int n = 26;
		ArrayList<State> initialStates = new ArrayList<State>();
		for (int j = 0; j < n; j++) {
			char[] whole = new char[26];
			char[] left = new char[13];
			char[] right = new char[13];
			for (int i = 0; i < 26; i++) {
				whole[i] = (char) ((i+j)%26 + 97);
			}
			for (int i = 0; i < 13; i++) {
				left[i] = whole[i];
			}
			for (int i = 13; i < 26; i++) {
				right[i-13] = whole[i];
			}
			initialStates.add(new State3(left, right));
		}
		return initialStates;
	}

	@Override
	public double maximumPossibleF() {
		return 224;
	}

	@Override
	public double borderF() {
		return 25;
	}

}
