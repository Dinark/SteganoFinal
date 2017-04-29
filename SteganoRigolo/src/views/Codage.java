//Classe pour l'interface du Codage
package views;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Codage extends JPanel {
	
	String to_code_name;//contient le nom de fichier a coder
	String to_code_path;//contient le chemin de fichier a coder
	String to_code_parent;
	
	String code_in_name;//contient le nom de fichier a coder
	String code_in_path;//contient le chemin de fichier a coder
	String code_in_parent;
	
	
	JLabel l=new JLabel();
	JLabel l1=new JLabel();
	
	JButton to_code=new JButton("fichier a coder ");
	JButton code_in=new JButton("fichier support");
	
	JPanel PanBoutton =new JPanel();/**panel pour les bouttons*/
	JPanel PanLabel=new JPanel();	/**panel pour les labels*/
	
	//Creation d'un selecteur de ficher.
		JFileChooser parcourir=new JFileChooser("fichier cible");
		
	
	
	/**
	 * fonction pour lier l'interface dans l'onglet Codage.
	 */
	public Codage(){
		
		
		positioner();
		
	    //gbc.gridwidth = GridBagConstraints.REMAINDER;
	  
		/**action a faire pour le boutton de fichier a coder*/
		to_code.addActionListener(new ActionListener(){
			
				public void actionPerformed(ActionEvent event)
				{
					int retour = parcourir.showOpenDialog(to_code);
					if (retour == JFileChooser.APPROVE_OPTION) 
					{
					    to_code_name = parcourir.getSelectedFile().getName();
					    l.setText(to_code_name);
					    to_code_path = parcourir.getSelectedFile().getAbsolutePath();
					    to_code_parent=parcourir.getSelectedFile().getParent();
					    System.out.println(to_code_path);
					}else if (retour == JFileChooser.CANCEL_OPTION) 
					{
							System.out.println("rien_choisi");
					}
				}	
			
		});
		
		/**action a faire pour le boutton fichier support*/
		
		code_in.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event)
			{
				int retour = parcourir.showOpenDialog(code_in);
				if (retour == JFileChooser.APPROVE_OPTION) 
				{
				    code_in_name = parcourir.getSelectedFile().getName();
				    code_in_path = parcourir.getSelectedFile().getAbsolutePath();
				    sourceImage(code_in_path,code_in_name);
				    l1.setText(code_in_name);
				    System.out.println(code_in_path);
				}else if (retour == JFileChooser.CANCEL_OPTION) 
				{
						System.out.println("rien choisi");
				}
			}	
		
	});
		
		
		
	}
	
	/**
	 * Permet de positionner les boutons de l'interface
	 */
	public void positioner(){
		this.setBackground(Color.gray);
		this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();   //positioner les bouttons
	    l.setBackground(Color.black);
	    
	    gbc.gridx=0;	    gbc.gridy=0;
	    gbc.gridwidth=1;  gbc.gridheight=1;
	    PanBoutton.setPreferredSize(new Dimension(160,65));
	    PanBoutton.add(to_code,gbc);	    
	    PanBoutton.add(code_in,gbc);
	    this.add(PanBoutton,gbc);

	    gbc.gridx=1;
	    PanLabel.setPreferredSize(new Dimension(210,65));
	    l.setPreferredSize(new Dimension(200,30));
	    l1.setPreferredSize(new Dimension(200,30));
	    PanLabel.add(l);
	    PanLabel.add(l1);
	    this.add(PanLabel,gbc);
	}
	
	/**
	 * Fonction pour chercher le chemin absolu d'une image
	 * 
	 * @param Chemin de l'image
	 * @param nom de l'image
	 */	
	public void sourceImage(String path,String name){
		int i,j;
		j=path.length()-name.length();
		String path_file="";
		for(i=0;i<j;i++)
			path_file=path_file+path.charAt(i);
		
		code_in_parent=path_file;
		System.out.println(code_in_parent);
		
		
	}
		  
		

}