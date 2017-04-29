//Fichier Interface pour la Steganografie dans un fichier audio.

package soundCompresion;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface ISoundSteganographe
{
	/**
	 * Fonction qui cache des bytes dans un fichier audio.
	 * Si la taille est trop petite, sa dimension est mise a la bonne taille pour pouvoir contenir les donn�es.
	 * 
	 * @param masquePath 	Chemin vers le fichier audio qui contiendra des donn�es cach�es.
	 * @param targetPath	Chemin vers le fichier a cacher dans le fichier audio.
	 * @param resultPath	chemin vers le fichier r�sultat cr�er dans la fonction.
	 * @return				Fichier du "resultPath" (pas utilis� pour ce TP)
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedAudioFileException
	 * @throws Exception
	 */
	public File encode(String masquePath,String targetPath,String resultPath) throws IOException, IllegalArgumentException, UnsupportedAudioFileException, Exception ;


	/**
	 * Fonction pour sortir des donn�es (encod� avec encode) d'un fichier audio.
	 * Si le fichier ne contient pas de donn�e cach�, la sortie est al�atoire.
	 * 
	 * @param masquePath 	Chemin vers le fichier audio contenant des donn�es cach�es.
	 * @param resultPath	chemin vers une copie du fichier original cach� dans le fichier audio
	 * @return				Fichier du "resultPath" (pas utilis� pour ce TP)
	 * @throws IOException
	 */
	public File decode(String masquePath, String resultPath) throws IOException;
}
	