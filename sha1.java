import java.math.BigInteger; 
import java.util.*; 
 
public class sha1 { 
    public static String[] rotate(String[] arr,int n){         
        String[] arr1 = new String [arr.length];
        for(int i=0;i<arr.length;i++){ 
            arr1[i] = arr[(i+(arr.length+n))%arr.length]; 
        } 
        return arr1; 
    } 
    public static String hexAdd(String hex, String hex1){         
        BigInteger bigInteger = new BigInteger(hex, 16); 
        BigInteger bigInteger1 = new BigInteger(hex1, 16); 
        BigInteger hehe = bigInteger.add(bigInteger1);         
        String ans = hehe.toString(16); 
        if (ans.length() != 8){             
            ans = ans.substring(1); 
        } 
        return ans; 
    } 
    public static String not(String s){ 
         String e = "FFFFFFFF"; 
         BigInteger a = new BigInteger(s, 16); 
         BigInteger b = new BigInteger(e, 16); 
         BigInteger hehe = b.subtract(a);          
         String ans = hehe.toString(16);           
         return ans; 
    } 
    public static String booleanFunction(String b, String c, String d){ 
        String Bnot = not(b); 
        BigInteger b1 = new BigInteger(b,16); 
        BigInteger c1 = new BigInteger(c,16); 
        BigInteger d1 = new BigInteger(d,16); 
        BigInteger bnot1 = new BigInteger(Bnot,16); 
        BigInteger BandC = b1.and(c1); 
        BigInteger BnotandD = bnot1.and(d1);         
        BigInteger ans = BandC.xor(BnotandD);         
        return ans.toString(16); 
    } 
    public static String shift(String s,int n){ 
        String bin_str = ""; 
        String final_str = ""; 
        String final_substr = "";         
        String ss =  "";         
        for(int i=0;i<s.length();i++){ 
            int a = Integer.parseInt(String.valueOf(s.charAt(i)),16); 
            String b = String.format("%4s", Integer.toBinaryString(a)).replace(" ", "0");             
            bin_str += b; 
        } 
        String[] bin_arr = new String[bin_str.length()];         
        for(int i=0;i<bin_str.length();i++){ 
            bin_arr[i] = String.valueOf(bin_str.charAt(i)); 
        } 
         
        String[] bin_arr1 = rotate(bin_arr,n);         
        for(int i=0;i<bin_arr1.length;i++){        
            final_str += bin_arr1[i]; 
        } 
        for(int i=0;i<final_str.length();i=i+4){             
            final_substr = final_str.substring(i, i+4);             
            int num = Integer.parseInt(final_substr,2); 
            String hex = Integer.toHexString(num); 
             
            ss += hex;             
            final_substr = ""; 
        } 
        return ss; 
    } 
    public static String StrToHex(String str) {         
        String s = ""; 
        for (int i=0;i<4;i++) { 
            String charToHex = Integer.toHexString(str.charAt(i));             
            s += charToHex; 
        } 
        return s; 
    } 
    public static void main(String[] args) {         
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter A"); 
        String a = sc.next(); 
        System.out.println("\nEnter B"); 
        String b = sc.next(); 
        System.out.println("\nEnter C"); 
        String c = sc.next(); 
        System.out.println("\nEnter D"); 
        String d = sc.next(); 
        System.out.println("\nEnter E"); 
        String e = sc.next(); 
        System.out.println("\nEnter the message");         
        String msg = sc.next();         
        sc.close(); 
        String newC = shift(b,30).toUpperCase(); 
        String newB = a.toUpperCase(); 
        String newE = d.toUpperCase(); 
        String newD = c.toUpperCase();  
        String fT = booleanFunction(b, c, d); 
        String s1 = hexAdd(e,fT); 
        String s2 = hexAdd(shift(a, 5), s1); 
        String wT = StrToHex(msg); 
        String s3 = hexAdd(s2, wT); 
        String kT = "5A827999"; 
        String s4 = hexAdd(s3, kT); 
        String newA = s4.toUpperCase(); 
        System.out.println("\nResult after one step of round One of SHA1"); 
        System.out.println("A="+newA+"\nB="+newB+"\nC="+newC+"\nD="+newD+"\nE="+newE); 
    } 
} 
 



/*
 * import java.math.BigInteger;
import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
        // String ans = not("CCCCCCCC");
        // System.out.print(ans);

        // String ans = hexAdd("98BADCFE","C3D2E1F0");
        // System.out.print(ans);
        
        // String ans = bool("EFCDAB89","98BADCFE","10325476");
        // System.out.print(ans);
        
        // String ans = StrToHex("INFORMATION SECURITY");
        // System.out.print(ans);
        
        //Circular shift
        int h1 = 0x22222222;
        int ans = (h1 << 30) | (h1 >> (Integer.SIZE - 30));
        System.out.println(Integer.toHexString(ans));
        
	}
	
	public static String not(String h){
	    String h2 = "FFFFFFFF";
	    BigInteger a = new BigInteger(h,16);
	    BigInteger b = new BigInteger(h2,16);
	    String ans = b.subtract(a).toString(16);
	    return ans;
	}
	
	public static String hexAdd(String h1, String h2){
	    BigInteger a = new BigInteger(h1,16);
	    BigInteger b = new BigInteger(h2,16);
	    String ans = a.add(b).toString(16);
	    if(ans.length()>8){
	        ans=ans.substring(1);
	    }
	    return ans;
	}
	
	public static String bool(String b, String c, String d){
	    String Bnot = not(b); //(b' & d) xor (b and c)
	    BigInteger b1 = new BigInteger(b,16);
	    BigInteger c1 = new BigInteger(c,16);
	    BigInteger d1 = new BigInteger(d,16);
	    BigInteger bnot1 = new BigInteger(Bnot,16);
	    BigInteger BandC = b1.and(c1);
	    BigInteger BnotandD = bnot1.and(d1);
	    String ans = BandC.xor(BnotandD).toString(16);
	    return ans;
	}
	
	public static String StrToHex(String str){
	    String ans = "";
	    for(int i=0;i<4;i++){
	        String chr = Integer.toHexString(str.charAt(i));
	        ans += chr;
	    }
	    return ans;
	}
}
 */
 
 
 
 
