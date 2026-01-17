// Oliver Giordano 
// 01/16/2026
// CSE 123 
// P0: Ciphers
// TA: Ishita Mundra

import java.util.*;

// This class allows users to create a substitution cipher with there own substitution cipher key
// and use it to encode and decode strings
public class Substitution extends Cipher{
    private String encoding;
    
    // Behavior:
    //   - Initializes the substitution cipher class with no encoder
    // Parameters:
    //   - None
    // Returns:
    //   - None
    // Exceptions:
    //   - None
    public Substitution(){
        encoding = null;
    }

    // Behavior:
    //   - Initializes the substitution cipher class with an substitution cipher key
    // Parameters:
    //   - The substitution cipher key you wish to encode with, must be the same length
    //     as the character set you are substituting with, contain no duplicit characters
    //     and all characters must be valid charcters as contained in VALID_CHARS
    // Returns:
    //   - None
    // Exceptions:
    //   - throws IllegalArgumentException if the encoder input is, null, not the length of the
    //     length of the character set you are substituting from, contains duplicits or contains
    //     characters that are not valid as contained in VALID_CHARS
    public Substitution(String encoding){
        checkEncodingLegality(encoding);
        this.encoding = encoding;
    }

    // Behavior:
    //   - Sets the substitution cipher key that will be used for encoding and decoding
    // Parameters:
    //   - The substitution cipher key you wish to encode with, must be the same length
    //     as the character set you are substituting with, contain no duplicit characters
    //     and all characters must be valid charcters as contained in VALID_CHARS
    // Returns:
    //   - None
    // Exceptions:
    //   - throws IllegalArgumentException if the encoder input is, null, not the length of the
    //     length of the character set you are substituting from, contains duplicits or contains
    //     characters that are not valid as contained in VALID_CHARS
    public void setEncoding(String encoding){
        checkEncodingLegality(encoding);
        this.encoding = encoding;
    }

    // Behavior:
    //   - Takes a string and checks if it acts as a legal encoder
    // Parameters:
    //   - A string that you wish to check if it would act as legal encoder
    // Returns:
    //   - None
    // Exceptions:
    //   - throws IllegalArgumentException if the string input is, null, not the length of the
    //     length of the character set you are substituting from, contains duplicits or contains
    //     characters that are not valid as contained in VALID_CHARS
    private void checkEncodingLegality(String encoding){
        if(encoding == null){
            throw new IllegalArgumentException();
        }
        if(encoding.length() != VALID_CHARS.length()){
            throw new IllegalArgumentException();
        }
        ArrayList<String> charArray = new ArrayList<String>();
        for(int i = 0; i < encoding.length(); i++){
           charArray.add(encoding.substring(i,i+1)); 
        }
        Set<String> characterSet = new HashSet<String>(charArray);
        if(characterSet.size() != charArray.size()){
            throw new IllegalArgumentException();
        }
        if(encoding.length() != VALID_CHARS.length()){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < encoding.length(); i++){
            if(!isCharValid(encoding.charAt(i))){
                throw new IllegalArgumentException();
            }
        }
    }

    // Behavior:
    //   - Takes a String and returns an encoded version created it by passing it through a 
    //     substitution cipher
    // Parameters:
    //   - The String you wish to encode
    // Returns:
    //   - Returns an encoded String 
    // Exceptions:
    //   - if the argument passed in is null an IllegalArgumentException is thrown, if the encoder
    //     has not been set an illegal state error is thrown
    public String encrypt(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        if(encoding == null){
            throw new IllegalStateException();
        }
        String outputString = "";
        for(int i = 0; i < input.length(); i++){
            int charIndex = VALID_CHARS.indexOf(input.substring(i,i+1)); 
            outputString += encoding.substring(charIndex,charIndex+1);
        }
        return outputString;
    } 

    // Behavior:
    //   - Takes a string and returns a decoded version created it by passing it through a 
    //     substitution cipher
    // Parameters:
    //   - The String you wish to decode
    // Returns:
    //   - Returns an decoded String 
    // Exceptions:
    //   - if the argument passed in is null an IllegalArgumentException is thrown, if the encoder
    //     has not been set an illegal state error is thrown
    public String decrypt(String input){ 
        if(input == null){
            throw new IllegalArgumentException();
        }
        if(encoding == null){
            throw new IllegalStateException();
        }
        String outputString = "";
        for(int i = 0; i < input.length(); i++){
            int j = encoding.indexOf(input.substring(i,i+1)); 
            outputString += VALID_CHARS.substring(j,j+1);
        }
        return outputString;
    }
}
