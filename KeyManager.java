import java.io.*;
import java.security.*;
import javax.crypto.*;

public class KeyManager {

    public SecretKey generateKey(String algorithm, int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.toUpperCase());
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public Key readKeyFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (Key) inputStream.readObject();
        }
    }

    public void writeKeyToFile(Key key, String filename) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(key);
        }
    }
}
