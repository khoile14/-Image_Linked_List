import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class contains utility methods for loading and saving images.
 */
public final class Utilities {
    /**
     * Loads an image from a PGM file.
     * @param pgmFile the file to load.
     * @return the image.
     */
    public static Image<Short> loadImage(String pgmFile) {
        try {
            File file = new File(pgmFile);
            Scanner sc = new Scanner(file);
            String type = sc.nextLine();
            if (!type.equals("P2")) {
                throw new RuntimeException();
            }
            String[] arr = sc.nextLine().split(" ");
            int width = Integer.parseInt(arr[0]);
            int height = Integer.parseInt(arr[1]);

            sc.nextLine();

            Image<Short> image = new Image<Short>(width, height);

            ImageIterator<Short> iterator = (ImageIterator<Short>) image.iterator();

            Scanner sc2 = new Scanner(sc.nextLine());

            while (iterator.hasNext()) {
                iterator.next().setValue(sc2.nextShort());
            }


            sc.close();

            return image;
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Saves an image to a PGM file.
     * @param image the image to save.
     * @param pgmFile the file to save to.
     */
    public static void saveImage(Image<Short> image, String pgmFile) {
        try {
            File file = new File(pgmFile);
            PrintWriter writer = new PrintWriter(file);

            writer.println("P2");
            writer.println(image.getWidth() + " " + image.getHeight());
            writer.println("255");
            ImageIterator<Short> iterator = (ImageIterator<Short>) image.iterator();

            writer.print(iterator.next().getValue());
            while (iterator.hasNext()) {
                writer.print(" ");
                writer.print(iterator.next().getValue());
            }


            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
