import java.util.Arrays;
public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        int counter = 0;
        if (str.length() < numRows * numCols) {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (counter < str.length() ) {
                        letterBlock[i][j] = str.substring(counter, counter + 1);
                        counter++;
                    } else letterBlock[i][j] = "A";
                }
            }
        }
        else {
            int c = 0;
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (c != str.length()) {
                        letterBlock[i][j] = str.substring(c,c+1);
                        c++;
                    }
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
    public String encryptBlock()
    {
        String message = "";
            for (int j = 0; j < numCols; j++) {
                for (int i = 0; i < numRows; i++) {
                message += letterBlock[i][j];
            }
        }
        return message;
    }
        /* to be implemented in part (b) */


    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message) {
        String blank = "";
        int i = 0;
        while (i < message.length()) {
                fillBlock(message.substring(i));
                blank += encryptBlock();
                i += numRows*numCols;
        }
        return blank;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String blank = "";
        int i = 0;
        int c = 0;
        while (i < encryptedMessage.length()) {
            for (int r = 0; r < numRows; )
           letterBlock[r][c] = encryptedMessage.substring(i, i+1);
           r++;
           c++;
        }
        for (int j = 0; j < letterBlock.length; j++) {
            for (int q = 0; q < letterBlock[0].length; q++) {
                if (letterBlock[j][q].equals("A") == false ) {
                    blank = blank + letterBlock[j][q];
                }
            }
        }
        return blank;

    }


}