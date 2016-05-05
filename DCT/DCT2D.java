package DCT;

import ij.IJ;
import ij.gui.Roi;
import ij.process.*;	// Pour classe Float Processor
import java.awt.Rectangle;

abstract public class DCT2D{
	final static int BLOCK_SIZE = 8;
	public final static int[][] QY = {
		    {16, 12, 14, 14,  18,  24,  49,  72},
		    {11, 12, 13, 17,  22,  35,  64,  92},
		    {10, 14, 16, 22,  37,  55,  78,  95},
		    {16, 19, 24, 29,  56,  64,  87,  98},
		    {24, 26, 40, 51,  68,  81, 103, 112},
		    {40, 58, 57, 87, 109, 104, 121, 100},
		    {51, 60, 69, 80, 103, 113, 120, 103},
		    {61, 55, 56, 62,  77,  92, 101,  99}
		};
	
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
	
public static void forwardDCT(FloatProcessor fp, int xStart, int yStart ) {
		
		Roi theRoi= new Roi(xStart,yStart,8,8);
		fp.setRoi(theRoi);
		Rectangle rect = fp.getRoi();
		//creer un tableau à deux dimensions à partir du floatProcessor,
		//pour chaque ligne du tableau, utiliser forwardDCT de la classe dct1d
		//pour chaque colonne de l'image resultante
		
		// Traiter les lignes
		/* � compl�ter */
		int fph, fpw;
		fph = rect.height;
		fpw = rect.width;
		//On parcourt chaque ligne de la roi
		for(int i =yStart; i<yStart+fph; i++)
		{
			
			//On créé le tableau de la ligne
			double[] ligne = new double[fpw];
			//On parcourt la ligne actuel
			for(int cursorLine = 0; cursorLine<fpw; cursorLine++)
			{
				double pixel = (double) fp.getPixelValue(xStart+cursorLine, i);
				ligne[cursorLine]=pixel;
				
			}
			//On applique la dct pour la ligne
			ligne = DCT.DCT1D.forwardDCT(ligne);
			//On applique les nouveaux pixels à l'image
			for(int cursorLine=0; cursorLine<fpw; cursorLine++)
			{
				float pixel = (float)ligne[cursorLine];
				fp.setf(xStart+cursorLine, i, pixel);
			}
		}
		//On applique la deuxième dct
		for(int i=xStart;i<xStart+fpw; i++)
		{
			//On crée le tableau de la colonne
			double[] colonne = new double[fph];
			for(int j=0; j<fph;j++)
			{
				colonne[j]=(double) fp.getPixelValue(i, yStart+j);
			}
			colonne= DCT.DCT1D.forwardDCT(colonne);
			//On applique les nouveaux pixels à l'image
			for(int j=0; j<fph;j++)
			{
				float pixel=(float)colonne[j];
				fp.setf(i,yStart+j,pixel);
			}
		}
		
	}

public static void testROI( Rectangle roi, FloatProcessor fp)
{
	System.out.println("Largeur avant Roi : +"+fp.getWidth());
	System.out.println("Hauteur avant Roi : +"+fp.getHeight());
	fp.setRoi(roi);
	System.out.println("Largeur après Roi : +"+fp.getWidth());
	roi.getWidth();
}

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