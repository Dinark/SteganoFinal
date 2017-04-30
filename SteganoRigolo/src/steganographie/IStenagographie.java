//Fichier Interface pour la Steganografie Image.

package steganographie;

import java.awt.image.BufferedImage;


public interface IStenagographie
{
	/**
	 * Fonction qui cache des bytes dans une image.
	 * Si l'image est trop petite, sa dimension est doubl�e pour pouvoir contenir les donn�es.
	 * 
	 * @param img 	Image dans laquelle on va cacher des donn�es.
	 * @param data  Donn�es en bytes � cacher dans l'image.
	 * @return		Image contenant des donn�e cach�e.
	 */
	public BufferedImage Steganofer(BufferedImage img, byte[] data);

	/**
	 * Fonction pour sortir des donn�es (encod� avec Steganofer) d'une Image.
	 * Si l'image ne contient pas de donn�e cach�, la sortie est al�atoire.
	 * 
	 * @param img 	Images contenant des donn�es cach�es
	 * @return		Donn�es en bytes cach�es dans l'image
	 */
	public byte[] DeSteganofer(BufferedImage img);


}
