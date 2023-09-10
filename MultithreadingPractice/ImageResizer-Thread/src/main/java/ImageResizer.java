import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ImageResizer extends Thread {

    private List<File> files;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(List<File> files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {

        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                BufferedImage newImage =
                        Scalr.resize(
                                image,
                                Scalr.Method.ULTRA_QUALITY,
                                Scalr.Mode.FIT_TO_WIDTH,
                                newWidth,
                                Scalr.OP_ANTIALIAS
                        );

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);

                image.flush();
                newImage.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + "ms");
    }
}
