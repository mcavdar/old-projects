package tree;
import javax.swing.JFrame;

import gui.SearchTreeFrame;

/**
 * Classe de lancement de l'application de recherche de solution et
 * de son interface graphique.
 */
public class SearchTreeLaunch
{	// Programme principal
	public static void main(String[] args)
	{	SearchTreeFrame frame = new SearchTreeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
