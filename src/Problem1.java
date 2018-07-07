
//مسیله ی رنگ آمیزی گراف

import java.util.ArrayList;
import java.util.Scanner;

public class Problem1 implements Problem {

	private boolean[][] graph;
	private int n;
	private int maxColor;

	public Problem1() {
		Scanner scr = new Scanner(System.in);
		System.out.println("Number of maximum colors? : ");
		setMaxColor(scr.nextInt());
		System.out.println("Number of nodes (n) : ");
		setN(scr.nextInt());
		System.out.println("insert your graph as a matrix : ");
		setGraph(new boolean[getN()][getN()]);
		for (int i = 0; i < getN(); i++) {
			for (int j = 0; j < getN(); j++) {
				Byte b = scr.nextByte();
				if (b == 0) {
					getGraph()[i][j] = false;
				} else {
					getGraph()[i][j] = true;
				}
			}
		}

	}

	@Override
	public State initialState() {
		int[] colors = new int[getN()];
		for (int i = 0; i < getN(); i++) {
			int color = (int) (Math.random() * getMaxColor());
			colors[i] = color;
		}
		return new State1(colors);
	}
	
	@Override
	public ArrayList<State> initialStates() {
		ArrayList<State> initialStates = new ArrayList<State>();
		for (int i = 0; i < getN(); i++) {
			initialStates.add(initialState());
		}
		return initialStates;
	}
	

	@Override
	public ArrayList<Action> actions(State state_inter) {
		State1 state = (State1) state_inter;
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = 0; i < getN(); i++) { // i omin node ra ba range j rang konim
			for (int j = 0; j < getMaxColor(); j++) {
				if (state.getColors()[i] != j)
					actions.add(new Action1(i, j, 1));
			}
		}
		return actions;
	}

	@Override
	public State result(State state_inter, Action action_inter) {
		State1 state = (State1) state_inter;
		Action1 action = (Action1) action_inter;
		int[] colors = new int[getN()];
		for (int i = 0; i < getN(); i++) {
			colors[i] = state.getColors()[i];
		}
		colors[action.getNode()] = action.getColor();
		return new State1(colors);
	}

	@Override
	public double f(State state_inter) {
		State1 state = (State1) state_inter;
		int numberOfConflicts = 0;
		for (int i = 0; i < getN(); i++) {
			for (int j = i + 1; j < getN(); j++) {
				if (getGraph()[i][j] && state.getColors()[i] == state.getColors()[j])
					numberOfConflicts++;
			}
		}
		return numberOfConflicts;
	}

	@Override
	public boolean equal(State s1_inter, State s2_inter) {
		State1 s1 = (State1) s1_inter;
		State1 s2 = (State1) s2_inter;
		for (int i = 0; i < getN(); i++) {
			if (s1.getColors()[i] != s2.getColors()[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double fValueForFinalState() {
		return 0;
	}

	public int getMaxColor() {
		return maxColor;
	}

	public void setMaxColor(int maxColor) {
		this.maxColor = maxColor;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean[][] getGraph() {
		return graph;
	}

	public void setGraph(boolean[][] graph) {
		this.graph = graph;
	}

	@Override
	public double maximumPossibleF() {
		return UsefulMethods.factorial(n) / (UsefulMethods.factorial(n-2) * 2);
	}

	@Override
	public double borderF() {
		return n/4;
	}

	

}
