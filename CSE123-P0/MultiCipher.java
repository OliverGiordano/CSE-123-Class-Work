// Oliver Giordano 
// 01/16/2026
// CSE 123 
// P0: Ciphers
// TA: Ishita Mundra

import java.util.*;

// Allows the user to create a multi-cipher, a cipher chaining together multiple ciphers in a row
// specifing, the ciphers in the chain, and use it to encode and decode strings
public class MultiCipher extends Cipher{
    private List<Cipher> ciphers;

    // Behavior:
    //   - Initializes a multi-cipher with a list of other ciphers
    // Parameters:
    //   - A list of ciphers objects
    // Returns:
    //   - None
    // Exceptions:
    //   - throws an IllegalArgumentException If the function argument is null 
    public MultiCipher(List<Cipher> ciphers){
        if(ciphers == null){
            throw new IllegalArgumentException();
        }
        this.ciphers = ciphers;
    }

    // Behavior:
    //   - Takes a String and returns an encoded version created it by passing it through a 
    //     passing it through each cipher initialized in the class sequtially 
    // Parameters:
    //   - The String you wish to encode
    // Returns:
    //   - Returns String that is the encoded version of the input argument
    // Exceptions:
    //   - if the argument passed in is null an IllegalArgumentException is thrown, if the encoder
    //     has not been set an illegal state error is thrown
    public String encrypt(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        for(Cipher c : ciphers){
            input = c.encrypt(input);
        }
        return input;
    }

    // Behavior:
    //   - Takes a string and returns a decoded version created it by passing it through each
    //     cipher initiallized in the class in reverse order
    // Parameters:
    //   - The String you wish to decode
    // Returns:
    //   - Returns a String that is the decoded version of the String passed into the function
    // Exceptions:
    //   - if the argument passed in is null an IllegalArgumentException is thrown, if the encoder
    //     has not been set an illegal state error is thrown
    public String decrypt(String input){
        if(input == null){
            throw new IllegalArgumentException();
        }
        for(int i = ciphers.size() - 1; i >= 0; i--){
            input = ciphers.get(i).decrypt(input);
        }
        return input;
    }

    // Behavior:
    //   - throws error 
    // Parameters:
    //   - String value for encoder
    // Returns:
    //   - None 
    // Exceptions:
    //   - throws UnsupportedOperationException whenever called as users cannot set encoding
    //     on multi-ciphers
    public void setEncoding(String encoder) {
        throw new UnsupportedOperationException("users cannot set encoding on multi-ciphers");
    }

}
