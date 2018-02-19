package problem;

/**
 * Repr�sentation d'un �tat du probl�me.
 */
public class State { // indique si le berger a travers�
	private boolean berger;
	// indique si la ch�vre a travers�
	private boolean chevre;
	// indique si le loup a travers�
	private boolean loup;
	// indique si la salade a travers�
	private boolean salade;

	/**
	 * Constructeur.
	 * 
	 * @param berger
	 *            vrai si le berger a travers�
	 * @param chevre
	 *            vrai si la ch�vre a travers�
	 * @param loup
	 *            vrai si le loup a travers�
	 * @param salade
	 *            vrai si la salade a travers�
	 */
	public State(boolean berger, boolean chevre, boolean loup, boolean salade) {
		this.berger = berger;
		this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
	}

	public void placerElement(StringBuffer left, StringBuffer right,
			boolean element, char v) {
		if (element)
			right.append(v+" ,");
		else
			left.append(v+" ,");
	}

	/**
	 * Renvoie une repr�sentation textuelle de l'�tat.
	 * 
	 * @return la repr�sentation textuelle de l'�tat
	 */
	public String getName() { // TODO � compl�ter par l'�tudiant
		StringBuffer left = new StringBuffer();
		StringBuffer right = new StringBuffer();
		placerElement(left, right, this.berger, 'B');
		placerElement(left, right, this.chevre, 'C');
		placerElement(left, right, this.loup, 'L');
		placerElement(left, right, this.salade, 'S');
		return left.toString()+"|"+right.toString();
	}

	public String toString() {
		return getName();
	}

	/**
	 * Compare des �tats : deux �tats sont �gaux si les m�mes �l�ments sont
	 * situ�s aux m�mes endroits dans les deux �tats.
	 * 
	 * @param object
	 *            l'�tat � comparer
	 * @return vrai si les deux �tats sont �gaux
	 */
	public boolean equals(Object object) {
		boolean result;
		if (object == null)
			result = false;
		else if (!(object instanceof State))
			result = false;
		else {
			State temp = (State) object;
			result = temp.getName().equals(getName());
		}
		return result;
	}

	/**
	 * Renvoie la position du berger.
	 * 
	 * @return vrai si le berger est � l'est
	 */
	public boolean getBerger() {
		return berger;
	}

	/**
	 * Renvoie la position de la ch�vre.
	 * 
	 * @return vrai si la ch�vre est � l'est
	 */
	public boolean getChevre() {
		return chevre;
	}

	/**
	 * Renvoie la position du loup.
	 * 
	 * @return vrai si le loup est � l'est
	 */
	public boolean getLoup() {
		return loup;
	}

	/**
	 * Renvoie la position de la salade.
	 * 
	 * @return vrai si la salade est � l'est
	 */
	public boolean getSalade() {
		return salade;
	}
}
