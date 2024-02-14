import java.security.*;
import javax.crypto.*;

public class EncryptionManager {
        
    private Cipher cipher;
    // Mode and padding should be default for this case
    public EncryptionManager(String algorithm ) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");
    }

    public byte[] encrypt(byte[] input, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input);
    }

}
