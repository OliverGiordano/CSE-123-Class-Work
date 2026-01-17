// do I need to wory about the CaesarShift() empty constructer calling the Substittion() method?
// Oliver Giordano 
// 01/16/2026
// CSE 123 
// P0: Ciphers
// TA: Ishita Mundra
    

// Allows the user to create a Caesar shift cipher with the users own shift key value and 
// allows the user to use it to encode and decode strings
public class CaesarShift extends Substitution{

    // Behavior:
    //   - Initializes the caesar shift with and creates a new cipher with a shift key passed in 
    //     by the user
    // Parameters:
    //   - An int value of the number of shifts you want the character set to pass through when 
    //     creating the encoded string
    // Returns:
    //   - None 
    // Exceptions:
    //   - throws IllegalArgumentException of the shift input is negitive
    public CaesarShift(int shift){
        super();
        if(shift < 0){
            throw new IllegalArgumentException();
        }
        String encoding = VALID_CHARS;
        String tmpChar;
        for(int i = 0; i < shift; i++){
            tmpChar = encoding.substring(0,1);
            encoding = encoding.substring(1);
            encoding += tmpChar;
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
    @Override
    public void setEncoding(String encoder) {
        throw new UnsupportedOperationException("users cannot set encoding on shift ciphers");
    }
}   
