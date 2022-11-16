import java.io.*;  
import java.net.*;  
import java.util.Scanner;
public class diffClient {
    public static void main(String[] args)throws Exception{
        Socket s=new Socket("localhost",6666);  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        DataInputStream dis=new DataInputStream(s.getInputStream());
        Scanner sc = new Scanner(System.in); 

        long alpha, q,xa,ks;
        System.out.println("Enter value for public key alpha:");  
        alpha = sc.nextLong();  
        System.out.println("Enter value for public key q:");  
        q = sc.nextLong();
        System.out.println("Enter value for private key(XA) a selected by user1:");  
        xa = sc.nextLong();
        
        long ya = calculatePower(alpha, xa, q);
        dout.writeLong(ya);
        long yb=dis.readLong();
        ks = calculatePower(yb, xa, q);
        System.out.println("Secret key for User1 is:" + ks); 
            

        } 
    private static long calculatePower(long x, long y, long q)  
    {  
        long result = 0;          
        if (y == 1){  
            return x;  
        }  
        else{  
            result = ((long)Math.pow(x, y)) % q;  
            return result;  
        }  
    }   

}