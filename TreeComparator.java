import java.util.ArrayList;
import java.util.HashMap;
/**
 * Generic binary tree, storing data of a parametric data in each node
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, Spring 2016, minor updates to testing
 * @author Tim Pierson, Winter 2018, added code to manually build tree in main
 *
 * Extension of BinaryTree for TwoForOne objects that is comparable
 *
 */
public class TreeComparator extends BinaryTree<TwoForOne> implements Comparable<TreeComparator>{

    /** creates instance with only data and right and left null
     *
     * @param data the node data
     */
    public TreeComparator(TwoForOne data){
    super(data);
        }

    /** creates instance with only data and right and left children
     *
     * @param data the node data
     * @param left the left child
     * @param right the right child
     */
    public TreeComparator(TwoForOne data, TreeComparator left, TreeComparator right){
        super(data, left, right);
    }

    /** compares the frequencies of the TwoForOne objects in the node
     *
     * @param o the object to be compared against
     * @return -1 if smalle, 0 if equal, 1 if larger
     */
    @Override
    public int compareTo(TreeComparator o) {
        return (this.data.getFrequency()).compareTo(o.data.getFrequency()); //returns integer result of compareTo
    }

    /** maps out the nodes of a tree
     *
     * @return the map containing the tree nodes as keys and bit codes as values
     */
    public HashMap<Character, ArrayList<Integer>> mapTree() {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>(); // map to hold characters and associated binary codes
        ArrayList<Integer> initCode = new ArrayList<>(); // array list holding the binary codes; is unique for each character

        addToMap(this, map, initCode); // calls the addToMap helper function
        return map; // return codeMap
    }

    /** helper function to recursively map out the tree and assign bit codes to relevant nodes
     *
     * @param data the node
     * @param map map being built
     * @param code associated bit code for node
     */
    private void addToMap(TreeComparator data, HashMap<Character, ArrayList<Integer>> map, ArrayList<Integer> code) {

        if (data.getData().getCharacter() != null) {
            map.put(data.getData().getCharacter(), code); // adds the character and code to the map
        }
        ArrayList<Integer> code1, code0; // code0 is for left and code1 is for right
        if (data.hasLeft()){
            code0 = new ArrayList<>(code);
            code0.add(0); // adds a zero to incoming
            addToMap((TreeComparator) data.getLeft(), map, code0);
            }

        if (data.hasRight() && data.hasLeft()){
            code1 = new ArrayList<>(code);
            code1.add(1); // adds a one to incoming code
            addToMap((TreeComparator) data.getRight(), map, code1);
            }
        }
}
