package DCT;

import ij.IJ;
import ij.gui.Roi;
import ij.process.*;	// Pour classe Float Processor
import java.awt.Rectangle;

abstract public class DCT2D{
	final static int BLOCK_SIZE = 8;
	
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
		int fph, fpw;
		fph = fp.getHeight();
		fpw = fp.getWidth();
		//On parcourt chaque ligne
		for(int i =0; i<fph; i++)
		{
			
			//On créé le tableau de la ligne
			double[] ligne = new double[fpw];
			for(int j = 0; j<fpw; j++)
			{
				double pixel = (double) fp.getPixelValue(j, i);
				ligne[j]=pixel;
				IJ.log(String.valueOf(pixel));
			}
			//On applique la dct pour la ligne
			ligne = DCT.DCT1D.forwardDCT(ligne);
			//On applique les nouveaux pixels à l'image
			for(int j=0; j<fpw; j++)
			{
				float pixel = (float)ligne[j];
				fp.setf(j, i, pixel);
			}
		}
		//On applique la deuxième dct
		for(int i=0;i<fpw; i++)
		{
			//On crée le tableau de la colonne
			double[] colonne = new double[fph];
			for(int j=0; j<fph;j++)
			{
				colonne[j]=(double) fp.getPixelValue(i, j);
			}
			colonne= DCT.DCT1D.forwardDCT(colonne);
			//On applique les nouveaux pixels à l'image
			for(int j=0; j<fph;j++)
			{
				float pixel=(float)colonne[j];
				fp.setf(i,j,pixel);
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
	
/*public static void forwardDCT(Rectangle roi) {
		
		//creer un tableau à deux dimensions à partir du floatProcessor,
		//pour chaque ligne du tableau, utiliser forwardDCT de la classe dct1d
		//pour chaque colonne de l'image resultante
		
		// Traiter les lignes
		/* � compl�ter 
		int x,y;
		x = roi.width;
		y = roi.height;
		double [] data = new double[x];
		float []dt = new float[x];
		for(int i=0;i<y; i++ ){
			roi.getRow(0, i, dt, x);
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
		/* � compl�ter 
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
	}*/

public static void applyDCT2D(FloatProcessor fp)
{
	int imageWidth=fp.getWidth();
	int imageHeight = fp.getHeight();
	// On commence la boucle
	for(int y=0; y<imageHeight; y+=DCT2D.BLOCK_SIZE);
		for(int x=0; x<=imageWidth; x+=DCT2D.BLOCK_SIZE)
		{
			//forwardDCT(new Rectangle(x, y, x+DCT2D.BLOCK_SIZE, y+DCT2D.BLOCK_SIZE));
		}
}

	

}