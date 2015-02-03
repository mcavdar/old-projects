package exceptions;

/**
 * Exception levée lorsqu'on tente de créer un lien dans un graphe
 * et que ce lien est déjà présent.
 *
 */
public class ExistingLinkException extends Exception
{	private static final long serialVersionUID = 1L;
	// le lien provoquant l'exeption
	private Object link;

	/**
	 * Constructeur. 
	 * @param link	le lien provoquant l'exeption
	 */
	public ExistingLinkException(Object link)
	{	this.link = link;	 	
	}
	
	/**
	 * Renvoie le lien qui a provoqué l'exception 
	 * @return le lien qui a provoqué l'exception 
	 */
	public Object getLink()
	{	return link;
	}
}
