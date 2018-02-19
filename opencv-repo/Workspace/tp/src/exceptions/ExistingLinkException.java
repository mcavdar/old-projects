package exceptions;

/**
 * Exception lev�e lorsqu'on tente de cr�er un lien dans un graphe
 * et que ce lien est d�j� pr�sent.
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
	 * Renvoie le lien qui a provoqu� l'exception 
	 * @return le lien qui a provoqu� l'exception 
	 */
	public Object getLink()
	{	return link;
	}
}
