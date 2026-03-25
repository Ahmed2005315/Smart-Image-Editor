import javax.swing.*;
import java.awt.image.BufferedImage;
public class Filter_Images {
    //crop
    BufferedImage Crop(BufferedImage img, int x, int y, int w, int h) {
        return img.getSubimage(x, y, w, h);
    }

    //Rotate
    BufferedImage Rotate(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage rotated = new BufferedImage(height, width, img.getType());
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                //Push method
                rotated.setRGB((height - 1) - y, x, img.getRGB(x, y));
                //pull method
                //rotated.setRGB(x, y, img.getRGB(y, width - 1 - x));
            }
        }

        return rotated;
    }

    BufferedImage GrayScale(BufferedImage img){

        int height= img.getHeight();
        int width= img.getWidth();
        BufferedImage grayImg=new BufferedImage(width,height,img.getType());
        for(int y=0;y<height;++y){
            for(int x=0;x<width;++x){
                int rgb=img.getRGB(x,y);
                int a = (rgb >> 24) & 0xff;
                int red=(rgb>>16)&0xff;
                int green=(rgb>>8)&0xff;
                int blue=rgb&0xff;
                int gray=(red+green+blue)/3;
                grayImg.setRGB(x, y,(a<<24)|(gray<<16)|(gray<<8)|gray );

            }
        }
        return grayImg;
    }

    BufferedImage BlackWhite(BufferedImage img){
        int threshold=128;
        int height= img.getHeight();
        int width= img.getWidth();
        BufferedImage BWImg=new BufferedImage(width,height,img.getType());
        for(int y=0;y<height;++y){
            for(int x=0;x<width;++x){
                int RGB=img.getRGB(x,y);
                int a = (RGB >> 24) & 0xff;
                int r=(RGB>>16)&0xff;
                int g=(RGB>>8)&0xff;
                int b=RGB&0xff;
                int gray=(r+g+b)/3;
                int result= (gray > threshold)?255:0;

                BWImg.setRGB(x, y,(a<<24)|(result<<16)|(result<<8)|result );
            }
        }
        return BWImg;
    }

BufferedImage resize(BufferedImage img,int New_Width,int New_Height)
{
    BufferedImage resized=new BufferedImage(New_Width,New_Height,img.getType());
    double Percent_Width= (double)(img.getWidth()/New_Width);
    double Percent_Height= (double)(img.getHeight()/New_Height);
    for(int x=0;x<New_Width;++x){
        for(int y=0;y<New_Height;++y){
            int xOrigin = Math.min((int)(x * Percent_Width), img.getWidth() - 1);
            int yOrigin = Math.min((int)(y * Percent_Height), img.getHeight() - 1);
            resized.setRGB(x, y, img.getRGB(xOrigin, yOrigin));
        }
    }
    return resized;
}

enum ColorsFilter
{
        RED,
        GREEN,
        CYAN,
        BLUE,
        YELLOW,
        MAGENTA
}
int clamp(int v){
        return Math.max(0,Math.min(255,v));
}
BufferedImage applyColorFilter(BufferedImage img,ColorsFilter color)
{
BufferedImage result=new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
    double boost = 1.5;
    double reduce = 1.0 / boost;

    for (int y = 0; y < img.getHeight(); ++y) {
        for (int x = 0; x < img.getWidth(); ++x) {

            int RGB = img.getRGB(x, y);

            int A = (RGB >> 24) & 0xff;
            int R = (RGB >> 16) & 0xff;
            int G = (RGB >> 8) & 0xff;
            int B = RGB & 0xff;

            int newR = R, newG = G, newB = B;

            switch (color) {
                case RED:
                    newR=clamp((int)(R*boost));
                    newG=clamp((int)(G*reduce));
                    newB=clamp((int)(B*reduce));
                    break;

                case GREEN:
                    newR = clamp((int)(R * reduce));
                    newG = clamp((int)(G * boost));
                    newB = clamp((int)(B * reduce));
                    break;

                case BLUE:
                    newR = clamp((int)(R * reduce));
                    newG = clamp((int)(G * reduce));
                    newB = clamp((int)(B * boost));
                    break;

                case CYAN:
                    newR = clamp((int)(R * reduce));
                    newG = clamp((int)(G * boost));
                    newB = clamp((int)(B * boost));
                    break;

                case YELLOW:
                    newR = clamp((int)(R * boost));
                    newG = clamp((int)(G * boost));
                    newB = clamp((int)(B * reduce));
                    break;

                case MAGENTA:
                    newR = clamp((int)(R * boost));
                    newG = clamp((int)(G * reduce));
                    newB = clamp((int)(B * boost));
                    break;
            }
        int NewRGB=(A<<24)|(newR<<16)|(newG<<8)|newB;
        result.setRGB(x,y,NewRGB);
    }
}
return result;
}
}

