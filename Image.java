import java.util.Iterator;

/**
 * This class represents a two-dimensional doubly-linked list of nodes.
 *
 * @param <T> the type of the value stored in each node.
 */
public class Image<T extends Comparable<T>> implements Iterable<Node<T>> {
    /**
     * The head of the doubly-linked list.
     */
    private Node<T> head;
    /**
     * The width of the image.
     */
    private int width;
    /**
     * The height of the image.
     */
    private int height;

    /**
     * Constructs an image with the given width and height.
     *
     * @param width  the width of the image.
     * @param height the height of the image.
     */

    public Image(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new RuntimeException();
        }
        this.width = width;
        this.height = height;
        this.head = null;
        Node<T> temp1 = null;
        Node<T> temp3 = null;
        for (int i = 0; i < getHeight(); i++) {
            Node<T> temp2 = null;
            for (int j = 0; j < getWidth(); j++) {
                Node<T> newNode = new Node<>();
                if (j == 0) {
                    temp3 = newNode;
                }
                if (i == 0) {
                    if (j == 0) {
                        this.head = newNode;
                        temp1 = temp3;
                    } else {
                        temp2.setRight(newNode);
                        newNode.setLeft(temp2);
                    }
                    temp2 = newNode;
                } else {
                    temp1.setDown(newNode);
                    newNode.setUp(temp1);
                    if (j > 0) {
                        temp2.setRight(newNode);
                        newNode.setLeft(temp2);
                    }
                    temp2 = newNode;
                    if (j == getWidth() - 1) {
                        temp1 = temp3;
                    } else {
                        temp1 = temp1.getRight();
                    }

                }

            }
        }
    }

    /**
     * Returns the width of the image.
     *
     * @return the width of the image.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the image.
     *
     * @return the height of the image.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the head of the image.
     *
     * @return the head of the image.
     */
    public Node<T> getHead() {
        return this.head;
    }

    /**
     * Inserts a column at the given index.
     *
     * @param index the index to insert the column at.
     * @param value the value to insert.
     */
    public void insertRow(int index, T value) {
        if (index < 0 || index > this.height || head == null) {
            throw new RuntimeException();
        }

        Node<T> temp1 = this.head;
        Node<T> temp2 = this.head;
        for (int i = 0; i < index; i++) {
            if (temp2.getDown() != null) {
                temp2 = temp2.getDown();
            }
        }
        if (temp2.getUp() != null) {
            temp1 = temp2.getUp();
        }
        //creating new row
        Node<T> tempCreatingRow = null;
        Node<T> tempCreatingRow2 = null;
        for (int i = 0; i < getWidth(); i++) {
            Node<T> newNode = new Node<T>(value);
            if (i == 0) {
                tempCreatingRow = newNode;
                tempCreatingRow2 = newNode;
            } else {
                tempCreatingRow.setRight(newNode);
                newNode.setLeft(tempCreatingRow);
                tempCreatingRow = newNode;
            }
        }
        for (int i = 0; i < getWidth(); i++) {
            if (index == 0) { // check for adding at the beginning
                if (tempCreatingRow2 != null) {
                    tempCreatingRow2.setDown(temp1);
                }
                temp1.setUp(tempCreatingRow2);
                if (i == 0) {
                    this.head = tempCreatingRow2;
                }
            } else if (index == getHeight()) { // check for adding at the end
                if (tempCreatingRow2 != null) {
                    tempCreatingRow2.setUp(temp2);
                }
                temp2.setDown(tempCreatingRow2);

            } else { // check for adding at the middle
                temp1.setDown(tempCreatingRow2);
                if (tempCreatingRow2 != null) {
                    tempCreatingRow2.setUp(temp1);
                }

                temp2.setUp(tempCreatingRow2);
                if (tempCreatingRow2 != null) {
                    tempCreatingRow2.setDown(temp2);
                }

            }
            if (tempCreatingRow2 != null) {
                tempCreatingRow2 = tempCreatingRow2.getRight();
            }
            temp1 = temp1.getRight();
            temp2 = temp2.getRight();
        }
        height++;
    }

    /**
     * removes a column at the given index.
     *
     * @param index the index to remove the column at.
     */
    public void removeColumn(int index) {
        if (index < 0 || index >= this.width || head == null) {
            throw new RuntimeException();
        }
        Node<T> temp1 = this.head;
        Node<T> temp2 = this.head;
        for (int i = 0; i < index; i++) {
            if (temp2.getRight() != null) {
                temp2 = temp2.getRight();
            }
        }
        if (temp2.getLeft() != null) {
            temp1 = temp2.getLeft();
        }

        if (index == 0) {
            temp2 = temp2.getRight();
        }

        for (int i = 0; i < height; i++) {
            if (index == 0) { // check for removing at the beginning
                temp1.setRight(null);
                temp2.setLeft(null);
                if (i == 0) {
                    this.head = temp2;
                }
            } else if (index == getWidth() - 1) { // check for removing at the end
                temp1.setRight(null);
                temp2.setLeft(null);

            } else { // check for removing at the middle
                temp1.setRight(temp2.getRight());
                if (temp2.getRight() != null) {
                    temp2.getRight().setLeft(temp1);
                }
                temp2.setLeft(null);
                temp2.setRight(null);

            }
            temp1 = temp1.getDown();
            temp2 = temp2.getDown();
        }
        width--;
    }

    /**
     * Helper method that removes a row at the given index.
     *
     * @param index the index to remove the row at.
     */
    private void removeRow(int index) {
        if (index < 0 || index >= this.height || head == null) {
            throw new RuntimeException();
        }

        Node<T> temp1 = this.head;
        Node<T> temp2 = this.head;

        for (int i = 0; i < index; i++) {
            if (temp2.getDown() != null) {
                temp2 = temp2.getDown();
            }
        }

        if (temp2.getUp() != null) {
            temp1 = temp2.getUp();
        }

        if (index == 0) {
            temp2 = temp2.getDown();
        }

        for (int i = 0; i < width; i++) {
            if (index == 0) { // Check for removing at the beginning
                temp1.setDown(null);
                temp2.setUp(null);
                if (i == 0) {
                    this.head = temp2;
                }
            } else if (index == getHeight() - 1) { // Check for removing at the end
                temp1.setDown(null);
                temp2.setUp(null);
            } else { // Check for removing at the middle
                temp1.setDown(temp2.getDown());
                if (temp2.getDown() != null) {
                    temp2.getDown().setUp(temp1);
                }
                temp2.setUp(null);
                temp2.setDown(null);
            }

            temp1 = temp1.getRight();
            temp2 = temp2.getRight();
        }
        height--;
    }

    /**
     * Compresses the image by removing adjacent rows and columns with the same value.
     *
     * @return the number of nodes removed.
     */
    public int compress() {
        //checking adjacent column
        int totalCount = 0;
        int count = 0;
        Node<T> temp1 = head;
        Node<T> temp2 = temp1.getRight();
        Node<T> check1 = head;
        Node<T> check2 = check1.getRight();
        for (int i = 0; i < getWidth(); i++) {
            count = 0;
            for (int j = 0; j < getHeight(); j++) {
                if (check1 != null && check1.getValue() == (check2.getValue())) {
                    count++;
                }
                if (check1 != null && (check1.getDown() != null || check2.getDown() != null)) {
                    check1 = check1.getDown();
                    check2 = check2.getDown();
                }

            }

            temp1 = temp1.getRight();
            check1 = temp1;
            temp2 = temp2.getRight();
            check2 = temp2;

            if (count == getHeight()) {
                removeColumn(i);
                //temp1 = temp2.getLeft();
                check1 = temp1;
                check2 = temp2;
                if (i != 0) {
                    i--;
                }
                totalCount += count;
            }
            if (temp2 == null) {
                break;
            }
        }

        //checking adjacent row
        temp1 = head;
        temp2 = temp1.getDown();
        check1 = head;
        check2 = check1.getDown();
        for (int i = 0; i < getHeight(); i++) {
            count = 0;
            for (int j = 0; j < getWidth(); j++) {
                if (check1 != null && check1.getValue() == check2.getValue()) {
                    count++;
                }
                if (check1 != null && (check1.getRight() != null || check2.getRight() != null)) {
                    check1 = check1.getRight();
                    check2 = check2.getRight();
                }
            }

            temp1 = temp1.getDown();
            check1 = temp1;
            temp2 = temp2.getDown();
            check2 = temp2;

            if (count == getWidth()) {
                removeRow(i);
                //temp1 = temp2.getUp();
                check1 = temp1;
                check2 = temp2;
                i--;
                totalCount += count;
            }
            if (temp2 == null) {
                break;
            }
        }
        return totalCount;
    }

    /**
     * Adds a border around the image.
     */
    public void addBorder() {
        //adding border to the top
        Node<T> temp1 = head;
        Node<T> temp2 = head;
        for (int i = 0; i < getWidth(); i++) {
            Node<T> newNode = new Node<T>(temp2.getValue());
            if (i == 0) {
                this.head = newNode;
            }
            if (i > 0) {
                temp1.setRight(newNode);
                newNode.setLeft(temp1);
            }
            temp1 = newNode;
            temp1.setDown(temp2);
            temp2.setUp(temp1);
            if (temp2.getRight() != null) {
                temp2 = temp2.getRight();
            } else {
                break;
            }
        }
        height++;

        //adding border to the bottom
        temp1 = head;
        while (temp1.getDown() != null) {
            temp1 = temp1.getDown();
        }
        temp2 = temp1;
        for (int i = 0; i < getWidth(); i++) {
            Node<T> newNode = new Node<T>(temp2.getValue());
            if (i > 0) {
                temp1.setRight(newNode);
                newNode.setLeft(temp1);
            }
            newNode.setUp(temp2);
            temp2.setDown(newNode);
            temp1 = newNode;
            if (temp2.getRight() != null) {
                temp2 = temp2.getRight();
            } else {
                break;
            }
        }
        height++;

        //adding border to the left
        temp1 = head;
        temp2 = head;
        for (int i = 0; i < getHeight(); i++) {
            Node<T> newNode = new Node<T>(temp2.getValue());
            if (i == 0) {
                this.head = newNode;
            }
            if (i > 0) {
                temp1.setDown(newNode);
                newNode.setUp(temp1);
            }
            newNode.setRight(temp2);
            temp2.setLeft(newNode);
            temp1 = newNode;

            if (temp2.getDown() != null) {
                temp2 = temp2.getDown();
            } else {
                break;
            }
        }
        width++;

        //adding border to the right
        temp1 = head;
        while (temp1.getRight() != null) {
            temp1 = temp1.getRight();
        }
        temp2 = temp1;
        for (int i = 0; i < getHeight(); i++) {
            Node<T> newNode = new Node<T>(temp2.getValue());
            if (i > 0) {
                temp1.setDown(newNode);
                newNode.setUp(temp1);
            }
            newNode.setLeft(temp2);
            temp2.setRight(newNode);
            temp1 = newNode;

            if (temp2.getDown() != null) {
                temp2 = temp2.getDown();
            } else {
                break;
            }
        }
        width++;
    }

    /**
     * Removes the border around the image.
     */
    public void removeBorder() {
        removeColumn(0);
        removeColumn(getWidth() - 1);
        removeRow(0);
        removeRow(getHeight() - 1);
    }

    /**
     * Applies max filter to the image.
     *
     * @return the new image.
     */
    public Image<T> maxFilter() {
        Node<T> temp1 = head;
        Image<T> newImage = new Image<T>(getWidth(), getHeight());
        Node<T> imageTemp1 = newImage.getHead();
        for (int i = 0; i < getHeight(); i++) {
            Node<T> temp2 = temp1;
            Node<T> imageTemp2 = imageTemp1;
            for (int j = 0; j < getWidth(); j++) {
                imageTemp2.setValue(helperForMaxFilter(temp2));
                if (temp2.getRight() != null) {
                    temp2 = temp2.getRight();
                    imageTemp2 = imageTemp2.getRight();
                }
            }
            if (temp1.getDown() != null) {
                temp1 = temp1.getDown();
                imageTemp1 = imageTemp1.getDown();
            }
        }
        return newImage;
    }

    /**
     * Helper method for maxFilter.
     *
     * @param node the node to apply max filter to.
     * @return the new value of the node.
     */
    private T helperForMaxFilter(Node<T> node) {
        if (node == null) {
            return null;
        }
        T max = node.getValue();

        Node<T> up = node.getUp();
        Node<T> down = node.getDown();
        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();
        Node<T> upLeft = node.getUp() != null ? node.getUp().getLeft() : null;
        Node<T> upRight = node.getUp() != null ? node.getUp().getRight() : null;
        Node<T> downLeft = node.getDown() != null ? node.getDown().getLeft() : null;
        Node<T> downRight = node.getDown() != null ? node.getDown().getRight() : null;

        Node<T>[] arr = new Node[]{up, down, left, right, upLeft, upRight, downLeft, downRight};
        for (Node<T> n : arr) {
            if (n != null && n.getValue().compareTo(max) > 0) {
                max = n.getValue();
            }
        }
        return max;
    }

    /**
     * Iterator for the image with default direction.
     *
     * @return the iterator.
     */
    public Iterator<Node<T>> iterator() {
        return new ImageIterator<T>(head);
    }

    /**
     * Iterator for the image with given direction.
     *
     * @param direction the direction to iterate in.
     * @return the iterator.
     */
    public Iterator<Node<T>> iterator(Direction direction) {
        return new ImageIterator<T>(head, direction);
    }

}
