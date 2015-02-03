package problem;

import exceptions.ImpossibleActionException;

/**
 * Classe repr�sentant une action applicable � un �tat d'origine et permettant
 * d'obtenir un �tat cible.
 */
public class Action { // indique si la ch�vre traverse
	private boolean chevre;
	// indique si le loup traverse
	private boolean loup;
	// indique si la salade traverse
	private boolean salade;
	// cout de l'action
	private double cost;

	/**
	 * Constructeur (le berger n'est pas repr�sent�, car il traverse
	 * syst�matiquement). Le co�t de l'action est nul.
	 * 
	 * @param chevre
	 *            indique si la ch�vre doit traverser
	 * @param loup
	 *            indique si le loup doit traverser
	 * @param salade
	 *            indique si la salade doit traverser
	 */
	public Action(boolean chevre, boolean loup, boolean salade) {
		this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
		this.cost = 0;
	}

	/**
	 * Constructeur (le berger n'est pas repr�sent�, car il traverse
	 * syst�matiquement).
	 * 
	 * @param chevre
	 *            indique si la ch�vre doit traverser
	 * @param loup
	 *            indique si le loup doit traverser
	 * @param salade
	 *            indique si la salade doit traverser
	 * @param cost
	 *            co�t de l'action.
	 */
	public Action(boolean chevre, boolean loup, boolean salade, double cost) {
		this.chevre = chevre;
		this.loup = loup;
		this.salade = salade;
		this.cost = cost;
	}

	/**
	 * M�thode utilis�e lors de la construction de la repr�sentation textuelle
	 * de l'action.
	 * 
	 * @param name
	 *            repr�sentation textuelle de l'action
	 * @param element
	 *            �l�ment � traiter (berger, ch�vre, loup ou salade)
	 * @param rep
	 *            repr�sentation textuelle de cet �l�ment
	 */
	private void placeElement(StringBuffer name, boolean element, String rep) {
		if (element)
			name.append(" " + rep);
	}

	/**
	 * Renvoie une repr�sentation textuelle de l'action, sous la forme d'un
	 * ensemble de lettres indiquant quels �l�ments traversent la rivi�re au
	 * cours de l'action. B repr�sente le berger, C la chevre, L le loup et S la
	 * salade.
	 * 
	 * @return la repr�sentation textuelle de l'action
	 */
	public String getName() {
		StringBuffer name = new StringBuffer("B");
		placeElement(name, chevre, "C");
		placeElement(name, loup, "L");
		placeElement(name, salade, "S");
		return name.toString();
	}

	public String toString() {
		return getName();
	}

	/**
	 * Renvoie le co�t de l'action.
	 * 
	 * @return le co�t de l'action.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Renvoie l'�tat obtenu si on applique l'action � l'�tat pass� en
	 * param�tre. Une ImpossibleActionException est lev�e quand il n'est pas
	 * possible d'appliquer l'action � l'�tat pass� en param�tre.
	 * 
	 * @param state
	 *            l'�tat d'origine
	 * @return l'�tat cible obtenu en appliquant l'action
	 * @throws ImpossibleActionException
	 */
	public State apply(State state) throws ImpossibleActionException {

		if ((!(state.getChevre() ^ state.getLoup()) && state.getBerger()
				^ state.getChevre())
				|| (!(state.getChevre() ^ state.getSalade()) && state
						.getBerger() ^ state.getChevre()))
			throw new ImpossibleActionException(state, this);
		else {
			if ((this.chevre && this.loup) || (this.chevre && this.salade)
					|| (this.loup && this.salade))
				throw new ImpossibleActionException(state, this);
			else {
				if (this.chevre) {
					if (state.getChevre() ^ state.getBerger())
						throw new ImpossibleActionException(state, this);
					else
						return new State(!state.getBerger(),
								!state.getChevre(), state.getLoup(),
								state.getSalade());
				} else if (this.loup) {
					if (state.getLoup() ^ state.getBerger())
						throw new ImpossibleActionException(state, this);
					else {
						if (!(state.getChevre() ^ state.getSalade()))
							throw new ImpossibleActionException(state, this);
						else
							return new State(!state.getBerger(),
									state.getChevre(), !state.getLoup(),
									state.getSalade());
					}
				} else if (this.salade) {
					if (state.getSalade() ^ state.getBerger())
						throw new ImpossibleActionException(state, this);
					else {
						if (!(state.getChevre() ^ state.getLoup()))
							throw new ImpossibleActionException(state, this);
						else
							return new State(!state.getBerger(),
									state.getChevre(), state.getLoup(),
									!state.getSalade());
					}
				} else {
					if (!(state.getSalade() ^ state.getChevre())
							|| !(state.getLoup() ^ state.getChevre()))
						throw new ImpossibleActionException(state, this);
					else
						return new State(!state.getBerger(), state.getChevre(),
								state.getLoup(), state.getSalade());
				}
			}
		}
	}
}