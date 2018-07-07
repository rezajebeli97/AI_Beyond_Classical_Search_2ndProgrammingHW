
public class Action3 extends Action{
	private int leftIndex , rightIndex;
	
	public Action3(int i, int j) {
		setLeftIndex(i);
		setRightIndex(j);
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLeftIndex() {
		return leftIndex;
	}

	public void setLeftIndex(int leftIndex) {
		this.leftIndex = leftIndex;
	}

	public int getRightIndex() {
		return rightIndex;
	}

	public void setRightIndex(int rightIndex) {
		this.rightIndex = rightIndex;
	}

}
