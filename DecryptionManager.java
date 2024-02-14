import java.security.*;
import javax.crypto.*;


public class DecryptionManager {
    private final Cipher cipher;
    // Mode and padding should be default for this case
    public DecryptionManager(String algorithm ) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");    
    }
    
    public byte[] decrypt(byte[] input, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(input);
    }
}
