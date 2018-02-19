package exceptions;

/**
 * Exception lev�e lorsqu'on tente de cr�er un noeud dans un graphe
 * et que ce noeud est d�j� pr�sent.
 *
 */
public class ExistingNodeException extends Exception
{	private static final long serialVersionUID = 1L;
	// le noeud provoquant l'exeption
	private Object node;

	/**
	 * Constructeur. 
	 * @param node	le noeud provoquant l'exeption
	 */
	public ExistingNodeException(Object node)
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
