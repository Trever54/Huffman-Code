// BinaryTree.java
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/**
 * A simple binary tree whose root holds a non-null value.
 * <p>
 * The BinaryTree&lt;V&gt; datatype is defined recursively as
 * being comprised of a value of type V,
 * a reference to a left child of type BinaryTree&lt;V&gt;, and
 * a reference to a right child of type BinaryTree&lt;V&gt;.
 * As this datatype represents a binary tree,
 * there must be no duplicate references nor any references
 * to the root.
 * </p>
 * <p>
 * The empty tree is distinguished by the following properties:<br>
 * <code>numberOfNodes() == 0</code>,<br>
 * <code>isEmpty() == true</code>, and<br>
 * <code>height() == -1</code>.
 * </p>
 * <p>
 * Two binary trees are equal if and only if<br>
 * (1) they have the same structure and<br>
 * (2) respective elements stored at respective nodes
 * in both trees are equal.<br>
 * <small>[Walicki, M., <i>Introduction to Mathematical Logic,</i>
 *        World Scientific, 2011.]</small>
 * </p>
 * Terminology:<ul>
 * <li><em>ancestor</em> = a parent, grandparent,
 *        great-grandparent, etc.</li>
 * <li><em>descendant</em> = a child, grandchild,
 *        great-grandchild, etc.</li>
 * <li><em>height</em> = the length of the longest path to a leaf</li>
 * <li><em>internal</em> = a tree with at least one child</li>
 * <li><em>leaf</em> = a tree with no children</li>
 * <li><em>root</em> = any BinaryTree object</li>
 * <li><em>tree</em> = a BinaryTree with all its descendants</li>
 * </ul>
 * @param <V> the type of value stored in nodes of the tree
 * @author Dr. Jody Paul
 * @version CS390K 2015 (3)
 */
