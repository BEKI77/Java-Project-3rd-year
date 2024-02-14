import java.util.*;
import java.security.*;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            try {
                Scanner scanner = new Scanner(System.in);
                // Choose encryption algorithm
                System.out.println("Choose encryption algorithm (e.g., AES):");
                String algorithm = scanner.nextLine();
               
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
                    try{
                        decryptor.decryptFile(inputFile, outputFile, key, decryptionManager);
                        System.out.println("File decrypted successfully.");
                    }catch(InvalidKeyException e){
                        System.out.println("Invalid Key try again: ");
                    }
                    
                } else {
                    System.out.println("Invalid operation.");
                }
                System.out.println("Do you want to exit:(y/n)");
                if(scanner.next().equals("y")){
                    flag = false;
                }
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
    
        }
    }
        
