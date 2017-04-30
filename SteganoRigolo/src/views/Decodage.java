//Classe pour l'interface du Decodage
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

public class Decodage extends JPanel {
	
	String to_decode_name;//contient le nom de fichier a decoder
	String to_decode_path;//contient le chemin de fichier a decoder
	String to_decode_parent;
	
	JButton to_decode=new JButton("fichier a decoder");
	JFileChooser parcourir=new JFileChooser("fichier cible");
	
	JLabel l=new JLabel();
	JPanel p=new JPanel();
	
	/**
	 * fonction pour lier l'interface dans l'onglet décodage.
	 */
	
	public Decodage(){
		
		this.setBackground(Color.gray);
		
		
		to_decode.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event)
			{
				int retour = parcourir.showOpenDialog(to_decode);
				if (retour == JFileChooser.APPROVE_OPTION) 
				{
				    to_decode_name = parcourir.getSelectedFile().getName();
				    to_decode_path = parcourir.getSelectedFile().getAbsolutePath();
				    sourceImage(to_decode_path,to_decode_name);
				    l.setText(to_decode_name);
				}else if (retour == JFileChooser.CANCEL_OPTION) 
				{
						System.out.println("rien_choisi");
				}
			}	
		
	});

		//this.add(Box.createVerticalStrut(300));
		this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();   //positioner les bouttons
	    gbc.gridx=0;gbc.gridy=0;
		gbc.gridx=1;
		l.setPreferredSize(new Dimension(250,30));
		p.add(to_decode);
		p.add(l);
		this.add(p);
		
					
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
		
		to_decode_parent=path_file;
		System.out.println(to_decode_parent);
		
		
	}




}