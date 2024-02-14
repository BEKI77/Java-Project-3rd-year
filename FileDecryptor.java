import javax.crypto.*;
import java.io.*;
import java.security.*;

public class FileDecryptor {

    public void decryptFile(String inputFile, String outputFile, Key key, DecryptionManager decryptionManager) throws IOException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] inputBytes = readFile(inputFile);
        byte[] decryptedBytes = decryptionManager.decrypt(inputBytes, key);
        writeFile(outputFile, decryptedBytes);
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
