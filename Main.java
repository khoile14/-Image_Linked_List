import java.io.FileNotFoundException;
import java.util.Iterator;

/*Do NOT submit this file.

    This is just a driver class for debugging your code.
    If any of this code doesn't compile with your other classes,
    it means that you have an error in the way you declare
    or implement the other classes -- do not alter this code to make
    it compile, fix the other classes instead.*/


public class P2 {
    public static void main(String[] args) throws FileNotFoundException {

/*if (args.length != 2)
        {
            System.err.println("Usage: java P2 <input filename> <output filename>");
            return;
        }*/


        // create a new image by loading a file
        Image<Short> image = Utilities.loadImage("test_image.pgm");
        
        // print the contents -- only if the optional toString() is implemented
        System.out.println(image);

        // run an enhanced-for loop and print all the pixels in a horizontal traversal

        for(Node<Short> node : image)
            System.out.print(node.getValue() + " ");
        System.out.println();
        // run the same iteration manually (i.e. with a while loop)
        Iterator<Node<Short>> iter = image.iterator();
        while(iter.hasNext())            System.out.print(iter.next().getValue() + " ");

        System.out.println();
        // run a vertical iteration
        iter = image.iterator(Direction.VERTICAL);
        while(iter.hasNext())
            System.out.print(iter.next().getValue() + " ");


        // modify the image and save it to a new file
        image.getHead().setValue((short) 0);
        Utilities.saveImage(image, "output1.txt");

        /*System.out.println("get width of the image: " + image.getWidth()); // should be 6
        System.out.println("get height of the image: " + image.getHeight()); // should be 4
        System.out.println("get the head node: " + image.getHead().getValue()); // should be 0

        System.out.println("--------------------");

        System.out.println("inserting row at the beginning");
        image.insertRow(0, (short) 5);
        System.out.println(image);
        System.out.println("getting the new Head node: " + image.getHead().getValue()); // should be 5
        System.out.println("new Height: " + image.getHeight()); // should be 5


        System.out.println("--------------------");

        System.out.println("inserting row in the middle");
        image.insertRow(3, (short) 6);
        System.out.println(image);
        System.out.println("new Height: " + image.getHeight()); // should be 6

        System.out.println("--------------------");

        System.out.println("inserting row at the end");
        image.insertRow(6, (short) 7);
        System.out.println(image);
        System.out.println("new Height: " + image.getHeight()); // should be 7

        System.out.println("--------------------");

        System.out.println("Check for correct exception handling");
        try{
            image.insertRow(-1, (short) 7); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index less than 0");
        }
        try{
            image.insertRow(8, (short) 7); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index greater than height");
        }

        System.out.println("--------------------");

        System.out.println("removing column at the beginning");
        image.removeColumn(0);
        System.out.println(image);
        System.out.println("new Width: " + image.getWidth()); // should be 5
        System.out.println("new Head node: " + image.getHead().getValue()); // should be 5

        System.out.println("--------------------");

        System.out.println("removing column in the middle");
        image.removeColumn(2);
        System.out.println(image);
        System.out.println("new Width: " + image.getWidth()); // should be 4

        System.out.println("--------------------");

        System.out.println("removing column at the end");
        image.removeColumn(3);
        System.out.println(image);
        System.out.println("new Width: " + image.getWidth()); // should be 3

        System.out.println("--------------------");
        System.out.println("Check for correct exception handling");
        try{
            image.removeColumn(3); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index equal to width");
        }

        try{
            image.removeColumn(-1); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index less than 0");
        }

        try{
            image.removeColumn(4); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index greater than width");
        }

        System.out.println("--------------------");

        System.out.println("removing row at the beginning");
        image.removeRow(0);
        System.out.println(image);
        System.out.println("new Height: " + image.getHeight()); // should be 6
        System.out.println("new Head node: " + image.getHead().getValue()); // should be 20

        System.out.println("--------------------");

        System.out.println("removing row in the middle");
        image.removeRow(2);
        System.out.println(image);
        System.out.println("new Height: " + image.getHeight()); // should be 5

        System.out.println("--------------------");

        System.out.println("removing row at the end");
        image.removeRow(4);
        System.out.println(image);
        System.out.println("new Height: " + image.getHeight()); // should be 4

        System.out.println("--------------------");
        System.out.println("Check for correct exception handling");
        try{
            image.removeRow(4); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index equal to height");
        }

        try{
            image.removeRow(-1); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index less than 0");
        }

        try{
            image.removeRow(5); // should throw an exception
        } catch (RuntimeException e) {
            System.out.println("Caught an exception for index greater than height");
        }

        System.out.println("--------------------");

        System.out.println("Check for Compress");
        System.out.println("Before compressing");
        image.insertRow(4, (short) 7);
        image.insertRow(5, (short) 7);
        image.insertRow(6, (short) 7);
        image.insertRow(0, (short) 1);
        image.insertRow(0, (short) 1);
        image.insertRow(0, (short) 1);
        image.insertRow(3, (short) 2);
        image.insertRow(3, (short) 2);
        image.insertRow(3, (short) 2);
        //image.addBorder();
        System.out.println(image);
        System.out.println("total number of nodes removed: " + image.compress());
        System.out.println("After compressing");
        System.out.println(image);

        System.out.println("--------------------");

        System.out.println("Check for adding border");
        System.out.println("Before adding border");
        System.out.println(image);
        System.out.println("height: " + image.getHeight());
        System.out.println("width: " + image.getWidth());
        image.addBorder();
        System.out.println("After adding border");
        System.out.println(image);
        System.out.println("checking for height: " + image.getHeight());
        System.out.println("checking for width: " + image.getWidth());

        System.out.println("--------------------");

        System.out.println("Check for removing border");
        System.out.println("Before removing border");
        System.out.println(image);
        System.out.println("height: " + image.getHeight());
        System.out.println("width: " + image.getWidth());
        image.removeBorder();
        System.out.println("After removing border");
        System.out.println(image);
        System.out.println("checking for height: " + image.getHeight());
        System.out.println("checking for width: " + image.getWidth());

        System.out.println("--------------------");

        System.out.println("Check for MaxFilter");
        System.out.println("Before MaxFilter");
        image.removeRow(5);
        System.out.println(image);
        System.out.println("After MaxFilter");
        System.out.println(image.maxFilter());*/
    }
}

