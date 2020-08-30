/** class that hold both a character and an int as it's data
 *
 *
 */

public class TwoForOne{
    private Integer Freq; // frequency of the character
    private Character Char; // the character

    /** creates instance without assigning character name
     *
     * @param frequency frequency of character
     */
    public TwoForOne(Integer frequency){
        this.Freq = frequency;
    }

    /** creates instance & also assigns frequency and name
     *
     * @param frequency frequency of character
     * @param character associated character
     */
    public TwoForOne(Character character, Integer frequency){
        this.Freq = frequency;
        this.Char = character;
    }

    /** gets the frequency of the character
     *
     * @return Freq
     */
    public Integer getFrequency() {
        return this.Freq;
    }

    /** gets the associated character
     *
     * @return Char
     */
    public Character getCharacter(){
        return this.Char;
    }

    /** prints out a string representation of the object
     *
     * @return string representation
     */
    public String toString(){
        if (this.Char != null){
            return this.Char + ":" + this.Freq;
        }
        else{
            return this.Freq.toString();
        }
    }
}