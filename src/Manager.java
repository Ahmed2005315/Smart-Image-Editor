import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Manager {
    BufferedImage image;
    File CurrentFile;
    void Load(){
        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter filterFiles=new FileNameExtensionFilter(
                "Image Files","jpg","jpeg","png","bmp");
        fileChooser.setFileFilter(filterFiles);
        int result=fileChooser.showOpenDialog(null);
        if(result== JFileChooser.APPROVE_OPTION){
            try {
                CurrentFile = fileChooser.getSelectedFile();
                image=ImageIO.read(CurrentFile);
                System.out.println(("loaded"+CurrentFile.getName()));
            } catch (IOException e) {
                System.out.println("Error loading");
            }
        }
    }

    void save(boolean SameFile){
        try{
        File outputFile;
        if(SameFile&& CurrentFile!=null){
            outputFile=CurrentFile;
        }
        else {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG Image", "jpg"
            );
            fileChooser.setFileFilter(filter);
            int result=fileChooser.showSaveDialog(null);
            if(result==JFileChooser.APPROVE_OPTION)
                outputFile=fileChooser.getSelectedFile();
            else
                return;
        }
        ImageIO.write(image,"jpg",outputFile);
            System.out.println("Image saved");
        }
        catch (IOException e) {
        System.out.println("Saved failed");
        }
    }
}
