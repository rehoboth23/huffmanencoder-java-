import java.io.*;
import java.util.*;

/** HuffmanEncoder class uses ReadIntoMap to map a file and creates a queue of TwoForOne TreeComparator objects using the map
 * using the queue a code tree of the characters is created with the lee frequent characters deeper in the tree
 * Using the code, another map mapping the characters to bit codes is created
 *
 */
public class HuffmanEncoder {
    private SortedArrayListMinPriorityQueue<TreeComparator> queue;
    private TreeComparator codeTree;
    private HashMap<Character, ArrayList<Integer>> codeMap;
    private String inputFile;

    /** creates an instance of Huffman encoder
     * Maps the file using ReadIntoMap class
     * Creates a queues of TwoForOne TreeComparator objects and queues them
     *
     * @param file file to be mapped
     * @throws Exception FileNotFound Exception
     */
    public HuffmanEncoder(String file) throws Exception {
        this.inputFile = file; // holds the file name of the original input file

        ReadIntoMap readFile = new ReadIntoMap(file); // read into map object that reads and maps the text file
        readFile.loadFile();

        HashMap<Character, Integer> map = readFile.getMap(); // gets the map of characters anf stores it in map

        this.queue = new SortedArrayListMinPriorityQueue<>(); // instantiates a minimum priority queue

        for (Character key: map.keySet()){
            // creates TreeComparators of TwoForOne objects and adds them to the queue
            this.queue.insert(new TreeComparator(new TwoForOne(key, map.get(key))));
        }
    }

    /** constructs the code tree
     * extracts the two minimum objects in the queue and merges them into a new tree then queues them
     */
    public void constructTree(){
        while (this.queue.getList().size() > 1){
            // gets two minimum member of the queue
            TreeComparator tree1 = this.queue.extractMin();
            TreeComparator tree2 = this.queue.extractMin();

            // creates a new TreeComparator with tree1 and tree2 and children and data as sum of their frequencies
            TwoForOne newData = new TwoForOne(tree1.data.getFrequency() + tree2.data.getFrequency());
            TreeComparator newTree = new TreeComparator(newData, tree1, tree2);

            // inserts the new tree comparator into the queue
            this.queue.insert(newTree);
        }

        // gets the final tree from the queue, store it in codeTree
        codeTree = this.queue.extractMin();
    }

    /** maps the tree and assigns binary code to the characters in the tree
     * calls on mapTree method from the TreeComparator class
     *
     */
    public void buildCodeMap(){
        codeMap =  codeTree.mapTree(); // Maps out the tree and saves it to codeMap
    }

    /** creates the bitoutput file
     *
     * @param pathName path of file to write bitoutput into
     * @throws IOException NoFileException
     */
    public void createBitOutput(String pathName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(this.inputFile)); // file to be converted into binary code
        BufferedBitWriter output = new BufferedBitWriter(pathName); // writes out the binary code to an output file
        int charInt; // keeps track of the current character being processed

        while ((charInt = input.read()) != -1){
            // converts the integer in charInt into a character
            char character = (char) charInt;

            if (this.codeMap.containsKey(character)){
                ArrayList<Integer> binaryCode = codeMap.get(character); // gets the arraylist holding the binary code

                // accesses the binary code and uses the BitWriter to write it in bit code
                for (Integer i: binaryCode){
                    if (i == 0){
                        output.writeBit(false);
                    }
                    else {
                        if(i == 1){
                            output.writeBit(true);
                        }
                    }
                }
            }
        }
        output.close();
        input.close();
    }

    /** reads a bit file and decompresses it
     *
     * @param inputFile name of bit file being read
     * @param outputFile name of outfile
     * @throws IOException NoFileException
     */
    public void readBitInput(String inputFile, String outputFile) throws IOException {
        BufferedBitReader input = new BufferedBitReader(inputFile); // file to be read
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile)); // file to be written into

        TreeComparator node = codeTree; // Holds the current node reached during any bit reading cycle

        while (input.hasNext()){

            if(node.getData().getCharacter() != null){
                output.write(node.getData().getCharacter()); // writes to output file when a character is reached
                node = codeTree; // resets node for next reading cycle
            }

            boolean bool = input.readBit(); // holds the boolean gotten from the BitReader

            if (bool){
                node = (TreeComparator) node.getRight(); // goes right if bool is true i.e 1
            }
            else {
                node = (TreeComparator) node.getLeft(); //goes left if bool is false i.e 0
            }
        }
        input.close();
        output.close();
    }
}
