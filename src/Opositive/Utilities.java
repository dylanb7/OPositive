package Opositive;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Utilities {

	public static BufferedImage loadImage(String path){
		URL file = Utilities.class.getResource(path);
		try{
			BufferedImage image = ImageIO.read(new File(path));
			return image;
		}catch(Exception e){
			System.out.println(file.toString());
			return null;
		}
	}
	
}
