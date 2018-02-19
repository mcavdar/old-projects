package exceptions;


/**
 * Exception levée lorsqu'on tente d'appliquer une action à un état
 * et que cette action n'est pas possible pour cet état.
 *
 */
public class ImpossibleActionException extends Exception
{	private static final long serialVersionUID = 1L;
	// l'état concerné
	private Object state;
	// l'action intentée
	private Object action;

	/**
	 * Constructeur. 
	 * @param state	l'état concerné
	 * @param action	l'action intentée
	 */
	public ImpossibleActionException(Object state, Object action)
	{	this.state = state;
		this.action = action;
	}
	
	/**
	 * Renvoie l'action qui a provoqué l'exception 
	 * @return l'action qui a provoqué l'exception 
	 */
	public Object getAction()
	{	return action;
	}

	/**
	 * Renvoie l'état qui a provoqué l'exception 
	 * @return l'état qui a provoqué l'exception 
	 */
	public Object getStat()
	{	return state;
	}
}
