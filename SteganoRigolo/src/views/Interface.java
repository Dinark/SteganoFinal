package views;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import steganographie.IStenagographie;
import steganographie.Stenagographie;


public class Interface extends JFrame implements ActionListener{


  CardLayout cl = new CardLayout();       		//manupilation des panneaux
  JPanel content = new JPanel();				//Contient les Panneaux de codage et decodage
  String[] listContent = {"Codage","Decodage"}; 
  JButton b3=new JButton("Commencer");
  JCheckBox check=new JCheckBox("avec perte"); //check box avec perte ou sans perte
  Boolean avec_perte;
  Boolean fermer;
  /*On crée deux panneaux*/
  Codage Cod = new Codage();			  //panneau de codage
  Decodage Dec = new Decodage();		  //panneau de decodage 
  JPanel boutonPane = new JPanel();       //panel contient les boutons

  JButton b1 = new JButton("Codage");
  JButton b2 = new JButton("Decodage");
  
  byte[] data = null;
  BufferedImage imgSupport = null;
  IStenagographie methode = new Stenagographie();/**le programme de codage*/
  BufferedImage stegonagraphe=null;       /**contient le resultat de codage*/
  JLabel afficher=new JLabel();
  Boolean we_code=true;



  public Interface(){

    this.setTitle("Steganographie");
    this.setSize(500, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    fermer=true;

    //Definition de l'action du bouton Codage

    b1.addActionListener(new ActionListener(){

      public void actionPerformed(ActionEvent event){  
      cl.show(content, listContent[0]);
      b1.setEnabled(false); 
      b2.setEnabled(true);
      check.setEnabled(true);
      we_code=true;}

    });


    //Définition de l'action du bouton decodage

    b2.addActionListener(new ActionListener(){

      public void actionPerformed(ActionEvent event){ cl.show(content, listContent[1]);
      b2.setEnabled(false); 
      b1.setEnabled(true);
      check.setEnabled(false);
      we_code=false;}

    });

        
    boutonPane.add(b1);
    boutonPane.add(b2);
    //On definit le layout
    content.setLayout(cl);
    //On ajoute les panel à la pile avec un nom pour les retrouver
    content.add(Cod, listContent[0]);
    content.add(Dec, listContent[1]);
    
    //Panel contient le button ok et le check box
    JPanel container=new JPanel();
    
    //Definition de l'action du bouton commencer
    b3.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent event){
    	if(we_code)
    	{
    		try{
	    		imgSupport=ImageIO.read(new File(Cod.code_in_path));
				data = Files.readAllBytes(new File(Cod.to_code_path).toPath());
    		} catch (IOException e) {
    		    e.printStackTrace();
    		}
    		
		  if(imgSupport!=null)
			{
			   code_in_picture();
			}
    	}
    	else
    	{
    		try{
		    	BufferedImage I=ImageIO.read(new File(Dec.to_decode_path));
		    	byte[] data_decoded=methode.DeSteganofer(I);
		    	Files.write(new File(Dec.to_decode_parent+"fichier_cacher").toPath(), data_decoded);
		    }catch (IOException e) {  e.printStackTrace(); }
		
    	}
 	   }
    });
    

    this.add(afficher);
    
   	
    //Definition de l'action du Check box
    check.addActionListener(new StateListener());
    
    container.add(check);
    container.add(b3);
    this.getContentPane().add(boutonPane, BorderLayout.NORTH);
    this.getContentPane().add(content, BorderLayout.CENTER);
    this.getContentPane().add(container, BorderLayout.SOUTH);
      
    this.setVisible(fermer);

  }
  
  /**methode pour appliquer le codage dans une image*/
  public void code_in_picture(){
		stegonagraphe = methode.Steganofer(imgSupport, data);
		afficher =new JLabel(new ImageIcon(stegonagraphe));
		String extensionn;
		extensionn=extension(Cod.code_in_name); 
		
		try{
			System.out.println(Cod.code_in_parent+Cod.code_in_name+extensionn);
			savePicture(stegonagraphe, Cod.code_in_parent,Cod.code_in_name,extensionn);
		}catch (IOException e){
			e.printStackTrace();
		}
  }
  
  public void savePicture(BufferedImage image,String path_file,String name,String ext) throws IOException
  {
	  
  File nomfichier = new File(path_file +name + "(stegano)."+ ext);
  ImageIO.write(image, ext , nomfichier);
  
  }
  
 
  
  public String extension(String name){
	  int i,j,l;
	  l=0;
	 String ext="";
	  for(i=0;i<name.length();i++)
		  if(name.charAt(i)=='.')
			  for(j=i+1;j<name.length();j++)
				  	ext=ext+name.charAt(j);
		  
	  
	  return ext; 
  }
  
  
  class StateListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	avec_perte=((JCheckBox)e.getSource()).isSelected();
	      System.out.println(" - état : " + avec_perte);
	    }
	  }
	
  

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
} 

}
