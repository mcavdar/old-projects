package problem;

import java.util.Iterator;
import java.util.Vector;

/**
 * Représente et permet d'initialiser le problème.
 */
public class Problem
{	// ensemble des états initiaux du problème
	private Vector<State> initialStates;
	// ensemble des états finaux du problème
	private Vector<State> finalStates;
	// ensemble des actions possibles
	private Vector<Action> actions;
	
	/**
	 * Constructeur. 
	 */
	public Problem()
	{	initialStates = new Vector<State>();
		finalStates = new Vector<State>();
		initStates();
		actions = new Vector<Action>();
		initActions();
	}

	/**
	 * Initialise les états initiaux et finaux. 
	 */
	public void initStates()
	{	// état initial
		State initialState = new State(false,false,false,false); 
		addInitialState(initialState);
		// état final
		State finalState = new State(true,true,true,true);
		addFinalState(finalState);		
	}

	/**
	 * Initialise la liste de toutes les actions possibles. 
	 */
	public void initActions()
	{	boolean chevre,loup,salade;
		chevre = false; loup = false; salade = false;
		actions.add(new Action(chevre,loup,salade,1));
		chevre = false; loup = false; salade = true;
		actions.add(new Action(chevre,loup,salade,2));
		chevre = false; loup = true; salade = false;
		actions.add(new Action(chevre,loup,salade,3));
		chevre = true; loup = false; salade = false;
		actions.add(new Action(chevre,loup,salade,3));
	}

	/**
	 * Rajoute un nouvel état initial au problème. 
	 * @param state	le nouvel état initial
	 */
	public void addInitialState(State state)
	{	if(!initialStates.contains(state))
			initialStates.add(state);
	}
	/**
	 * Rajoute un nouvel état final au problème. 
	 * @param state	le nouvel état final
	 */
	public void addFinalState(State state)
	{	if(!finalStates.contains(state))
			finalStates.add(state);
	}

	/**
	 * Renvoie un itérateur sur la liste des états initiaux.
	 * return un itértateur sur une liste d'états
	 */
	public Iterator<State> getInitialStatesIterator()
	{	return initialStates.iterator();	
	}

	/**
	 * Renvoie le premier état initial de la liste. 
	 * @return	le premier état initial
	 */
	public State getInitialState()
	{	return initialStates.get(0);	
	}

	/**
	 * Renvoie un itérateur sur la liste des actions possibles.
	 * return un itértateur sur une liste d'actions
	 */
	public Iterator<Action> getActionsIterator()
	{	return actions.iterator();	
	}
	
	/**
	 * Indique si l'état passé en paramètre fait partie des états finaux.
	 * @param state	l'état à tester
	 * @return	un booléen indiquant si cet état est final
	 */
	public boolean isFinalState(State state)
	{	return finalStates.contains(state);		
	}
}
