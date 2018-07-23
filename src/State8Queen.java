
public class State8Queen extends State{
	int[] x = new int[9];
	
	public State8Queen(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8) {
		this.x[1] = x1;
		this.x[2] = x2;
		this.x[3] = x3;
		this.x[4] = x4;
		this.x[5] = x5;
		this.x[6] = x6;
		this.x[7] = x7;
		this.x[8] = x8;
	}

	@Override
	public String print() {
		String str = "( [1]:" + x[1] + " [2]:" + x[2] + " [3]:" + x[3] + " [4]:" + x[4] + " [5]:" + x[5] + " [6]:" + x[6] + " [7]:" + x[7] + " [8]:" + x[8] + " )";
		return str;
	}

	@Override
	public State crossOver(Problem problem_inter, State mother_inter) {
		Problem8Queen problem = (Problem8Queen)problem_inter;
		State8Queen mother = (State8Queen)mother_inter;
		
		State8Queen child = new State8Queen(this.x[1], this.x[2], this.x[3], this.x[4], mother.x[5], mother.x[6], mother.x[7], mother.x[8]);
		return child;
	}

	@Override
	public State mutation(Problem problem) {
		State8Queen mutatedChild = new State8Queen(x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8]);
		int rand1 = (int) (Math.random() * 8) + 1;
		int rand2 = (int) (Math.random() * 8) + 1;
		mutatedChild.x[rand1] = rand2;
		return mutatedChild;
	}

}
