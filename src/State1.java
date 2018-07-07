
public class State1 extends State {

	private int[] colors;

	public State1(int[] colors) {
		this.colors = colors;
	}

	public int[] getColors() {
		return colors;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public void setColors(int i, int value) {
		colors[i] = value;
	}

	@Override
	public String print() {
		String s = "( ";
		for (int color : getColors()) {
			s += color + " ";
		}
		s += ")";
		return s;
	}

	@Override
	public State crossOver(Problem problem_inter , State mother_inter) {
		Problem1 problem = (Problem1) problem_inter;
		State1 mother = (State1) mother_inter;
		int n = colors.length;
		int[] resultColors = new int[n];
		
		int which = (int) (Math.random() * 2);
		
		if (which == 0) {
			for (int i = 0; i < n ; i++) {
				resultColors[i] = colors[i];
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (problem.getGraph()[i][j] && getColors()[i] == getColors()[j])
						resultColors[i] = mother.colors[i];
				}
			}
		}
		else {
			for (int i = 0; i < n ; i++) {
				resultColors[i] = mother.colors[i];
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (problem.getGraph()[i][j] && mother.getColors()[i] == mother.getColors()[j])
						resultColors[i] = colors[i];
				}
			}
		}
		return new State1(resultColors);
	}

	@Override
	public State mutation(Problem problem_inter) {
		Problem1 problem = (Problem1) problem_inter;
		int index = (int) (Math.random() * problem.getN());
		int color = (int) (Math.random() * problem.getMaxColor());
		colors[index] = color;
		return this;
	}

}
