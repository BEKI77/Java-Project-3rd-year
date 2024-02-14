import java.util.*;
import java.security.Key;

public class Main {
    public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);
                // Choose encryption algorithm, mode, and padding
                System.out.println("Choose encryption algorithm (e.g., AES):");
                String algorithm = scanner.nextLine();
                // Choose a default padding instead

               
                KeyManager keyManager = new KeyManager();
    
                // Generate or load key
                System.out.println("Do you want to generate a new key or use an existing one? (generate/load)");
                String choice = scanner.nextLine();
                Key key;
                if (choice.equalsIgnoreCase("generate")) {
                    System.out.println("Enter key size:(128,192,256)");
                    int keySize = scanner.nextInt();
                    key = keyManager.generateKey(algorithm, keySize);
                    System.out.println("Key generated successfully: "+ key);
                    System.out.println("Do you want to save the generated key(y/n): ");
                    String fl = scanner.next();
                    if (fl.equals("y")|| fl.equals("Y")){
                        System.out.println("Enter path to the file(eg. 'fileName'.txt): ");
                        String path = scanner.next();
                        try{
                            keyManager.writeKeyToFile(key, "./Keys/"+path);
                            System.out.println("Success the key has been saved in ./Keys/"+path);
                        }catch(Exception e){
                            System.out.println("Can't save the key try again: "+ e);
                        }
                    }
                } else if (choice.equalsIgnoreCase("load")) {
                    System.out.println("Enter path to the key file:");
                    String keyFilePath = scanner.nextLine();
                    key = keyManager.readKeyFromFile(keyFilePath);
                    System.out.println("Key loaded successfully.");
                } else {
                    System.out.println("Invalid choice.");
                    scanner.close();
                    return;
                }
    
                // Encrypt or decrypt file
                System.out.println("Do you want to encrypt or decrypt the file? (e/d)");

                String operation = scanner.next();

                if (operation.equalsIgnoreCase("e")) {
                    System.out.println("Enter path to input file:('FILENAME.txt)");
                    String inputFile = scanner.next();
                    System.out.println("Enter path to output file:('FILENAME.txt)");
                    String outputFile = scanner.next();

                    EncryptionManager encryptionManager = new EncryptionManager(algorithm);
                    FileEncryptor encryptor = new FileEncryptor();
                    
                    encryptor.encryptFile(inputFile, outputFile, key, encryptionManager);
                    System.out.println("File encrypted successfully.");
                } else if (operation.equalsIgnoreCase("d")) {
                    
                    System.out.println("Enter path to input file:('FILENAME.txt)");
                    String inputFile = scanner.next();
                    System.out.println("Enter path to output file:('FILENAME.txt)");
                    String outputFile = scanner.next();

                    DecryptionManager decryptionManager = new DecryptionManager(algorithm);
                    FileDecryptor decryptor = new FileDecryptor();
                    decryptor.decryptFile(inputFile, outputFile, key, decryptionManager);
                    System.out.println("File decrypted successfully.");
                } else {
                    System.out.println("Invalid operation.");
                }
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    
        }
    }
        
