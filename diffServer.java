import java.io.*;  
import java.net.*;  
import java.util.Scanner;

public class diffServer {
    public static void main(String[] args)throws Exception{
        ServerSocket ss=new ServerSocket(6666);  
        Socket s=ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        System.out.println("Running....");
        
        Scanner sc = new Scanner(System.in); 

        long alpha, q,xb,ks;
        System.out.println("Enter value for public key alpha:");  
        alpha = sc.nextLong();  
        System.out.println("Enter value for public key q:");  
        q = sc.nextLong();

        System.out.println("Enter value for private key b selected by user2:xb");  
        xb = sc.nextLong();

        long yb = calculatePower(alpha, xb, q);
        long ya=dis.readLong();
        dout.writeLong(yb);

        ks = calculatePower(ya, xb, q);
        System.out.println("Secret key for User2 is:" + ks);

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
