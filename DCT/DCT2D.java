package DCT;

import ij.process.*;	// Pour classe Float Processor
import java.awt.Rectangle;

abstract public class DCT2D{
	
	// ---------------------------------------------------------------------------------
	/**
	 * Transformation DCT 2D directe (m�thode de classe) utilisant la s�parabilit�
	 * @param fp Signal 2D d'entr�e et de sortie (MxN) (FloatProcessor)
	 */ 
	public static void forwardDCT(FloatProcessor fp) {
		
		//creer un tableau à deux dimensions à partir du floatProcessor,
		//pour chaque ligne du tableau, utiliser forwardDCT de la classe dct1d
		//pour chaque colonne de l'image resultante
		
		// Traiter les lignes
		/* � compl�ter */
		int x,y;
		x = fp.getWidth();
		y = fp.getHeight();
		double [] data = new double[x];
		float []dt = new float[x];
		for(int i=0;i<y; i++ ){
			fp.getRow(0, i, dt, x);
			for(int j=0; j<x;j++){
				data[j] = (double)dt[j];
			}
			DCT1D.forwardDCT(data);
			for(int z=0; z<x;z++){
				dt[z] = (float)data[z];
			}
			fp.putRow(0, i, dt, x);
			
		}
		
		// Traiter les colonnes de l'image r�sultant du traitement des lignes
		/* � compl�ter */
		double[] tab = new double[y];
		for(int c=0;c<x;c++ ){
			for(int l=0; l<y;l++) {
				tab[l]= (double)fp.getPixelValue(c, l);
				
		}
			DCT1D.forwardDCT(tab);
			for(int l2=0;l2<y;l2++){
				fp.putPixelValue(c, l2, tab[l2]);
			}
		}
	}

	// ---------------------------------------------------------------------------------
	/**
	 * Transformation DCT 2D inverse (m�thode de classe)
	 * @param fp Signal 2D d'entr�e et de sortie (FloatProcessor)
	 */
	public static void inverseDCT(FloatProcessor fp) {

		// Traiter les lignes
		/* � compl�ter */
		
		// Traiter les colonnes de l'image r�sultant du traitement des lignes
		/* � compl�ter */
	}
}