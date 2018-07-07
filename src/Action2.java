
public class Action2 extends Action {
	int ai, aj, direction;

	public Action2(int ai, int aj, int direction, int cost) {
		this.ai = ai;
		this.aj = aj;
		this.direction = direction;
		this.cost = cost;
	}

	@Override
	public String print() {
		String str = "";
		if (direction == 0) {
			str = "( " + ai + " , " + aj + " , right" + " )";

		} else if (direction == 1) {
			str = "( " + ai + " , " + aj + " , down" + " )";
		} else if (direction == 2) {
			str = "( " + ai + " , " + aj + " , left" + " )";
		} else if (direction == 3) {
			str = "( " + ai + " , " + aj + " , up" + " )";
		}
		return str;
	}

}
