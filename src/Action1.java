
public class Action1 extends Action{
	private int node;
	private int color;
	
	public Action1(int node, int color , int cost) {
		this.setNode(node);
		this.setColor(color);
		this.cost = cost;
	}

	@Override
	public String print() {
		return "( node:" + getNode() + " , color:" + getColor() + " )";
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
