/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.*;

public class HillCipherNew
{
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    static Scanner sc = new Scanner(System.in);
    
    public static int[][] matmul(int[][] mx1, int[][] mx2, int n1, int n2){
        int[][] ans = new int[n1][n2];
        for(int i=0;i<n1;i++){
            for(int j=0;j<n2;j++){
                ans[i][j]=0;
                for(int k=0;k<n1;k++){
                    ans[i][j] += mx1[i][k] * mx2[k][j];
                }
            }
        }
        return ans;
    }
    
    public static void printmx(int[][] mx){
        for(int i=0;i<mx.length;i++){
            for(int j=0;j<mx[i].length;j++){
                System.out.print(mx[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public static int[][] performModulus(int[][] mx, int mod) {
        int[][] modArr = new int[mx.length][mx[0].length];
        for (int i = 0; i < mx.length; i++) {
            for (int j = 0; j < mx[i].length; j++) {
                if (mx[i][j] < 0) {
                    modArr[i][j] = (-1) * (Math.abs(mx[i][j]) % mod) + mod;
                } else
                    modArr[i][j] = mx[i][j] % mod;
            }
        }
        return modArr;
    }
    
    public static int determinant2(int[][] mx){
        return (mx[0][0] * mx[1][1]) - (mx[0][1] * mx[1][0]);
    }
    
    public static int determinant3(int[][] mx){
        return mx[0][0]*(mx[1][1]*mx[2][2]-mx[2][1]*mx[1][2]) - mx[1][0]*(mx[0][1]*mx[2][2]-mx[2][1]*mx[0][2]) + mx[2][0]*(mx[0][1]*mx[1][2]-mx[1][1]*mx[0][2]);
    }
    
    public static int[][] multipWithConstant(int[][] mx, int cons) {
        int[][] mul = new int[mx.length][mx[0].length];
        for (int i = 0; i <mx.length; i++)
            for (int j = 0; j < mx[0].length; j++)
                mul[i][j] = mx[i][j] * cons;
        return mul;
    }
    
    public static int multiplicInv(int num1, int num2) {
        int rem = Math.abs(num1) % num2;
        if (num1 < 0)
            num1 = 26 + (-1) * rem;
        for (int i = 1; i < num2; i++) {
            if ((num1 * i) % num2 == 1) {
                return i;
            }
        }
        return 0;
    }
    
    public static int[][] transpose(int mat[][], int r, int c) {
        int tr[][] = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                tr[i][j] = mat[j][i];
            }
        }
        return tr;
    }
    
    public static int[][] getCofactor(int mat[][], int r, int c, int n) {
        int i = 0, j = 0;
        int temp[][] = new int[n - 1][n - 1];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != r && col != c) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return temp;
    }
    
    public static int[][] getAdjoint(int mat[][]) {
        int[][] adj = new int[mat.length][mat[0].length];
        if (mat.length == 2) {
            adj[0][1] = (-1) * mat[0][1];
            adj[1][0] = (-1) * mat[1][0];
            int temp = mat[0][0];
            adj[0][0] = mat[1][1];
            adj[1][1] = temp;
            return adj;
        } else {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    int sign = (int) Math.pow(-1, (i + j + 2));
                    adj[i][j] = sign * (determinant2(getCofactor(mat, i, j, mat.length)));
                }
            }
            return transpose(adj, mat.length, mat[0].length);
        }
    }
    
    public static int[][] getInverse(int[][] mx){
        int det = 0;
        if(mx.length == 2)
            det = determinant2(mx);
        else if(mx.length == 3)
            det = determinant3(mx);
        int detinv = multiplicInv(det, 26);
        int[][] adjmx = getAdjoint(mx);
        int[][] mulinv = multipWithConstant(adjmx, detinv);
        int[][] inv = performModulus(mulinv, 26);
        return inv;
    }
    
    public static int[][] encode(String Text,int m1){
        int n1=m1;
        int n2=(Text.length())/m1;
        int[][] mx = new int[n1][n2];
        int k=0;
        for(int i=0;i<n2;i++){
            for(int j=0;j<n1;j++){
                    int charPosition = ALPHABET.indexOf(Text.charAt(k));
                    mx[j][i] = charPosition;
                    k++;
            }
        }
        return mx;
    }
    
    public static String decode(int[][] mx){
        String ans="";
        for (int i = 0; i < mx[0].length; i++) {
            for (int j = 0; j <mx.length; j++)
                ans += ALPHABET.charAt(mx[j][i]);
            }
        return ans;
    }
    
    public static String encrypt(){
        
        System.out.println("Enter plaintext: ");
        String pt = sc.next();
        
        System.out.println("Enter the order of key matrix: ");
        int m1 = sc.nextInt();
        int filler=0;
        if(m1==2)
            filler= pt.length()%m1;
        if(m1==3)
            filler = m1*m1-pt.length();
        if(filler != 0){
            for(int i=0;i<filler;i++){
                pt=pt+'x';
            }
        };
        
        int[][] key_mx = new int[m1][m1];
        System.out.println("Enter key matrix elements: ");
        for(int i=0;i<m1;i++){
            for(int j=0;j<m1;j++){
                key_mx[i][j] = sc.nextInt();
            }
        }
        int[][] en_mx = encode(pt, m1);
        int[][] mul = matmul(key_mx, en_mx, key_mx.length, en_mx[0].length);
        int[][] modmul = performModulus(mul, 26);
        String ct = decode(modmul);
        return ct;
    }
    
    public static String decrypt(){
        System.out.println("Enter cipher text");
        String ct = sc.next();
        
        System.out.println("Enter the order of key matrix: ");
        int m1 = sc.nextInt();
        
        int[][] key_mx = new int[m1][m1];
        System.out.println("Enter key matrix elements: ");
        for(int i=0;i<m1;i++){
            for(int j=0;j<m1;j++){
                key_mx[i][j] = sc.nextInt();
            }
        }
        
        int[][] en_ct = encode(ct, m1);
        int[][] inv_key = getInverse(key_mx);
        //printmx(inv_key);
        int[][] ans = matmul(inv_key, en_ct, inv_key.length, en_ct[0].length);
        int[][] ans_mod = performModulus(ans,26);
        String pt = decode(ans_mod);
        return pt;
        
    }

    public static int[][] knownptct(){
        System.out.println("Enter Cipher Text : ");
        String ct = sc.next();
        System.out.println("Enter Plain Text : ");
        String pt = sc.next();
        System.out.println("Enter Key Dimension : ");
        int m1 = sc.nextInt();
        ct = ct.substring(0, m1 * m1);
        pt = pt.substring(0, m1 * m1);
        int[][] en_ct = encode(ct,m1);
        int[][] en_pt = encode(pt,m1);
        int[][] inv_pt = getInverse(en_pt);
        int[][] mul = matmul(en_ct, inv_pt, m1, m1);
        int[][] modmul = performModulus(mul,26);
        return modmul;
    }
    
    
    
	public static void main(String[] args) {
		while(true){
            System.out.println("1 - Encryption  2 - Decryption 3 - KnownPtCt Attack \n Any other number - Exit");
            int key = sc.nextInt();
            String ans;
            switch (key){
                case 1:
                    ans = encrypt();
                    System.out.println("The required cipher text: "+ans);
                    break;
                
                case 2:
                    ans = decrypt();
                    System.out.println("The required plain text: "+ans);
                    break;

                case 3:
                    int[][] key_mx = knownptct();
                    printmx(key_mx);
                    break;
    
                default:
                    System.exit(0);
            } 
	}
}
}
