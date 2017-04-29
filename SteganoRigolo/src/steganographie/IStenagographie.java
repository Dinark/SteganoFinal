//Fichier Interface pour la Steganografie Image.

package steganographie;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface IStenagographie
{
	/**
	 * Fonction qui cache des bytes dans une image.
	 * Si l'image est trop petite, sa dimension est doublée pour pouvoir contenir les données.
	 * 
	 * @param img 	Image dans laquelle on va cacher des données.
	 * @param data  Données en bytes à cacher dans l'image.
	 * @return		Image contenant des donnée cachée.
	 */
	public BufferedImage Steganofer(BufferedImage img, byte[] data);

	/**
	 * Fonction pour sortir des données (encodé avec Steganofer) d'une Image.
	 * Si l'image ne contient pas de donnée caché, la sortie est aléatoire.
	 * 
	 * @param img 	Images contenant des données cachées
	 * @return		Données en bytes cachées dans l'image
	 */
	public byte[] DeSteganofer(BufferedImage img);


}
