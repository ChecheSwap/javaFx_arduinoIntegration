/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
/**
 *
 * @author Master
 */
public class img {
    
    public InputStream getBinary(String path){
        
        FileInputStream iStr = null;    
        
        try{            
            File image = new File(path);
            iStr = new FileInputStream(image);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return (InputStream)iStr;
    }
    
    public InputStream getBinary(Image img){
        InputStream is = null;
        
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage)img, "jpg", baos);
            is = new ByteArrayInputStream(baos.toByteArray());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return is;
    }
    public InputStream getBinary(javafx.scene.image.Image img){
        
        InputStream is = null;
        
        try{
            
            BufferedImage bi = SwingFXUtils.fromFXImage(img, null);                        
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            is = new ByteArrayInputStream(baos.toByteArray());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return is;
    }    
    
    public javafx.scene.image.Image getImage(InputStream is){
        
        javafx.scene.image.Image i = null;
        
        try{
        
            BufferedImage image = ImageIO.read(is);
            i = SwingFXUtils.toFXImage(image,null);
        
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }        
        return i;        
    }
    
    public javafx.scene.image.Image getImage(byte [] arr){
        
        javafx.scene.image.Image i = null;
        
        try{
            InputStream is = new ByteArrayInputStream(arr);
            BufferedImage image = ImageIO.read(is);
            i = SwingFXUtils.toFXImage(image,null);
        
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }        
        return i;        
    }
    
    public Image getImage(InputStream is, boolean x){
        
        Image i = null;
        
        try{                    
            i = ImageIO.read(is);        
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }        
        return i;        
    }
    
}
