/**
 * 
 */
package DCT;

import java.awt.Rectangle;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.plugin.filter.PlugInFilter;
import ij.plugin.frame.RoiManager;
import ij.process.Blitter;
import ij.process.ByteProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

/**
 * @author malembetirick
 *
 */
public class Plugin12_ implements PlugInFilter{
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
	ImagePlus imp;	
	int width;
	int height;
	private static ByteProcessor bpQuantification = new ByteProcessor(8, 8);
	
	@Override
	public void run(ImageProcessor arg0) {
		// TODO Auto-generated method stub
	    width = imp.getWidth();
	    height = imp.getHeight();
	    ImageConverter imc = new ImageConverter(imp);
		imc.convertToGray32();
		FloatProcessor fp = (FloatProcessor) imp.getProcessor();
		fp.subtract(128.0);
		DCT2D.forwardDCT(fp);
		imp.setProcessor(fp);
		imp.updateAndDraw();
		imp.setProcessor(fp);
		imp.updateAndDraw();
		bpQuantification.setIntArray(QY);
		fp.copyBits(bpQuantification, 0, 0, Blitter.DIVIDE);
		
		imp.setProcessor(fp);
		imp.updateAndDraw();
		
	}

	@Override
	public int setup(String arg0, ImagePlus arg1) {
		// TODO Auto-generated method stub
		this.imp= arg1;
		return Plugin12_.DOES_ALL;
	}
	
	public static void main(String[] args) {
		// set the plugins.dir property to make the plugin appear in the Plugins menu
		Class<?> clazz = Plugin12_.class;
		String url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class").toString();
		String pluginsDir = url.substring(5, url.length() - clazz.getName().length() - 6);
		System.setProperty("plugins.dir", pluginsDir);

		// start ImageJ
		new ImageJ();

		// open the Clown sample
		
		ImagePlus image = IJ.openImage("./wikipedia_extract.png");
		

		// run the plugin
		IJ.runPlugIn(clazz.getName(), "");
	}

}
