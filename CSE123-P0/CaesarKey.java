// Oliver Giordano 
// 01/16/2026
// CSE 123 
// P0: Ciphers
// TA: Ishita Mundra

import java.util.*;

// Allows the user to create a caesar key cipher with the users own key and use it to 
// encode and decode strings
public class CaesarKey extends Substitution{
    
    // Behavior:
    //   - Initializes the CaesarKey class and generates a new cipher based on the users
    //     key input
    // Parameters:
    //   - The key input the user wants to create the cipher with
    // Returns:
    //   - None 
    // Exceptions:
    //   - Throws an IllegalArgumentException if the key input by the user is, null, contains 
    //     duplicate characters, or contains characters not part of the VALID_CHARS character set
    public CaesarKey(String key){
        super();
        if(key == null){
            throw new IllegalArgumentException();
        }
        ArrayList<String> charArray = new ArrayList<String>();
        for(int i = 0; i < key.length(); i++){
           charArray.add(key.substring(i,i+1)); 
        }
        Set<String> characterSet = new HashSet<String>(charArray);
        if(characterSet.size() != charArray.size()){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < key.length(); i++){
            if(!isCharValid(key.charAt(i))){
                throw new IllegalArgumentException();
            }
        }

        String encoding = key;
        for(int i = 0; i < VALID_CHARS.length(); i ++){
            if(!key.contains(""+VALID_CHARS.charAt(i))){
                encoding += VALID_CHARS.charAt(i);
            }
        }
        super.setEncoding(encoding);
    }

    // Behavior:
    //   - throws error 
    // Parameters:
    //   - String value for encoder
    // Returns:
    //   - None 
    // Exceptions:
    //   - throws UnsupportedOperationException whenever called as users cannot set encoding
    //     on shift ciphers
    public void setEncoding(String encoder) {
        throw new UnsupportedOperationException("users cannot set encoding on Caesar key ciphers");
    }
}
