import java.io.*;
import java.math.*;
import java.util.*;
/*
 * Java program for RSA asymmetric cryptographic algorithm.
 * For demonstration, values are
 * relatively small compared to practical application
 */
public class rsa {
  public static double gcd(double a, double h)
  {
    /*
         * This function returns the gcd or greatest common
         * divisor
         */
    double temp;
    while (true) {
      temp = a % h;
      if (temp == 0)
        return h;
      a = h;
      h = temp;
    }
  }
  public static void main(String[] args)
  {
    double p = 3;
    double q = 7;
 
    // Stores the first part of public key:
    double n = p * q;
 
    // Finding the other part of public key.
    // double e stands for encrypt
    double e = 2;
    double phi = (p - 1) * (q - 1);
    while (e < phi) {
      /*
             * e must be co-prime to phi and
             * smaller than phi.
             */
      if (gcd(e, phi) == 1)
        break;
      else
        e++;
    }
    int k = 2; // A constant value
    double d = (1 + (k * phi)) / e;
 
    // Message to be encrypted
    double msg = 12;
 
    System.out.println("Message data = " + msg);
 
    // Encryption c = (msg ^ e) % n
    double c = Math.pow(msg, e);
    c = c % n;
    System.out.println("Encrypted data = " + c);
 
    // Decryption m = (c ^ d) % n
    double m = Math.pow(c, d);
    m = m % n;
    System.out.println("Original Message Sent = " + m);
  }
}
 
/*
 * import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter n: ");
		int n = sc.nextInt();
		int p=0;
		int q=0;
		for(int i=2;i<n;i++){
		    while(n%i==0){
		        System.out.println(i+"");
		        p = i;
		        n=n/i;
		    }
		}
		if(n>2){
		    System.out.println(n);
		    q=n;
		}
		System.out.println(p);
	    System.out.println(q);   
	}
	
	
}
 */