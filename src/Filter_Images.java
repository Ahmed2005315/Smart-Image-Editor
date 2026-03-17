import java.awt.image.BufferedImage;
public class Filter_Images {
    //crop
    BufferedImage Crop(BufferedImage img,int x,int y,int w,int h){
        return img.getSubimage(x,y,w,h);
    }
    //Rotate
    BufferedImage Rotate(BufferedImage img){
        int width=img.getWidth();
        int  height=img.getHeight();
        BufferedImage rotated=new BufferedImage(height,width,img.getType());
        for(int y=0;y<height;++y)
        {
            for(int x=0;x<width;++x)
            {
                //Push method
                rotated.setRGB((height-1)-y,x,img.getRGB(x,y));
                //pull method
                //rotated.setRGB(x, y, img.getRGB(y, width - 1 - x));
            }
        }

        return rotated;
    }
}
