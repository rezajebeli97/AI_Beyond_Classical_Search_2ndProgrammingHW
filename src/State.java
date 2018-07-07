public abstract class State {
	public abstract String print();
	
	public abstract State crossOver(Problem problem_inter , State mother);

	public abstract State mutation(Problem problem);

}