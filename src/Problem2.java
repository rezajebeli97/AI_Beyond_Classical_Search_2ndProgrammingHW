import java.util.ArrayList;
import java.util.Scanner;

public class Problem2 implements Problem {
	int n, m, numOfWords;
	char[][] characters_input;
	String[] words_input;

	public Problem2() {
		Scanner scr = new Scanner(System.in);
		System.out.println("number of rows:");
		n = scr.nextInt();
		System.out.println("number of columns:");
		m = scr.nextInt();
		characters_input = new char[n][m];

		System.out.println("Table:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				characters_input[i][j] = scr.next().charAt(0);
			}
		}
		System.out.println("number of words:");
		numOfWords = scr.nextInt();
		words_input = new String[numOfWords];
		System.out.println("Words:");
		for (int i = 0; i < numOfWords; i++) {
			words_input[i] = scr.next();
		}

	}

	@Override
	public State initialState() {
		return new State2(characters_input);
	}

	@Override
	public ArrayList<Action> actions(State state) { // swap 2 neighbor cell
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (j != m - 1) {
					actions.add(new Action2(i, j, 0, 1)); // direction = 0 -> right
				}
				if (i != n - 1) {
					actions.add(new Action2(i, j, 1, 1)); // direction = 1 -> down
				}
				if (j != 0) {
					actions.add(new Action2(i, j, 2, 1)); // direction = 2 -> left
				}
				if (i != 0) {
					actions.add(new Action2(i, j, 3, 1)); // direction = 3 -> up
				}
			}
		}
		return actions;
	}

	@Override
	public State result(State state_inter, Action action_inter) {
		State2 state = (State2) state_inter;
		Action2 action = (Action2) action_inter;

		char[][] newCharacters = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newCharacters[i][j] = state.characters[i][j];
			}
		}
		switch (action.direction) {
		case 0: // right
			newCharacters[action.ai][action.aj] = state.characters[action.ai][action.aj + 1];
			newCharacters[action.ai][action.aj + 1] = state.characters[action.ai][action.aj];
			break;
		case 1: // down
			newCharacters[action.ai][action.aj] = state.characters[action.ai + 1][action.aj];
			newCharacters[action.ai + 1][action.aj] = state.characters[action.ai][action.aj];
			break;
		case 2: // left
			newCharacters[action.ai][action.aj] = state.characters[action.ai][action.aj - 1];
			newCharacters[action.ai][action.aj - 1] = state.characters[action.ai][action.aj];
			break;
		case 3: // up
			newCharacters[action.ai][action.aj] = state.characters[action.ai - 1][action.aj];
			newCharacters[action.ai - 1][action.aj] = state.characters[action.ai][action.aj];
			break;

		default:
			break;
		}

		return new State2(newCharacters);
	}

	@Override
	public double f(State state_inter) {
		State2 state = (State2) state_inter;
		int sum = 0;
		for (int i = 0; i < numOfWords; i++) {
			boolean finished = false;
			String myWord = words_input[i];
			int index = myWord.length() - 1;
			while (!finished && index > 0) {
				String subWord = myWord.substring(0, index + 1);
				boolean b = state.hasWord(subWord, 0, 0, 0);
				if (b) {
					if (index == myWord.length() - 1) {
						sum += 2 + myWord.length() - 1;
					} 
					else{
						sum +=subWord.length() - 1;
					}
					finished = true;
				}
				index--;
			}
		}

		return maximumPossibleF() - sum;
	}

	@Override
	public boolean equal(State s1, State s2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double fValueForFinalState() {
		return 0;
	}

	@Override
	public ArrayList<State> initialStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double maximumPossibleF() {
		double maxF = numOfWords * 2;
		for (int i = 0; i < words_input.length; i++) {
			maxF += words_input[i].length() - 1;
		}
		return maxF;
	}

	@Override
	public double borderF() {
		// TODO Auto-generated method stub
		return 0;
	}

}
