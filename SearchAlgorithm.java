import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;

public class SearchAlgorithm {

	boolean anomalyFound = false; // won't change unless we end up finding
									// something. obv
	private int imageHeight;
	private int imageWidth;
	private int buffRange = 30;
	private Color constant;
	private PNGDecode pd;
	
	public static void main(String[] args) {
		new SearchAlgorithm(args[0]); //how get an image???
	}

	public SearchAlgorithm(String path) {
		BufferedImage image = null;
		try { 
    		image = ImageIO.read(new File(path)); 
		} 
		catch (IOException e) { e.printStackTrace();}


		pd = new PNGDecode(image);

		Search();
	}

	public void Search() {
		PrintWriter out = null;
		try {
			out = new PrintWriter("out.txt");	
		} catch( FileNotFoundException e ){}

		
		setConstant();
		Color[][] colorArray = pd.decode();
//for (int y = 0; y < colorArray[y].length; y++) {
//			for (int x = 0; x < colorArray[; x++) {
		for (int y = 0; y < imageHeight; y++) {
			for (int x = 0; x < imageWidth; x++) {
				if (!compareColors(constant, colorArray[x][y], buffRange)) { 
					anomalyFound = true;
				}
			}
			
		}
		if( anomalyFound == true ){
			out.println("Found an object");
		}
		else {
			out.println("Didn't find object");
		}
		System.out.println("Foo");
		
	}

	public boolean compareColors(Color constant, Color var, int buffer) {
		if (Compare(constant.getRed(), var.getRed(), buffer)
				&& Compare(constant.getBlue(), var.getBlue(), buffer)
				&& Compare(constant.getGreen(), var.getGreen(), buffer)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean Compare(int constant, int color, int buffer) {
		if (Math.abs(constant - color) <= buffer)
			return true;
		else
			return false;
	}

	public void setConstant() {
		this.constant = new Color(pd.Mode()); 
	}

	// ////

	public class PNGDecode {

		private BufferedImage img;

		private Color[][] colorArray;
		
		public void setUp(BufferedImage image) {
			this.img = image;
			this.colorArray = new Color[img.getWidth()][img.getHeight()];
			imageHeight = image.getHeight();
			imageWidth = image.getWidth();
		}

		public PNGDecode(BufferedImage image) {
			setUp(image);
		}

		public Color[][] decode() {
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) { // look through
															// picture
					Color c = new Color(img.getRGB(x, y)); // grab the color. it
															// contains the rgb
															// values
					colorArray[x][y] = c; // stick it in the array.
				}
			}
			return colorArray;
		}

		public int Mode() { // PS THANK YOU JALAL
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					int count = 0;
					int key = img.getRGB(x, y);
					if (map.containsKey(key)) {
						count = map.get(key);
						map.put(key, ++count);
					} else {
						map.put(key, 0);
					}
				}
			}
			Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
			Entry<Integer, Integer> largest = it.next();
			while (it.hasNext()) {
				Entry<Integer, Integer> entry = it.next();
				if (entry.getValue() > largest.getValue()) {
					largest = entry;
				}
			}
			return largest.getKey();
		}

	}
}
