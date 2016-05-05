package DCT;

import ij.IJ;
import ij.gui.Roi;
import ij.process.*;	// Pour classe Float Processor
import java.awt.Rectangle;

public class DCT2D{
	//Taille bloc
	protected static int BLOCK_SIZE = 8;
	// coefficients
    protected double[][] c;
    // matrice quelconque
    protected int[][] mt;
    public DCT2D(){
    	
    }
	public DCT2D(int bloc){
	    	this.BLOCK_SIZE = bloc;
	    	initialiserCoefficients();
	    	Matrix();
	    }
	//initialiser les coefficient de la matrice
	protected void initialiserCoefficients() {
        c = new double[BLOCK_SIZE][BLOCK_SIZE];
        //c(x)=1 si x >0
        for (int i=1;i<BLOCK_SIZE;i++) {
        	for (int j=1;j<BLOCK_SIZE;j++) {
        		c[i][j]=1;
        	}
        }
        //c(x)=o si x= 1/(racine de 2)
        for (int i=0;i<BLOCK_SIZE;i++) {
                c[i][0]=1/Math.sqrt(2.0);
        	c[0][i]=1/Math.sqrt(2.0);
        }
        c[0][0]=0.5;
    }
	//DCT2D selection de zones de traitement d'image
	protected double[][] forwardDCT(double[][] input) {
        double[][] output = new double[BLOCK_SIZE][BLOCK_SIZE];

        for (int u=0;u<BLOCK_SIZE;u++) {
          for (int v=0;v<BLOCK_SIZE;v++) {
            double sum = 0.0;
            for (int x=0;x<BLOCK_SIZE;x++) {
              for (int y=0;y<BLOCK_SIZE;y++) {
                sum+=input[x][y]*Math.cos(((2*x+1)/(2.0*BLOCK_SIZE))*u*Math.PI)*Math.cos(((2*y+1)/(2.0*BLOCK_SIZE))*v*Math.PI);
              }
            }
            sum*=c[u][v]/4.0;
            output[u][v]=sum;
          }
        }
        return output;
    }
	
	    
	    private int[][] Matrix() {
	        int[][] zz = new int[BLOCK_SIZE][BLOCK_SIZE];
	        int zval=0;
	        int zval2=BLOCK_SIZE*(BLOCK_SIZE-1)/2;
	        int i,j;
	        for (int k=0;k<BLOCK_SIZE;k++) {
	          if (k%2==0) {
	            i=0;
	            j=k;
	            while (j>-1) {
	              zz[i][j]=zval;
	              zval++;
	              i++;
	              j--;
	            }
	            i=BLOCK_SIZE-1;
	            j=k;
	            while (j<BLOCK_SIZE) {
	              zz[i][j]=zval2;
	              zval2++;
	              i--;
	              j++;
	            }
	          }
	          else {
	            i=k;
	            j=0;
	            while (i>-1) {
	              zz[i][j]=zval;
	              zval++;
	              j++;
	              i--;
	            }
	            i=k;
	            j=BLOCK_SIZE-1;
	            while (i<BLOCK_SIZE) {
	              zz[i][j]=zval2;
	              zval2++;
	              i++;
	              j--;
	            }
	          }
	        }
	        return zz;
	    }
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
			}
			//On applique la dct pour la ligne
			ligne = DCT1D.forwardDCT(ligne);
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
			colonne= DCT1D.forwardDCT(colonne);
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
	
public static void forwardDCT(Rectangle roi, FloatProcessor fp) 
{
	fp.setRoi(roi);
	int fph, fpw;
	fph = roi.height;
	fpw = roi.width;
	//On parcourt chaque ligne
	for(int i =roi.y; i<roi.y+8; i++)
	{
		System.out.println(i);
		//On créé le tableau de la ligne
		double[] ligne = new double[fpw];
		for(int j =roi.x; j<roi.x+8; j++)
		{
			double pixel = (double) fp.getPixelValue(j, i);
			ligne[j]=pixel;
			IJ.log(String.valueOf(pixel));
		}
		//On applique la dct pour la ligne
		ligne = DCT1D.forwardDCT(ligne);
		//On applique les nouveaux pixels à l'image
		for(int j=roi.x; j<roi.x+8; j++)
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
		for(int j=roi.y; j<roi.y+8;j++)
		{
			colonne[j]=(double) fp.getPixelValue(i, j);
		}
		colonne= DCT1D.forwardDCT(colonne);
		//On applique les nouveaux pixels à l'image
		IJ.log(String.valueOf(roi.y));
		for(int j=roi.y; j<roi.y+8;j++)
		{
			float pixel=(float)colonne[j];
			fp.setf(i,j,pixel);
		}
	}
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
