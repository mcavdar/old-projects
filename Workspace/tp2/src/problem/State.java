package problem;

/**
 * Représentation d'un état du problème. 
 */
public class State
{	// indique si le berger a traversé
	private boolean berger;
	// indique si la chèvre a traversé
	private boolean chevre;
	// indique si le loup a traversé
	private boolean loup;
	// indique si la salade a traversé
	private boolean salade;

	/**
	 * Constructeur. 
	 * @param berger	vrai si le berger a traversé
	 * @param chevre	vrai si la chèvre a traversé
	 * @param loup	vrai si le loup a traversé
	 * @param salade	vrai si la salade a traversé
	 */
	public State(boolean berger, boolean chevre, boolean loup, boolean salade)
	{	this.berger = berger;
		this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
	}

	/**
	 * Méthode utilisée lors de la construction de la représentation
	 * textuelle de l'état. 
	 * @param left	représentation textuelle de l'état à gauche du pipe
	 * @param right	représentation textuelle de l'état à droite du pipe
	 * @param element	élément à traiter (berger, chèvre, loup ou salade)
	 * @param rep	représentation textuelle de cet élément
	 */
	private void placeElement(StringBuffer left, StringBuffer right, boolean element, String rep)
	{	if(element)
			right.append(rep+" ");
		else
			left.append(rep+" ");
	}
	/**
	 * Renvoie une représentation textuelle de l'état, sous la forme d'un ensemble
	 * de lettres séparées par un pipe. Le pipe représente la rivière, B représente
	 * le berger, C la chevre, L le loup et S la salade. Le côté gauche du pipe représente
	 * la rive ouest et le côté droit la rive est.
	 *  
	 * @return	la représentation textuelle de l'état
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
	 * Compare des états : deux états sont égaux si les mêmes éléments sont situés 
	 * aux mêmes endroits dans les deux états.
	 * @param	object	l'état à comparer
	 * @return	vrai si les deux états sont égaux
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
	 * @return	vrai si le berger est à l'est
	 */
	public boolean getBerger()
	{	return berger;
	}

	/**
	 * Renvoie la position de la chèvre. 
	 * @return	vrai si la chèvre est à l'est
	 */
	public boolean getChevre()
	{	return chevre;
	}

	/**
	 * Renvoie la position du loup. 
	 * @return	vrai si le loup est à l'est
	 */
	public boolean getLoup()
	{	return loup;
	}

	/**
	 * Renvoie la position de la salade. 
	 * @return	vrai si la salade est à l'est
	 */
	public boolean getSalade()
	{	return salade;
	}
}
