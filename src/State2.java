
public class State2 extends State {
	char[][] characters;

	public State2(char[][] characters) {
		this.characters = characters;
	}

	@Override
	public String print() {
		String str = "\n";
		for (int i = 0; i < characters.length; i++) {
			for (int j = 0; j < characters[0].length; j++) {
				str+= characters[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}

	@Override
	public State crossOver(Problem problem_inter, State mother) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State mutation(Problem problem) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasWord(String word, int index, int i, int j) {
		if (index == word.length()) {
			return true;
		}
		if (index == 0) {
			for (int k = i; k < characters.length; k++) {
				for (int l = j; l < characters[0].length; l++) {
					if (characters[k][l] == word.charAt(0)) {
						boolean status = hasWord(word, 1, k, l);
						if (status == true) 
							return true;							
					}
				}
			}
		} else {
			if (j != characters[0].length - 1 && characters[i][j + 1] == word.charAt(index)) { // right
				return hasWord(word, index + 1, i, j + 1);
			}

			if (i != characters.length - 1 && characters[i + 1][j] == word.charAt(index)) { // down
				return hasWord(word, index + 1, i + 1, j);
			}

			if (j != 0 && characters[i][j - 1] == word.charAt(index)) { // left
				return hasWord(word, index + 1, i, j - 1);
			}

			if (i != 0 && characters[i - 1][j] == word.charAt(index)) { // up
				return hasWord(word, index + 1, i - 1, j);
			}

		}
		return false;
	}


	private boolean promissing(String word, int index, int i, int j, int k) {
		switch (k) {
		case 0: // right
			if (j != characters[0].length - 1 && characters[i][j + 1] == word.charAt(index)) {
				return true;
			} else {
				return false;
			}

		case 1: // down
			if (i != characters.length - 1 && characters[i + 1][j] == word.charAt(index)) {
				return true;
			} else {
				return false;
			}

		case 2: // left
			if (j != 0 && characters[i][j - 1] == word.charAt(index)) {
				return true;
			} else {
				return false;
			}

		case 3: // up
			if (j != 0 && characters[i - 1][j] == word.charAt(index)) {
				return true;
			} else {
				return false;
			}

		default:
			break;
		}
		return false;
	}

}
