import java.util.*;
public class euc
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter n: ");
		int n = sc.nextInt();
		int n1 = n;
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
		int phi = (p-1)*(q-1);
		
		//e
        for(int i=0;i<phi;i++){
            boolean x = gcd(phi, i);
            if(x == true){
                int euc = euclid(phi,i);
                System.out.println("For "+ i+" " +euc+","+n1);
            }
                
        }
        
        
        
      
    
	}
	
	public static boolean gcd(int a, int b){
        int q=0,r=0;
        while(b!=0){
            q=a/b;
            r=a%b;
            a=b;
            b=r;
        }
        if(a==1)
            return true;
        return false;
	}
	
	public static int euclid(int a, int b){
	    
        int q=0,r=0,t1=0,t2=1,t,tt;
        int a1=a;
        while(b!=0){
            q=a/b;
            r=a%b;
            t=t1-(q*t2);
            a=b;
            b=r;
            t1=t2;
            t2=t;
        }
        if(a!=1){
            return 0;
        }
        else
        { 
            tt=t1%a1;
            if(tt<0){tt=tt+a1;}
            return tt; 
        }
	}
	
	
}

/* primi
 * 
 * import java.io.*;
 
class GFG {
 // Recursive function to return gcd of a and b
    static int __gcd(int a, int b)
    {
        // Everything divides 0 
        if (a == 0)
          return b;
        if (b == 0)
          return a;
        
        // base case
        if (a == b)
            return a;
        
        // a is greater
        if (a > b)
            return __gcd(a-b, b);
        return __gcd(a, b-a);
    }
 
// Function to return the count of
// primitive roots modulo p
static int countPrimitiveRoots(int p)
{
    int result = 1;
    for (int i = 2; i < p; i++)
        if (__gcd(i, p) == 1)
            result++;
 
    return result;
}
 
// Driver code
    public static void main (String[] args) {
            int p = 5;
 
    System.out.println( countPrimitiveRoots(p - 1));
    }
}
 */