public class BinaryTree<V>
    implements Iterable<BinaryTree<V>>, java.io.Serializable {

    /** Default state save/restore file name. */
    public static final String SERIAL_FILENAME = "bt.ser";

    /**
     * Serialization version indicator used to determine
     *    if a file is compatible with this class.
     */
    private static final long serialVersionUID = 201510081L;

    /**
     * Value of the root of this tree;
     * null if and only if empty tree.
     * @serial
     */
    private V rootValue;

    /**
     * Left child of the root of this tree.
     * <code>null</code> used as indicator of missing child.
     * @serial
     */
    private BinaryTree<V> leftChild;

    /**
     * Right child of the root of this tree.
     * <code>null</code> used as indicator of missing child.
     * @serial
     */
    private BinaryTree<V> rightChild;

    /**
     * Constructs an empty tree.
     */
    public BinaryTree() {
        this.rootValue = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Constructs a tree with no children
     * whose value is specified by the parameter.
     * @param rootvalue the value stored at the root of the tree
     * @throws IllegalArgumentException if parameter is null
     */
    public BinaryTree(final V rootvalue)
            throws IllegalArgumentException {
        this(rootvalue, null, null);
    }

    /**
     * Constructs a tree with specified value,
     *   left child, and right child.
     * @param rootvalue the value stored at the root of the tree
     * @param leftchild the left child of the root;
     *        <code>null</code> if no such child
     * @param rightchild the right child of the root;
     *        <code>null</code> if no such child
     * @throws IllegalArgumentException
     *         if <code>rootvalue</code> parameter is null
     */
    public BinaryTree(final V rootvalue,
                            final BinaryTree<V> leftchild,
                            final BinaryTree<V> rightchild)
        throws IllegalArgumentException {
        if (rootvalue == null) {
            throw new IllegalArgumentException();
        }
        this.rootValue = rootvalue;
        this.leftChild = leftchild;
        this.rightChild = rightchild;
    }

    /**
     * Empty tree predicate.
     * @return <code>true</code> if this is an empty tree;
     *         <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return (this.rootValue == null);
    }

    /**
     * Returns the number of nodes in this tree;
     *   0 if empty tree.
     * @return the number of nodes
     */
    public int numberOfNodes() {
        int numNodes = 0;
        if (this.rootValue != null) {
            numNodes++;
            if (this.getLeftChild() != null) {
                numNodes += this.getLeftChild().numberOfNodes();
            }
            if (this.getRightChild() != null) {
                numNodes += this.getRightChild().numberOfNodes();
            }
        }
        return numNodes;
    }

    /**
     * Leaf predicate.
     * @return <code>true</code> if this is a leaf;
     *         <code>false</code> otherwise.
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public boolean isLeaf() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (numberOfNodes() < 1) {
            return false;
        }
        return (numberOfNodes() == 1);
    }

    /**
     * Returns the value of the root of this tree.
     * @return the value of the root
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public V getValue() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.rootValue;
    }

    /**
     * Returns the left child of this tree.
     * @return the left child; null if no such child
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public BinaryTree<V> getLeftChild() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.leftChild;
    }

    /**
     * Returns the right child of this tree.
     * @return the right child; null if no such child
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public BinaryTree<V> getRightChild() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return this.rightChild;
    }

    /**
     * Modifies the value of the root of this tree.
     * @param value the new value for the root
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public void setValue(final V value) throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.rootValue = value;
    }

    /**
     * Replaces the left child of the root of this tree.
     * @param child the new left child for this tree
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public void setLeftChild(final BinaryTree<V> child)
        throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.leftChild = child;
    }

    /**
     * Replaces the right child of the root of this tree.
     * @param child the new right child for this tree
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public void setRightChild(final BinaryTree<V> child)
        throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        this.rightChild = child;
    }

    /**
     * Determines the number of leaves of this tree.
     * @return the number of leaves
     * @throws java.lang.NullPointerException if this tree is empty
     */
    public int numberOfLeaves() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        if (this.leftChild == null && this.rightChild == null) {
            return 1;
        }
        int numLeaves = 0;
        if (this.leftChild != null) {
            if (this.leftChild.isEmpty()) {
                numLeaves++;
            } else {
                numLeaves += this.leftChild.numberOfLeaves();
            }
        }
        if (this.rightChild != null) {
            if (this.rightChild.isEmpty()) {
                numLeaves++;
            } else {
                numLeaves += this.rightChild.numberOfLeaves();
            }
        }
        return numLeaves;
    }

    /**
     * Determines the height of this tree;
     *   -1 if empty tree.
     * @return the height of this tree, -1 if empty
     */
    public final int height() {
        if (isEmpty()) {
            return -1;
        }
        int maxChildHeight = -1;
        if (this.leftChild != null) {
            maxChildHeight = this.leftChild.height();
        }
        if (this.rightChild != null) {
            maxChildHeight = Math.max(maxChildHeight, this.rightChild.height());
        }
        return 1 + maxChildHeight;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare
     * @return true if and only if
     *  <ul>
     *    <li>both are non-null</li>
     *    <li>both have the same number of subtrees</li>
     *    <li>both are the same height</li>
     *    <li>if non-empty, both have equal root values</li>
     *    <li>if left-children are present, they are equal to each other</li>
     *    <li>if right-children are present, they are equal to each other</li>
     *  </ul>
     * @see #hashCode()
     */
    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        @SuppressWarnings("unchecked") // Have already checked.
        BinaryTree<V> param = (BinaryTree<V>) obj;
        return compareTrees(this, param);
    }

    /**
     * Returns a hash code value for this tree.
     * @return a hash code value for this tree
     * @see #equals(Object o)
     */
    @Override
    public int hashCode() {
        int mycode = null == this.rootValue ? 1 : this.rootValue.hashCode();
        mycode += null == this.leftChild ? 2 : this.leftChild.hashCode();
        mycode += null == this.rightChild ? 2 : this.rightChild.hashCode();
        return mycode;
    }

    /**
     * Returns a list of values in the order in which
     *   the nodes would be visited using preorder traversal.
     * @return values of all nodes in preorder
     */
    public List<V> preorderValues() {
        List<V> preorderV = new ArrayList<V>();
        if (this.numberOfNodes() != 0) {
            preorderV.add(this.getValue());
            if (this.getLeftChild() != null) {
                preorderV.addAll(this.getLeftChild().preorderValues());
            }
            if (this.getRightChild() != null) {
                preorderV.addAll(this.getRightChild().preorderValues());
            }
        }
        return preorderV;
    }

    /**
     * Returns a list of values in the order in which
     *   the nodes would be visited using inorder traversal.
     * @return values of all nodes in inorder
     */
    public List<V> inorderValues() {
        List<V> inorderV = new ArrayList<V>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                inorderV.addAll(this.getLeftChild().inorderValues());
            }
            inorderV.add(this.getValue());
            if (this.getRightChild() != null) {
                inorderV.addAll(this.getRightChild().inorderValues());
            }
        }
        return inorderV;
    }

    /**
     * Returns a list of values in the order in which
     *   the nodes would be visited using postorder traversal.
     * @return values of all nodes in postorder
     */
    public List<V> postorderValues() {
        List<V> postorderV = new ArrayList<V>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                postorderV.addAll(this.getLeftChild().postorderValues());
            }
            if (this.getRightChild() != null) {
                postorderV.addAll(this.getRightChild().postorderValues());
            }
            postorderV.add(this.getValue());
        }
        return postorderV;
    }

    /**
     * Returns a list of subtrees in the order in which
     *   they would be visited using preorder traversal.
     * @return all subtrees in preorder
     */
    public List<BinaryTree<V>> preorderSubtrees() {
        List<BinaryTree<V>> preorderList = new ArrayList<BinaryTree<V>>();
        if (this.numberOfNodes() != 0) {
            preorderList.add(this);
            if (this.getLeftChild() != null) {
                preorderList.addAll(this.getLeftChild().preorderSubtrees());
            }
            if (this.getRightChild() != null) {
                preorderList.addAll(this.getRightChild().preorderSubtrees());
            }
        }
        return preorderList;
    }

    /**
     * Returns a list of subtrees in the order in which
     *   they would be visited using inorder traversal.
     * @return all subtrees in inorder
     */
    public List<BinaryTree<V>> inorderSubtrees() {
        List<BinaryTree<V>> inorderList = new ArrayList<BinaryTree<V>>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                inorderList.addAll(this.getLeftChild().inorderSubtrees());
            }
            inorderList.add(this);
            if (this.getRightChild() != null) {
                inorderList.addAll(this.getRightChild().inorderSubtrees());
            }
        }
        return inorderList;
    }

    /**
     * Returns a list of subtrees in the order in which
     *   they would be visited using postorder traversal.
     * @return all subtrees in postorder
     */
    public List<BinaryTree<V>> postorderSubtrees() {
        List<BinaryTree<V>> postorderList
            = new ArrayList<BinaryTree<V>>();
        if (this.numberOfNodes() != 0) {
            if (this.getLeftChild() != null) {
                postorderList.addAll(this.getLeftChild().postorderSubtrees());
            }
            if (this.getRightChild() != null) {
                postorderList.addAll(this.getRightChild().postorderSubtrees());
            }
            postorderList.add(this);
        }
        return postorderList;
    }

    /**
     * Converts a list of trees into a list of the
     * root values of those trees.
     * @param listOfTrees the list of trees from which to extract values
     * @param <V> the type of values stored in nodes
     * @return all root values in the order they appear in the parameter
     */
    public static <V> List<V> values(
                    final List<BinaryTree<V>> listOfTrees) {
        List<V> listOfValues = new ArrayList<V>();
        for (BinaryTree<V> bt : listOfTrees) {
            if (bt == null) {
                listOfValues.add(null);
            } else if (!bt.isEmpty()) {
                listOfValues.add(bt.getValue());
            }
        }
        return listOfValues;
    }

    /**
     * Returns an iterator over the subtrees (nodes) of this tree.
     * @return an iterator over subtrees of this tree
     */
    @Override
    public java.util.Iterator<BinaryTree<V>> iterator() {
        return this.postorderSubtrees().iterator();
    }

    /**
     * Renders tree as a string.
     * @return string rendering of this object
     */
    @Override
    public String toString() {
        return indentedToString(0);
    }

    /**
     * Provides indented multi-line rendering of tree.
     * @param level the depth of this tree in overall rendering
     * @return rendering of tree using multiple lines and indentation
     */
    private String indentedToString(final int level) {
        String indent =
            (level <= 0)
            ? ""
            : String.format("%" + (level * 2) + "s", "");
        if (isEmpty()) {
            return indent + "X_";
        }
        String rendering = indent;
        rendering += "[";
        rendering += getValue() + ": \n";
        if (this.leftChild != null) {
            rendering += this.leftChild.indentedToString(level + 1);
        } else {
            rendering += indent + " _";
        }
        rendering += ",\n";
        if (this.rightChild != null) {
            rendering += this.rightChild.indentedToString(level + 1);
        } else {
            rendering += indent + " _";
        }
        rendering += "]";
        return rendering;
    }

    /**
     * Saves this tree to a file.
     * @param filename the name of the file in which to save this tree;
     *                 if null, uses default file name
     * @return <code>true</code> if successful save;
     *         <code>false</code> otherwise
     * @throws java.io.IOException if unexpected IO error
     */
    public final boolean save(final String filename)
    throws java.io.IOException {
        boolean success = true;
        String treeFileName = filename;
        if (treeFileName == null) {
            treeFileName = BinaryTree.SERIAL_FILENAME;
        }
        // Serialize the tree.
        try {
            OutputStream file = new FileOutputStream(treeFileName);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try {
                output.writeObject(this);
            } finally { output.close(); }
        } catch (IOException ex) {
            System.err.println("Unsuccessful save. " + ex);
            throw ex;
        }

        // Attempt to deserialize the graph as verification.
        try {
            InputStream file = new FileInputStream(treeFileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                @SuppressWarnings("unchecked") // Accommodate type erasure.
                BinaryTree<V> restored = (BinaryTree<V>) input.readObject();
                // Use toString for quick check.
                if (!this.toString().equals(restored.toString())) {
                    success = false;
                }
                // Use compareTrees predicate for deeper check.
                if (!compareTrees(this, restored)) {
                    success = false;
                }
            } finally { input.close(); }
        } catch (ClassNotFoundException ex) {
            // System.err.println("save: restore-check Class not found. " + ex);
            success = false;
        } catch (IOException ex) {
            // System.err.println("save: restore-check exception.  " + ex);
            success = false;
        }
        return success;
    }

    /**
     * Restores this tree from a file.
     * <br><em>Postconditions:</em>
     * <blockquote>If successful, previous contents of this tree have
     * been replaced by the contents of the file.
     * If unsuccessful, content of the tree is unchanged.</blockquote>
     * @param filename the name of the file from which to restore this tree;
     *                 if null, uses default file name
     * @return <code>true</code> if successful restore;
     *         <code>false</code> otherwise
     * @throws java.io.IOException if unexpected IO error
     */
    public final boolean restore(final String filename) throws
    java.io.IOException {
        boolean success = false;
        String treeFileName = filename;
        if (treeFileName == null) {
            treeFileName = BinaryTree.SERIAL_FILENAME;
        }
        BinaryTree<V> restored = null;
        try {
            InputStream file = new FileInputStream(treeFileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try {
                @SuppressWarnings("unchecked") // Accommodate type erasure.
                BinaryTree<V> retrieved = (BinaryTree<V>) input.readObject();
                restored = retrieved;
            } finally {
                input.close();
                success = true;
            }
        } catch (ClassNotFoundException ex) {
            // ClassNotFoundException is common.
            success = false;
        } catch (FileNotFoundException ex) {
            // FileNoteFoundException is common.
            success = false;
        } catch (IOException ex) {
            // Throw other IOExceptions as unexpected.
            throw ex;
        }
        if (restored == null) {
            // Invalid; BinaryTree<V>cannot be null.
            success = false;
        } else {
            this.rootValue = restored.rootValue;
            this.leftChild = restored.leftChild;
            this.rightChild = restored.rightChild;
        }
        return success;
    }

    /**
     * Utility that compares two trees for shape and contents.
     * This private method behaves like an equals predicate
     * except that it works only for BinaryTree objects.
     * @param bt1 first tree for comparison
     * @param bt2 second tree for comparison
     * @return true if and only if both are non-null,
     *      have the same number of nodes and same height,
     *      and if non-empty then also have
     *      equal root values, and if present
     *      both the left children and right children
     *      also return true to this predicate
     */
    private boolean compareTrees(final BinaryTree<V> bt1,
                                 final BinaryTree<V> bt2) {
        if (bt1 == null || bt2 == null) {
            return false;
        }
        if (bt1.isEmpty()) {
            return bt2.isEmpty();
        }
        if (bt1.height() != bt2.height()) {
            return false;
        }
        if (!bt1.getValue().equals(bt2.getValue())) {
            return false;
        }
        if (bt1.getLeftChild() == null) {
            if (bt2.getLeftChild() != null) {
                return false;
            }
        } else if (bt2.getLeftChild() == null) {
            return false;
        }
        if (bt1.getLeftChild() != null
            && !compareTrees(bt1.getLeftChild(), bt2.getLeftChild())) {
            return false;
        }
        if (bt1.getRightChild() == null) {
            if (bt2.getRightChild() != null) {
                return false;
            }
        } else if (bt2.getRightChild() == null) {
            return false;
        }
        if (bt1.getRightChild() != null
            && !compareTrees(bt1.getRightChild(), bt2.getRightChild())) {
            return false;
        }
        return true;
    }
}