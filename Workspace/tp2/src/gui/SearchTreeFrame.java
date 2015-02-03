package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import algorithms.BreadthFirstRunnable;
import algorithms.DepthFirstItRunnable;
import algorithms.DepthFirstModRunnable;
import algorithms.DepthFirstRunnable;
import problem.Problem;
import tree.SearchTree;

/**
 * Affiche l'arbre de recherche.
 */
public class SearchTreeFrame extends JFrame
{	private static final long serialVersionUID = 1L;
	// largeur par défaut de la fenetre
	public static final int DEFAULT_WIDTH = 450;
	// hauteur par défaut de la fenetre
	public static final int DEFAULT_HEIGHT = 275;
	// représentation graphique de l'arbre
	private SearchTreeTree jtree;
	// arbre de recherche représenté
	private SearchTree tree;
	// boutons de l'interface graphique
	private JButton breadthButton;
	private JButton depthButton;
	private JButton depthItButton;
	private JButton depthModButton;
	
	/**
	 * Constructeur. 
	 * 
	 */
	public SearchTreeFrame()
	{	// initialisation du problème
		Problem problem = new Problem();
		tree = new SearchTree(problem);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("TP03 - Recherche aveugle");
		// création des panels pour les boutons
		jtree = new SearchTreeTree(tree);
		JScrollPane scrollPane = new JScrollPane(jtree);
		add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		// création du bouton utilisé pour lancer la recherche en largeur d'abord
		{	breadthButton = new JButton("Largeur");
			buttonPanel.add(breadthButton);
			ActionListener listener = new ActionListener()
			{	public void actionPerformed(ActionEvent event)
				{	actionBreadth();
				}
			};
			breadthButton.addActionListener(listener);
		}
		// création du bouton utilisé pour lancer la recherche en profondeur d'abord
		{	depthButton  = new JButton("Profondeur");
			buttonPanel.add(depthButton);
			ActionListener listener = new ActionListener()
			{	public void actionPerformed(ActionEvent event)
				{	actionDepth();
				}
			};
			depthButton.addActionListener(listener);
		}
		// création du bouton utilisé pour lancer la recherche en profondeur d'abord itérative
		{	depthItButton  = new JButton("Profondeur itérative");
			buttonPanel.add(depthItButton);
			ActionListener listener = new ActionListener()
			{	public void actionPerformed(ActionEvent event)
				{	actionItDepth();
				}
			};
			depthItButton.addActionListener(listener);
		}
		// création du bouton utilisé pour lancer la recherche en profondeur d'abord modifiée
		{	depthModButton  = new JButton("Profondeur modifiée");
			buttonPanel.add(depthModButton);
			ActionListener listener = new ActionListener()
			{	public void actionPerformed(ActionEvent event)
				{	actionModDepth();
				}
			};
			depthModButton.addActionListener(listener);
		}
		// création du bouton utilisé pour fermer l'application
		{	JButton button = new JButton("Fermer");
			buttonPanel.add(button);
			ActionListener listener = new ActionListener()
			{	public void actionPerformed(ActionEvent event)
				{	System.exit(0);
				}
			};
			button.addActionListener(listener);
		}
	}
	
	/**
	 * Crée un thread exécutant un parcours en largeur d'abord.  
	 */
	public void actionBreadth()
	{	switchButtons(false);
		BreadthFirstRunnable tr = new BreadthFirstRunnable(this,tree);
		Thread t = new Thread(tr);
		t.start();
	}
	
	/**
	 * Crée un thread exécutant un parcours en profondeur d'abord.  
	 */
	public void actionDepth()
	{	switchButtons(false);
		DepthFirstRunnable tr = new DepthFirstRunnable(this,tree);
		Thread t = new Thread(tr);
		t.start();
	}

	/**
	 * Crée un thread exécutant un parcours en profondeur d'abord itérative.  
	 */
	public void actionItDepth()
	{	switchButtons(false);
		DepthFirstItRunnable tr = new DepthFirstItRunnable(this,tree);
		Thread t = new Thread(tr);
		t.start();
	}

	/**
	 * Crée un thread exécutant un parcours en profondeur d'abord modifiée.  
	 */
	public void actionModDepth()
	{	switchButtons(false);
		DepthFirstModRunnable tr = new DepthFirstModRunnable(this,tree);
		Thread t = new Thread(tr);
		t.start();
	}

	/**
	 * Permet d'activer/désactiver les boutons de la GUI.
	 * @param state	vrai pour activer, faux pour désactiver
	 */
	public void switchButtons(boolean state)
	{	breadthButton.setEnabled(state);
		depthButton.setEnabled(state);
		depthItButton.setEnabled(state);
		depthModButton.setEnabled(state);
	}
}