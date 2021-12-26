
import java.awt.image.BufferedImage;
import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.URL;

public class test1 {
    static int k=0;
    public String path;
    String  path1="C:/Users/matth/Desktop/Netbeans/OptimizedVersion/BasicUImongodb/Images/";

    String gitdir="https://github.com/findthetrue/ImageStorage/blob/main/images/";
    String gitdir1="https://github.com/findthetrue/ImageStorage/blob/main/images/";

    public test1() {
   
    }
    public void ImageControl(String dir,String dir1) throws IOException{
       String t1=path1+"image"+k+".jpg";
       path=t1;
       System.out.println(path);
       String destinationFile = path;
       String t2=gitdir1+dir+"/"+dir1+".jpg?raw=true";
       gitdir=t2;
       System.out.println(gitdir);
       SaveImage(gitdir, destinationFile);
       k++;
       
    }

public static void SaveImage(String imageUrl, String destinationFile) throws IOException {
    URL url = new URL(imageUrl);
    InputStream is = url.openStream();
    OutputStream os = new FileOutputStream(destinationFile);

    byte[] b = new byte[2048];
    int length;

    while ((length = is.read(b)) != -1) {
        os.write(b, 0, length);
    }

    is.close();
    os.close();
}

public void DeleteImage(String imagepath) throws IOException {
    File remove=new File(imagepath);
    remove.delete();
  
}


}
    