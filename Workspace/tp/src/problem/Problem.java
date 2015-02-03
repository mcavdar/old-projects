package problem;

import java.util.Iterator;
import java.util.Vector;

import javax.print.attribute.standard.Finishings;

/**
 * Repr�sente et permet d'initialiser le probl�me.
 */
public class Problem { // ensemble des �tats initiaux du probl�me
	private Vector<State> initialStates;
	// ensemble des �tats finaux du probl�me
	private Vector<State> finalStates;
	// ensemble des actions possibles
	private Vector<Action> actions;

	/**
	 * Constructeur.
	 */
	public Problem() {
		initialStates = new Vector<State>();
		finalStates = new Vector<State>();
		initStates();
		actions = new Vector<Action>();
		initActions();
	}

	/**
	 * Initialise les �tats initiaux et finaux.
	 */
	public void initStates() { // TODO � compl�ter par l'�tudiant
		State e = new State(false, false, false, false);
		initialStates.add(e);
		State f = new State(true, true, true, true);
		finalStates.add(f);
	}

	/**
	 * Initialise la liste de toutes les actions possibles.
	 */
	public void initActions() { // TODO � compl�ter par l'�tudiant
		Action a = new Action(true, false, false);
		actions.add(a);	
	}

	/**
	 * Rajoute un nouvel �tat initial au probl�me.
	 * 
	 * @param state
	 *            le nouvel �tat initial
	 */
	public void addInitialState(State state) {
		if (!initialStates.contains(state))
			initialStates.add(state);
	}

	/**
	 * Rajoute un nouvel �tat final au probl�me.
	 * 
	 * @param state
	 *            le nouvel �tat final
	 */
	public void addFinalState(State state) {
		if (!finalStates.contains(state))
			finalStates.add(state);
	}

	/**
	 * Renvoie un it�rateur sur la liste des �tats initiaux. return un
	 * it�rtateur sur une liste d'�tats
	 */
	public Iterator<State> getInitialStatesIterator() {
		return initialStates.iterator();
	}

	/**
	 * Renvoie le premier �tat initial de la liste.
	 * 
	 * @return le premier �tat initial
	 */
	public State getInitialState() {
		return initialStates.get(0);
	}

	/**
	 * Renvoie un it�rateur sur la liste des actions possibles. return un
	 * it�rtateur sur une liste d'actions
	 */
	public Iterator<Action> getActionsIterator() {
		return actions.iterator();
	}

	/**
	 * Indique si l'�tat pass� en param�tre fait partie des �tats finaux.
	 * 
	 * @param state
	 *            l'�tat � tester
	 * @return un bool�en indiquant si cet �tat est final
	 */
	public boolean isFinalState(State state) {
		return finalStates.contains(state);
	}
}
