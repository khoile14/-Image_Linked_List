import java.util.Iterator;

/**
 * This class implements the Iterator interface and is used to iterate over the nodes of an Image object.
 *
 * @param <T> the type of the data stored in the node.
 */
public class ImageIterator<T extends Comparable<T>> implements Iterator<Node<T>> {

    /**
     * The current node.
     */
    private Node<T> node;
    /**
     * The direction of the traversal.
     */
    private final Direction direction;
    /**
     * The temporary node.
     */
    private Node<T> temp;

    /**
     * The constructor with a head parameter and default horizontal transversal.
     *
     * @param head the head of the image.
     */
    public ImageIterator(Node<T> head) {
        this.direction = Direction.HORIZONTAL;
        this.temp = head;
        this.node = null;
    }

    /**
     * The constructor with a head and direction parameter.
     *
     * @param node      the head of the image.
     * @param direction the direction of the traversal.
     */

    public ImageIterator(Node<T> node, Direction direction) {
        this.direction = direction;
        this.node = null;
        this.temp = node;
    }

    /**
     * The hasNext method checks if there is a next node.
     *
     * @return true if there is a next node, false otherwise.
     */

    @Override
    public boolean hasNext() {
        if (this.direction == Direction.HORIZONTAL) {
            if (this.node == null && temp != null) {
                return true;
            } else if (this.node.getRight() != null || this.node.getDown() != null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (this.node == null && temp != null) {
                return true;
            } else if (this.node.getDown() != null || this.node.getRight() != null) {
                return true;
            } else {
                return false;
            }

        }
    }

    /**
     * The next method returns the next node.
     *
     * @return the next node.
     */
    @Override
    public Node<T> next() {
        if (!this.hasNext()) {
            throw new UnsupportedOperationException();
        }
        if (this.direction == Direction.HORIZONTAL) {
            if (this.node == null) {
                this.node = temp;
            } else if (this.node.getRight() == null && temp.getDown() != null) {
                temp = temp.getDown();
                this.node = temp;

            } else {
                this.node = this.node.getRight();
            }

            return this.node;

        } else {
            if (this.node == null) {
                this.node = temp;
            } else if (this.node.getDown() == null && temp.getRight() != null) {
                temp = temp.getRight();
                this.node = temp;

            } else {
                this.node = this.node.getDown();
            }

            return this.node;
        }
    }
}
