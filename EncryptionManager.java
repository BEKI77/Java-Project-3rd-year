import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


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
