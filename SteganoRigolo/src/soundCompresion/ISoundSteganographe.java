//Fichier Interface pour la Steganografie dans un fichier audio.

package soundCompresion;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public interface ISoundSteganographe
{
	/**
	 * Fonction qui cache des bytes dans un fichier audio.
	 * Si la taille est trop petite, sa dimension est mise a la bonne taille pour pouvoir contenir les données.
	 * 
	 * @param masquePath 	Chemin vers le fichier audio qui contiendra des données cachées.
	 * @param targetPath	Chemin vers le fichier a cacher dans le fichier audio.
	 * @param resultPath	chemin vers le fichier résultat créer dans la fonction.
	 * @return				Fichier du "resultPath" (pas utilisé pour ce TP)
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedAudioFileException
	 * @throws Exception
	 */
	public File encode(String masquePath,String targetPath,String resultPath) throws IOException, IllegalArgumentException, UnsupportedAudioFileException, Exception ;


	/**
	 * Fonction pour sortir des données (encodé avec encode) d'un fichier audio.
	 * Si le fichier ne contient pas de donnée caché, la sortie est aléatoire.
	 * 
	 * @param masquePath 	Chemin vers le fichier audio contenant des données cachées.
	 * @param resultPath	chemin vers une copie du fichier original caché dans le fichier audio
	 * @return				Fichier du "resultPath" (pas utilisé pour ce TP)
	 * @throws IOException
	 */
	public File decode(String masquePath, String resultPath) throws IOException;
}
	