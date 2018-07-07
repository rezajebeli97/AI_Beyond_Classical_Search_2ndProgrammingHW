
//This Search Algorithms are assuming that each better state has lower f

import java.util.ArrayList;

public class Search {

	public void simulatedAnealing(Problem problem) {
		final long maxTime = 500;
		long t = 0, t0 = System.currentTimeMillis();

		State p = problem.initialState();
		ArrayList<State> myPath = new ArrayList<State>();
		ArrayList<Action> myActions = new ArrayList<Action>();
		myPath.add(p);
		int observedNodes = 1;
		int extendedNodes = 0;
		double T = 10;
		while (true) {
			// t = System.currentTimeMillis() - t0;
			t++;
			String str = "Linear";
			switch (str) {
			case "logarithmic":
				T = (10 + 0.0) / Math.log(t + 10);
				break;
			case "Linear":
				T = T - 0.005;
				break;
			case "exponentional":
				T = T * (1 - 0.005);
				break;
			case "me":
				T = Math.pow(((double) (maxTime - t) / maxTime) * 1.5, 10);
				break;

			default:
				break;
			}

//			if (Math.exp(-1 / T) < 0.5) {
//				T /= 5;
//			}
			if (T <= 0) {
				break;
			}

			ArrayList<Action> actions = problem.actions(p);
			State bestNeighbour = p;
			Action bestAction = null;
			double pCost = problem.f(p);
			if (pCost == problem.fValueForFinalState())
				break;
			///
			ArrayList<Boolean> observeNode = new ArrayList<Boolean>();
			for (int i = 0; i < actions.size(); i++) {
				observeNode.add(false);
			}
			while (observeNode.contains(false) && bestAction == null) {
				int index = (int) (Math.random() * actions.size());
				while (observeNode.get(index)) {
					index = (index + 1) % actions.size();
				}
				State neighbour = problem.result(p, actions.get(index));
				double neighbourWorth = problem.f(neighbour);
				observedNodes++;
				observeNode.set(index, true);
				double deltaE = neighbourWorth - pCost;
				if (deltaE < 0) {
					pCost = neighbourWorth;
					bestNeighbour = neighbour;
					bestAction = actions.get(index);
				} else {
					if (deltaE == 0) {
						deltaE = 0.5;
					}
					if (p(Math.exp(-deltaE / T))) {
						pCost = neighbourWorth;
						bestNeighbour = neighbour;
						bestAction = actions.get(index);
					}
				}
			}
			if (bestNeighbour == p) {
				break;
			}
			// System.out.println("Action : " + bestAction.print() + " -> " +
			// problem.f(bestNeighbour)
			// + " , propability : " + Math.exp(-1 / T) + " , T = " + T);
			p = bestNeighbour;
			extendedNodes++;
			myPath.add(bestNeighbour);
			myActions.add(bestAction);

		}

		printDetailsSimulatedAnealing(problem, observedNodes, extendedNodes, myPath, myActions);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private boolean p(double propability) {
		if (propability >= 1) {
			return true;
		}
		if (propability <= 0) {
			return false;
		} else {
			RandomCollection<Boolean> rc = new RandomCollection<Boolean>().add(1 - propability, false).add(propability,
					true);
			return rc.next();
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void hillClimbing(Problem problem) {
		State p = problem.initialState();
		ArrayList<State> myPath = new ArrayList<State>();
		ArrayList<Action> myActions = new ArrayList<Action>();
		myPath.add(p);
		int observedNodes = 1;
		int extendedNodes = 0;
		while (true) {
			ArrayList<Action> actions = problem.actions(p);
			State bestNeighbour = p;
			Action bestAction = null;
			double minCost = problem.f(p);
			for (int i = 0; i < actions.size(); i++) { // find best neighbour which has minimum f value
				State neighbour = problem.result(p, actions.get(i));
				double neighbourWorth = problem.f(neighbour);
				observedNodes++;
				if (neighbourWorth < minCost) {
					minCost = neighbourWorth;
					bestNeighbour = neighbour;
					bestAction = actions.get(i);
				}
			}
			if (bestNeighbour == p) {
				break;
			}
			p = bestNeighbour;
			extendedNodes++;
			myPath.add(bestNeighbour);
			myActions.add(bestAction);
		}

		printDetailsHillClimbing(problem, observedNodes, extendedNodes, myPath, myActions);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void hillClimbing_random(Problem problem) {
		State p = problem.initialState();
		ArrayList<State> myPath = new ArrayList<State>();
		ArrayList<Action> myActions = new ArrayList<Action>();
		myPath.add(p);
		int observedNodes = 1;
		int extendedNodes = 0;
		while (true) {
			ArrayList<Action> actions = problem.actions(p);
			State bestNeighbour = p;
			Action bestAction = null;
			double pCost = problem.f(p);
			///
			ArrayList<Boolean> observeNode = new ArrayList<Boolean>();
			for (int i = 0; i < actions.size(); i++) {
				observeNode.add(false);
			}
			while (observeNode.contains(false) && bestAction == null) {
				int index = (int) (Math.random() * actions.size());
				while (observeNode.get(index)) {
					index = (index + 1) % actions.size();
				}
				State neighbour = problem.result(p, actions.get(index));
				double neighbourWorth = problem.f(neighbour);
				observedNodes++;
				observeNode.set(index, true);
				if (neighbourWorth < pCost) {
					pCost = neighbourWorth;
					bestNeighbour = neighbour;
					bestAction = actions.get(index);
				}
			}
			///
			if (bestNeighbour == p) {
				break;
			}
			p = bestNeighbour;
			extendedNodes++;
			myPath.add(bestNeighbour);
			myActions.add(bestAction);
		}

		printDetailsHillClimbing(problem, observedNodes, extendedNodes, myPath, myActions);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void hillClimbing_firstChoice(Problem problem) {
		State p = problem.initialState();
		ArrayList<State> myPath = new ArrayList<State>();
		ArrayList<Action> myActions = new ArrayList<Action>();
		myPath.add(p);
		int observedNodes = 1;
		int extendedNodes = 0;
		while (true) {
			ArrayList<Action> actions = problem.actions(p);
			State bestNeighbour = p;
			Action bestAction = null;
			double pCost = problem.f(p);
			for (int i = 0; i < actions.size(); i++) { // find best neighbour which has minimum f value
				State neighbour = problem.result(p, actions.get(i));
				double neighbourWorth = problem.f(neighbour);
				observedNodes++;
				if (neighbourWorth < pCost) {
					pCost = neighbourWorth;
					bestNeighbour = neighbour;
					bestAction = actions.get(i);
					break;
				}
			}
			if (bestNeighbour == p) {
				break;
			}
			p = bestNeighbour;
			extendedNodes++;
			myPath.add(bestNeighbour);
			myActions.add(bestAction);
		}

		printDetailsHillClimbing(problem, observedNodes, extendedNodes, myPath, myActions);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void hillClimbing_randomRestart(Problem problem) {
		State initialState = problem.initialState();
		ArrayList<State> finalStates = new ArrayList<State>();

		while (true) {
			State finalState = hillClimbing_withConstantIntialState(problem, initialState, finalStates);
			finalStates.add(finalState);
		}

	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private State hillClimbing_withConstantIntialState(Problem problem, State p, ArrayList<State> finalStates) {
		ArrayList<State> myPath = new ArrayList<State>();
		ArrayList<Action> myActions = new ArrayList<Action>();
		myPath.add(p);
		int observedNodes = 1;
		int extendedNodes = 0;
		while (true) {
			ArrayList<Action> actions = problem.actions(p);
			State bestNeighbour = p;
			Action bestAction = null;
			double minCost = problem.f(p);
			for (int i = 0; i < actions.size(); i++) { // find best neighbour which has minimum f value
				State neighbour = problem.result(p, actions.get(i));
				double neighbourWorth = problem.f(neighbour);
				observedNodes++;
				if (neighbourWorth < minCost) {
					minCost = neighbourWorth;
					bestNeighbour = neighbour;
					bestAction = actions.get(i);
				}
			}
			if (bestNeighbour == p) {
				break;
			}
			p = bestNeighbour;
			extendedNodes++;
			myPath.add(bestNeighbour);
			myActions.add(bestAction);
		}

		for (State state : finalStates) {
			if (problem.equal(state, p)) {
				printDetailsHillClimbing(problem, observedNodes, extendedNodes, myPath, myActions);
				System.exit(0);
			}
		}

		return p;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void geneticSearch(Problem problem) {
		ArrayList<State> population = problem.initialStates();
		int n = population.size(); // n = my population size
		int m = population.size() / 2; // number of children in every reproduction (the value is optional)
		double mutationPropability = 0.5;
		double maxTime = 300;

		ArrayList<ArrayList<State>> myPath = new ArrayList<ArrayList<State>>();
		ArrayList<double[]> bestWorstAverage = new ArrayList<double[]>();
		myPath.add(population);
		bestWorstAverage.add(bestWorstAverage(problem, population));
		State bestChild = population.get(0);

		double t0 = System.currentTimeMillis();
		double t = 0;
		while (t < maxTime) { // فقط یه چیزو چک کن که با تغییر پی مقادیر قبلی پی که ریخته بودم تو اری لیست مای
								// پف عوض میشه یا نه

			ArrayList<State> children = makesChildren(problem, population, m, mutationPropability); // each 2 states are
																									// near in index ,
																									// are couple

			population = selectTheBests(problem, population, children);
			myPath.add(population);
			bestWorstAverage.add(bestWorstAverage(problem, population));
			for (State p : population) {
				if (problem.f(p) < problem.f(bestChild)) {
					bestChild = p;
				}
			}
			if (problem.f(bestChild) == problem.fValueForFinalState()) {
				printDetails(problem, myPath, bestWorstAverage, bestChild);
				return;
			}

			t = System.currentTimeMillis() - t0;

		}

		printDetails(problem, myPath, bestWorstAverage, bestChild);
	}

	//////////////////////////////////////////////////////////////////////

	private double[] bestWorstAverage(Problem problem, ArrayList<State> population) {
		double bestF = 10000000;
		double worstF = -10000000;
		double averageF = 0;
		for (State p : population) {
			bestF = Math.min(bestF, problem.f(p));
			worstF = Math.max(worstF, problem.f(p));
			averageF += problem.f(p);
		}
		averageF /= population.size();
		double[] bestWorstAverage = new double[3];
		bestWorstAverage[0] = bestF;
		bestWorstAverage[1] = worstF;
		bestWorstAverage[2] = averageF;
		return bestWorstAverage;
	}

	//////////////////////////////////////////////////////////////////////

	private ArrayList<State> selectTheBests(Problem problem, ArrayList<State> population, ArrayList<State> children) {
		int n = population.size();

		ArrayList<State> survivors = new ArrayList<State>();

		ArrayList<Integer> propabilityArray = new ArrayList<Integer>();
		for (int i = 0; i < population.size(); i++) {
			double value = problem.maximumPossibleF() - problem.f(population.get(i));
			if (value < problem.maximumPossibleF() / 2) {
				value /= 2;
			} else {
				value *= 2;
			}
			for (int j = 0; j < value; j++) {
				propabilityArray.add(i);
			}
		}
		for (int i = 0; i < children.size(); i++) {
			double value = problem.maximumPossibleF() - problem.f(children.get(i));
			if (value < problem.maximumPossibleF() / 2) {
				value /= 2;
			} else {
				value *= 2;
			}
			for (int j = 0; j < value; j++) {
				propabilityArray.add(i + population.size());
			}
		}

		while (survivors.size() != population.size()) { // with propability select who has lowest f
			int random = (int) (Math.random() * propabilityArray.size());
			int index = propabilityArray.get(random);
			if (index < population.size()) {
				if (!survivors.contains(population.get(index))) {
					survivors.add(population.get(index));
				}
			} else { // is a index of children
				index = index - population.size();
				if (!survivors.contains(children.get(index))) {
					survivors.add(children.get(index));
				}
			}
		}

		return survivors;
	}

	//////////////////////////////////////////////////////////////////////

	private State reProduce(Problem problem, State father, State mother, double mutationPropability) { // reproduce
																										// child from
																										// mother and
		// father
		State child = father.crossOver(problem, mother);
		if (p(mutationPropability)) {
			child = child.mutation(problem);
		}
		return child;
	}

	//////////////////////////////////////////////////////////////////////

	private ArrayList<State> makesChildren(Problem problem, ArrayList<State> population, int m,
			double mutationPropability) { // m is number of children , select 2m parent from n (that can be
											// repetitive(one guy can be father of more than 1 child))

		ArrayList<Integer> propabilityArray = new ArrayList<Integer>();
		for (int i = 0; i < population.size(); i++) {
			double value = problem.maximumPossibleF() - problem.f(population.get(i));
			if (value < problem.maximumPossibleF() - problem.borderF()) {
				value /= 2;
			} else {
				value *= 2;
			}
			for (int j = 0; j < value; j++) {
				propabilityArray.add(i);
			}
		}

		ArrayList<State> children = new ArrayList<State>();
		double p = 0.1; // if a state is a repetitive add to population with this p
		while (children.size() < m) { // with propability select who has lowest f
			int random1 = (int) (Math.random() * propabilityArray.size());
			int index1 = propabilityArray.get(random1);
			int random2 = (int) (Math.random() * propabilityArray.size());
			int index2 = propabilityArray.get(random2);
			State parent1 = population.get(index1);
			State parent2 = population.get(index2);
			State child = reProduce(problem, parent1, parent2, mutationPropability);
			if (!children.contains(child)) {
				boolean valid = true;
				for (int i = 0; i < children.size(); i++) {
					if (problem.equal(child, children.get(i))) {
						valid = false;
						break;
					}
				}

				if (valid) {
					children.add(child);
				} else if (Math.random() < p) {
					children.add(child);
				}
			}
		}
		return children;

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetails(Problem problem, ArrayList<ArrayList<State>> myPath, ArrayList<double[]> bestWorstAverage,
			State bestChild) {
		int n = myPath.size();
		System.out.println("Number of Generations  : " + n + "\n");

		System.out.println("best , worst , average value in each generation : ");
		for (int i = 0; i < bestWorstAverage.size(); i++) {
			double[] ds = bestWorstAverage.get(i);
			System.out.println("[" + (i + 1) + "]( " + ds[0] + " , " + ds[1] + " , " + ds[2] + " )");
		}
		System.out.println();

		System.out.print("Answer : ");
		System.out.println(bestChild.print());

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetailsHillClimbing(Problem problem, int observedNodes, int extendedNodes,
			ArrayList<State> myPath, ArrayList<Action> myActions) {
		System.out.println("Number of observed nodes : " + observedNodes);
		System.out.println("Number of extended nodes : " + extendedNodes);
		System.out.print("Path states : ");
		for (State state : myPath) {
			System.out.print(state.print() + " ");
		}
		System.out.println();
		double cost = 0;
		System.out.print("Path actions : ");
		for (Action action : myActions) {
			cost += action.cost;
			System.out.print(action.print() + " ");
		}
		System.out.println();
		System.out.println("Path cost : " + cost);
		System.out.println("Final state worth : " + problem.f(myPath.get(myPath.size() - 1)));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void printDetailsSimulatedAnealing(Problem problem, int observedNodes, int extendedNodes,
			ArrayList<State> myPath, ArrayList<Action> myActions) {
		System.out.println("Number of observed nodes : " + observedNodes);
		System.out.println("Number of extended nodes : " + extendedNodes);
		System.out.print("Best Answer : ");
		// for (State state : myPath) {
		System.out.print(myPath.get(myPath.size() - 1).print() + " ");
		// }
		System.out.println();
		double cost = 0;
		// System.out.print("Path actions : ");
		// for (Action action : myActions) {
		// cost += action.cost;
		// System.out.print(action.print() + " ");
		// }
		// System.out.println();
		System.out.println("Path cost : " + cost);
		System.out.println("Final state worth : " + problem.f(myPath.get(myPath.size() - 1)));
	}
}
