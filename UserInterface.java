import java.util.Scanner;
import javax.crypto.*;

public class UserInterface {
    public static void main(String[] args) {
        System.out.println("Do you want to generate a security key? (Y/N)");
        Scanner input = new Scanner(System.in);
        char x = input.nextLine().charAt(0);

        if (x=='Y' || x=='y'){
            System.out.println("Enter the length of the key: (128 or 192 or 256)");
            int n = input.nextInt();
            if ( n==128 || n==192 || n==256){
                try{
                    KeyManager keyMan = new KeyManager();
                    SecretKey key = keyMan.generateKey(n);
                    System.out.println("Do you want to save the key? (1/0)");
                    int s = input.nextInt();
                    
                    
                    if (s == 1){
                        System.out.print("Enter the user name: ");

                        String name = input.next();
                        
                        keyMan.storeKey(name,key);
                    }
                    System.out.println(keyMan.getkeys());   

                }catch(Exception NoSuchAlgorithmException){
                    System.out.println(NoSuchAlgorithmException);
                }
            }else{
                System.out.println("Invalid input");
            }
            
        }
        input.close();
        
    }
}
