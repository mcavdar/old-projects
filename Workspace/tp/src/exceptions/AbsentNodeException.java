package exceptions;

/**
 * Exception lev�e lorsqu'on tente d'effectuer une op�ration
 * en utilisant un noeud qui n'appartient pas au graphe consid�r�.
 *
 */
public class AbsentNodeException extends Exception
{	private static final long serialVersionUID = 1L;
	// le noeud provoquant l'exeption
	private Object node;
	
	
	/**
	 * Constructeur. 
	 * @param node	le noeud provoquant l'exeption
	 */
	public AbsentNodeException(Object node)
	{	this.node = node;
	}
	
	/**
	 * Renvoie le noeud qui a provoqu� l'exception 
	 * @return le noeud qui a provoqu� l'exception 
	 */
	public Object getNode()
	{	return node;
	}
}
