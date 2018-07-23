
public class Action8Queen extends Action{

	int queenNumber;
	int columnNumber;
	
	public Action8Queen(int queenNumber , int columnNumber) {
		this.queenNumber = queenNumber;
		this.columnNumber = columnNumber;
		this.cost = 1;
	}
	@Override
	public String print() {
		String str = "( q[" + queenNumber +"] -> " + columnNumber + " )";
		return str;
	}

}
