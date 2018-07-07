public class State3 extends State{
	
	private char[] leftChars = new char[13];
	private char[] rightChars = new char[13];
	
	
	public State3(char[] left, char[] right) {
		setLeftChars(left);
		setRightChars(right);
	}

	@Override
	public String print() {
		String s = "( ";
		for (int i = 0; i < 13; i++) {
			s += leftChars[i] + " ";
		}
		s+= " , ";
		for (int i = 0; i < 13; i++) {
			s += rightChars[i] + " ";
		}
		s+=" )";
		return s;
	}

	@Override
	public State crossOver(Problem problem_inter, State mother_inter) {
		State3 mother = (State3) mother_inter;
		State3 child = new State3(new char[13], new char[13]);
		int lIndex=0 , rIndex=0;
		boolean turn = false;
		for (int i = 0; i < 26; i++) {
			char c = (char) (i + 97);
			char whereInFather = leftOrRight( this , c );
			char whereInMother = leftOrRight( mother , c);
			if ( whereInFather == 'l' &&  whereInMother == 'l') {
				child.leftChars[lIndex] = c;
				lIndex ++;
			}
			else if ( whereInFather == 'r' &&  whereInMother == 'r') {
				child.rightChars[rIndex] = c;
				rIndex++;
			}
			else {
				if (turn) {
					child.rightChars[rIndex] = c;
					rIndex++;
					turn = false;
				}
				else {
					child.leftChars[lIndex] = c;
					lIndex ++;
					turn = true;
				}
			}
		}
		return child;
	}

	private char leftOrRight(State3 state, char c) {
		for (int i = 0; i < state.leftChars.length; i++) {
			if (state.leftChars[i] == c) {
				return 'l';
			}
			if (state.rightChars[i] == c) {
				return 'r';
			}
		}
		return 0;
	}

	@Override
	public State mutation(Problem problem) {
		int index1 = (int) (Math.random() * 13);
		int index2 = (int) (Math.random() * 13);
		char c = this.leftChars[index1];
		this.leftChars[index1] = this.rightChars[index2];
		this.rightChars[index2] = c;
		return this;
	}

	public char[] getLeftChars() {
		return leftChars;
	}

	public void setLeftChars(char[] leftChars) {
		this.leftChars = leftChars;
	}
	public void setLeftChars(int index , char c) {
		leftChars[index] = c;
	}
	
	public char[] getRightChars() {
		return rightChars;
	}

	public void setRightChars(char[] rightChars) {
		this.rightChars = rightChars;
	}
	public void setRightChars(int index , char c) {
		rightChars[index] = c;
	}
	
}
