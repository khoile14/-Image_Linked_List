/**
 * Creating a Node class that implements the Comparable interface.
 *
 * @param <T> the type of the data stored in the node.
 */
public final class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    /**
     * The data stored in the node.
     */
    private T data;
    /**
     * The left pointers.
     */
    private Node<T> left;
    /**
     * The right pointers.
     */
    private Node<T> right;
    /**
     * The up pointers.
     */
    private Node<T> up;
    /**
     * The down pointers.
     */
    private Node<T> down;

    /**
     * The default constructor.
     */
    public Node() {
        this.data = null;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    }

    /**
     * The constructor with a parameter.
     *
     * @param value the value to be stored in the node.
     */
    public Node(T value) {
        this.data = value;
        this.left = null;
        this.right = null;
        this.up = null;
        this.down = null;
    }

    /**
     * The getter method for the data.
     *
     * @return the data stored in the node.
     */
    public T getValue() {
        return this.data;
    }

    /**
     * The setter method for the data.
     *
     * @param value the value to be stored in the node.
     */
    public void setValue(T value) {
        this.data = value;
    }

    /**
     * The getter method for the up pointer.
     *
     * @return the up pointer.
     */
    public Node<T> getUp() {
        return this.up;
    }

    /**
     * The setter method for the up pointer.
     *
     * @param p the node to be set as the up pointer.
     */
    public void setUp(Node<T> p) {
        this.up = p;
    }

    /**
     * The getter method for the down pointer.
     *
     * @return the down pointer.
     */
    public Node<T> getDown() {
        return this.down;
    }

    /**
     * The setter method for the down pointer.
     *
     * @param p the node to be set as the down pointer.
     */
    public void setDown(Node<T> p) {
        this.down = p;
    }

    /**
     * The getter method for the left pointer.
     *
     * @return the left pointer.
     */
    public Node<T> getLeft() {
        return this.left;
    }

    /**
     * The setter method for the left pointer.
     *
     * @param p the node to be set as the left pointer.
     */
    public void setLeft(Node<T> p) {
        this.left = p;
    }

    /**
     * The getter method for the right pointer.
     *
     * @return the right pointer.
     */
    public Node<T> getRight() {
        return this.right;
    }

    /**
     * The setter method for the right pointer.
     *
     * @param p the node to be set as the right pointer.
     */
    public void setRight(Node<T> p) {
        this.right = p;
    }

    /**
     * The compareTo method required by the interface.
     *
     * @param o the object to be compared.
     * @return the result of the comparison.
     */
    @Override
    public int compareTo(Node<T> o) {
        return this.data.compareTo(o.data);
    }
}
