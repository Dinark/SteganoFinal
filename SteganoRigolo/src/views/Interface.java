package views;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.tika.Tika;

import soundCompresion.SoundStenagographe;
import steganographie.IStenagographie;
import steganographie.Stenagographie;


public class Interface extends JFrame implements ActionListener{


	CardLayout cl = new CardLayout();       		//manupilation des panneaux
	JPanel content = new JPanel();				//Contient les Panneaux de codage et decodage
	String[] listContent = {"Codage","Decodage"}; 
	JButton b3=new JButton("Commencer");
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
				we_code=true;}

		});


		//Définition de l'action du bouton decodage

		b2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent event){ cl.show(content, listContent[1]);
			b2.setEnabled(false); 
			b1.setEnabled(true);
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
					System.out.println("Lancement du Codage");



					File fileMasque = new File(Cod.code_in_path);


					System.out.println("lecture du fichier masque");

					
					if(fileMasque.exists())
					{

						
						//String typeMasque= URLConnection.guessContentTypeFromName(fileMasque.getName());
						String typeMasque = getFileContentType(fileMasque);
						
						System.out.println("Recherche type de "+ fileMasque.getName() +" : " + typeMasque);

						String[] splitString =typeMasque.split("/");

						System.out.println("Recherche type" + typeMasque);

						if(splitString[0].equals("image") )
						{
							System.out.println("Lancement codage image");

							codageImage(fileMasque);

						}
						else if(splitString[0].equals("audio"))
						{
							if( !splitString[1].equals("mpeg3") && !splitString[1].equals("mpeg") )
							{
								Object[] options = { "continuer", "Annuler" };
								int reponse = JOptionPane.showOptionDialog(null, 
										"Pour des raisons de compatibilité,il est preferable de travailler avec des fichiers audio/mpeg3",
										"Codage de fichier MP3",

										JOptionPane.DEFAULT_OPTION,
										JOptionPane.WARNING_MESSAGE,

										null, options, options[0]);


								if(reponse == 1)
								{
									return;
								}
							}

							codageSon(fileMasque);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Type de fichier masque inconnu", "Fichier inconnu", JOptionPane.ERROR_MESSAGE); 

						}

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Fichier inexistant Masque", "Fichier pour masque non existant", JOptionPane.ERROR_MESSAGE); 

					}
				}
				else
				{
					System.out.println("Lancement du Decodage");

					File fileAdecoder = new File(Dec.to_decode_path);

					if(fileAdecoder.exists())
					{

						String typeMasque = 
								getFileContentType(fileAdecoder);
;

						String[] splitString =typeMasque.split("/");

						if(splitString[0].equals("image") )
						{

							decodeImage();

						}
						else if(splitString[0].equals("audio"))
						{
							decodeAudio(fileAdecoder);

						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Fichier inexistant Decodage", "Fichier pour decodage non existant", JOptionPane.ERROR_MESSAGE); 

					}
				}
			}

			private void decodeAudio(File fileAdecoder) {
				SoundStenagographe stenago =  new SoundStenagographe(1);
				
				String resultPath = Dec.to_decode_parent  + name(fileAdecoder.getName()) + "_decode";  
				try {
					File newFile = new File(resultPath);
					if(newFile.exists())
					{
						newFile.delete();
					}
					stenago.decode(fileAdecoder.getAbsolutePath(), resultPath);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erreur Decodage", "Une erreur est survenue pendant le decodage du fichier", JOptionPane.ERROR_MESSAGE); 
					e.printStackTrace();
				}
			}

			private void decodeImage() {
				try{
					BufferedImage I=ImageIO.read(new File(Dec.to_decode_path));
					byte[] data_decoded=methode.DeSteganofer(I);
					Files.write(new File(Dec.to_decode_parent+name(Dec.to_decode_name)+"_destegano").toPath(), data_decoded);
				}
				catch (IOException e) {  e.printStackTrace(); }
			}

			private void codageImage(File fileMasque) {
				try{
					imgSupport=ImageIO.read(fileMasque);
					File cible = new File(Cod.to_code_path);
					if(cible.exists())
					{
						data = Files.readAllBytes(new File(Cod.to_code_path).toPath());
						if(imgSupport!=null && data != null)
						{
							code_in_picture();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Fichier cible inexistant ", "Fichier a coder non existant", JOptionPane.ERROR_MESSAGE); 
					}



				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void codageSon(File fileMasque) {

				File cible = new File(Cod.to_code_path);
				if(cible.exists())
				{
					
					SoundStenagographe soundCon = new SoundStenagographe(1);

					String resultPath = Cod.code_in_parent + name(fileMasque.getName()) + "_stegano.wav";
					try {
						File newFile = new File(resultPath);
						if(newFile.exists())
						{
							newFile.delete();
						}
						
						soundCon.encode(fileMasque.getAbsolutePath(),cible.getAbsolutePath(), resultPath);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erreur creation fichier Wav", "Une erreur est survenue pendant la creation du wav", JOptionPane.ERROR_MESSAGE); 

						e.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fichier cible inexistant ", "Fichier a coder non existant", JOptionPane.ERROR_MESSAGE); 
				}
			}


		});


		this.add(afficher);



		container.add(b3);
		this.getContentPane().add(boutonPane, BorderLayout.NORTH);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(container, BorderLayout.SOUTH);

		this.setVisible(fermer);

	}

	/**methode pour appliquer le codage dans une image*/
	private void code_in_picture(){
		stegonagraphe = methode.Steganofer(imgSupport, data);
		afficher =new JLabel(new ImageIcon(stegonagraphe));
		//extensionn=extension(Cod.code_in_name); 

		try{
			System.out.println(Cod.code_in_parent+Cod.code_in_name);
			savePicture(stegonagraphe, Cod.code_in_parent,Cod.code_in_name);
		}catch (IOException e){
			e.printStackTrace();
		}
	}



	public void savePicture(BufferedImage image,String path_file,String name) throws IOException
	{

		File nomfichier = new File(path_file +name(name) + "_stegano.png");
		ImageIO.write(image, "png" , nomfichier);

	}



	public String extension(String name){
		
		String res = "";
		String split[] = name.split("\\.");
		if(split.length > 2)
		{
			res = split[1];
		}
		
		return res; 
	}
	public String name(String name){
		String res = "";
		String split[] = name.split("\\.");
		if(split.length > 1)
		{
			res = split[0];
		}

		return res; 
	}
	/**
	 * permet dobtenir le type d un fichier
	 * @param fileName le nom du fichier
	 * @return le type mime
	 */
	public String getFileContentType(File file) {
	    
		Tika tika = new Tika();
		String fileType = "unknown/unknown";
		try {
			fileType = tika.detect(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return fileType;
	}







	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	} 

}
