package exceptions;


/**
 * Exception lev�e lorsqu'on tente d'appliquer une action � un �tat
 * et que cette action n'est pas possible pour cet �tat.
 *
 */
public class ImpossibleActionException extends Exception
{	private static final long serialVersionUID = 1L;
	// l'�tat concern�
	private Object state;
	// l'action intent�e
	private Object action;

	/**
	 * Constructeur. 
	 * @param state	l'�tat concern�
	 * @param action	l'action intent�e
	 */
	public ImpossibleActionException(Object state, Object action)
	{	this.state = state;
		this.action = action;
	}
	
	/**
	 * Renvoie l'action qui a provoqu� l'exception 
	 * @return l'action qui a provoqu� l'exception 
	 */
	public Object getAction()
	{	return action;
	}

	/**
	 * Renvoie l'�tat qui a provoqu� l'exception 
	 * @return l'�tat qui a provoqu� l'exception 
	 */
	public Object getStat()
	{	return state;
	}
}
