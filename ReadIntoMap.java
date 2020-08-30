import java.io.*;
import java.util.*;

/** ReadintoMap class takes a text file and maps out all the characters and their frequencies
 * Author: Okorie Kenechukwu & Catriona Farquason
 * Modelled After Search from course webpage
 */
public class ReadIntoMap {
    private HashMap<Character, Integer> frequencyMap;
    private String file;

    /** creates instance of the class
     *
     * @param file FILE TO MAP
     */
    public ReadIntoMap(String file){
        frequencyMap = new HashMap<>(); // map of characters and their frequencies
        this.file = file; // hold the file name of file ot be mapped
    }

    /** loads the file and maps out the characters and their frequencies
     *
     * @throws Exception FileNotFound Exception
     */
    public void loadFile() throws Exception {
        // Loop over lines
        BufferedReader input = new BufferedReader(new FileReader(file));
        int charInt;
        while ((charInt = input.read()) != -1){
            // Loop over all the words split out of the string, adding to map or incrementing count
            char character = (char) charInt;

            if (frequencyMap.containsKey(character)) {
                // Increment the count
                frequencyMap.put(character, frequencyMap.get(character)+1);
            }
            else {
                // Add the new word
                frequencyMap.put(character, 1);
            }

        }
        input.close();
    }

    /** gets the built map
     *
     * @return frequency map
     */
    public HashMap<Character, Integer> getMap(){
        return frequencyMap; // returns the frequency map
    }
}
