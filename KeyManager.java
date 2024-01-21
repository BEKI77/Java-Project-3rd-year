import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyManager {
    private Map<String, SecretKey> keyStorage;

    public KeyManager() {
        this.keyStorage = new HashMap<>();
    }
    
    public SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }
    void storeKey(String user, SecretKey key){
        this.keyStorage.put(user, key );
    }
    public Map<String, SecretKey> getkeys(){
        return this.keyStorage;
    }
}
