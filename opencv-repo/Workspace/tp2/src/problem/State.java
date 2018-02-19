package problem;

/**
 * Repr�sentation d'un �tat du probl�me. 
 */
public class State
{	// indique si le berger a travers�
	private boolean berger;
	// indique si la ch�vre a travers�
	private boolean chevre;
	// indique si le loup a travers�
	private boolean loup;
	// indique si la salade a travers�
	private boolean salade;

	/**
	 * Constructeur. 
	 * @param berger	vrai si le berger a travers�
	 * @param chevre	vrai si la ch�vre a travers�
	 * @param loup	vrai si le loup a travers�
	 * @param salade	vrai si la salade a travers�
	 */
	public State(boolean berger, boolean chevre, boolean loup, boolean salade)
	{	this.berger = berger;
		this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
	}

	/**
	 * M�thode utilis�e lors de la construction de la repr�sentation
	 * textuelle de l'�tat. 
	 * @param left	repr�sentation textuelle de l'�tat � gauche du pipe
	 * @param right	repr�sentation textuelle de l'�tat � droite du pipe
	 * @param element	�l�ment � traiter (berger, ch�vre, loup ou salade)
	 * @param rep	repr�sentation textuelle de cet �l�ment
	 */
	private void placeElement(StringBuffer left, StringBuffer right, boolean element, String rep)
	{	if(element)
			right.append(rep+" ");
		else
			left.append(rep+" ");
	}
	/**
	 * Renvoie une repr�sentation textuelle de l'�tat, sous la forme d'un ensemble
	 * de lettres s�par�es par un pipe. Le pipe repr�sente la rivi�re, B repr�sente
	 * le berger, C la chevre, L le loup et S la salade. Le c�t� gauche du pipe repr�sente
	 * la rive ouest et le c�t� droit la rive est.
	 *  
	 * @return	la repr�sentation textuelle de l'�tat
	 */
	public String getName()
	{	StringBuffer left = new StringBuffer();
		StringBuffer right = new StringBuffer();
		placeElement(left,right,berger,"B");
		placeElement(left,right,chevre,"C");
		placeElement(left,right,loup,"L");
		placeElement(left,right,salade,"S");		
		return left+"| "+right;
	}
	public String toString()
	{	return getName();	
	}

	/**
	 * Compare des �tats : deux �tats sont �gaux si les m�mes �l�ments sont situ�s 
	 * aux m�mes endroits dans les deux �tats.
	 * @param	object	l'�tat � comparer
	 * @return	vrai si les deux �tats sont �gaux
	 */
	public boolean equals(Object object)
	{	boolean result;
		if(object == null)
			result = false;
		else if(!(object instanceof State))
			result = false;
		else
		{	State temp = (State) object;
			result = temp.getName().equals(getName());
		}
		return result;
	}
	
	/**
	 * Renvoie la position du berger. 
	 * @return	vrai si le berger est � l'est
	 */
	public boolean getBerger()
	{	return berger;
	}

	/**
	 * Renvoie la position de la ch�vre. 
	 * @return	vrai si la ch�vre est � l'est
	 */
	public boolean getChevre()
	{	return chevre;
	}

	/**
	 * Renvoie la position du loup. 
	 * @return	vrai si le loup est � l'est
	 */
	public boolean getLoup()
	{	return loup;
	}

	/**
	 * Renvoie la position de la salade. 
	 * @return	vrai si la salade est � l'est
	 */
	public boolean getSalade()
	{	return salade;
	}
}
