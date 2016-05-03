/**
 * 
 */
package DCT;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

/**
 * @author malembetirick
 *
 */
public class Plugin12_ implements PlugInFilter{
	ImagePlus imp;	
	int width;
	int height;
	
	@Override
	public void run(ImageProcessor arg0) {
		// TODO Auto-generated method stub
	    width = imp.getWidth();
	    height = imp.getHeight();
		FloatProcessor fp = new FloatProcessor(width, height);
		fp.subtract(128.0);
		DCT2D.forwardDCT(fp);
		//dupliquer l'image
		System.out.println("traitement effectue");
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
		//ImageConverter imc = new ImageConverter(image);
		//imc.convertToGray8();
		//image.updateAndDraw();
		image.show();

		// run the plugin
		IJ.runPlugIn(clazz.getName(), "");
	}

}
