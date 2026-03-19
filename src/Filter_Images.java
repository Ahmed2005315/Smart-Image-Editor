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
}
