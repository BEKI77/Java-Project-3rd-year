import java.io.*;
import java.security.*;
import javax.crypto.*;

public class FileEncryptor {

    public void encryptFile(String inputFile, String outputFile, Key key, EncryptionManager encryptionManager) throws IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] inputBytes = readFile(inputFile);
        byte[] encryptedBytes = encryptionManager.encrypt(inputBytes, key);
        writeFile(outputFile, encryptedBytes);
    }

    private byte[] readFile(String filename) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(filename)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return bytes;
        }
    }

    private void writeFile(String filename, byte[] data) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            outputStream.write(data);
        }
    }
}