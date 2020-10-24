package dev.duels.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static BufferedImage loadImage(String path){
        try{
            URL url = ImageLoader.class.getResource(path);
            return ImageIO.read(url);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


}
