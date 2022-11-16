import java.util.*;
class CaesarCipher {
private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
public String encrypt(String plainText,int shiftKey)
{
plainText = plainText.toLowerCase();
String cipher_Text="";
for(int i=0;i<plainText.length();i++)
{
int charPosition = ALPHABET.indexOf(plainText.charAt(i));
int keyVal = (shiftKey+charPosition)%26;
char replaceVal = this.ALPHABET.charAt(keyVal);
cipher_Text += replaceVal;
}
return cipher_Text;
}
public String decrypt(String cipherText, int d_shiftKey)
{
cipherText = cipherText.toLowerCase();
String plainText="";
for(int i=0;i<cipherText.length();i++)
{
int charPosition = this.ALPHABET.indexOf(cipherText.charAt(i));
int keyVal = (charPosition-d_shiftKey)%26;
if(keyVal<0)
{
keyVal = this.ALPHABET.length() + keyVal;
}
char replaceVal = this.ALPHABET.charAt(keyVal);
plainText += replaceVal;
}
return plainText;
}

public void brute(String cText)
{
for(int i=0;i<=25;i++)
{
String brute_force=decrypt(cText,i);
System.out.println("Plain Text for Key "+i+" - "+brute_force);
}
}

public void frequency(String c_Text)
{
c_Text=c_Text.toLowerCase();
int[] freq=new int[c_Text.length()];
char[] str=c_Text.toCharArray();
System.out.println("Frequency of Occurance - ");
for(int i=0;i<c_Text.length();i++)
{
freq[i]=1;
for(int j=i+1;j<c_Text.length();j++)
{
if(str[i]==str[j])
{freq[i]++;
str[j]='0';
}
}
if(str[i]!='0'){System.out.println(str[i]+" --> "+freq[i]+"/"+c_Text.length());}
}
for(int i=0;i<26;i++)
{
Scanner sc=new Scanner(System.in);
System.out.println("Enter the Cipher Text - ");
String ct= sc.next();
System.out.println("Enter the Plain Text - ");
String pt= sc.next();
ct=ct.toLowerCase();
pt=pt.toLowerCase();
int k=(ALPHABET.indexOf(ct.charAt(0))-ALPHABET.indexOf(pt.charAt(0)))%26;
String dtext=decrypt(c_Text,k);
System.out.println(dtext);
System.out.println("Are you satisfied?? 1/0");
int ans=sc.nextInt();
if(ans==1){break;}
}
}
}
class Caesar{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
CaesarCipher cc = new CaesarCipher();
while(true){
System.out.println("1 - Encryption  2 - Decryption 3 - Brute Force Attack 4 - Frequency Analysis 5 - exit");
int key=sc.nextInt();
switch(key){
case 1:
      System.out.println("Enter the Plain Text - ");
      String plainText = sc.next();
      System.out.println("Enter the Shift Key - ");
      int shiftKey=sc.nextInt();
      String cipher_Text = cc.encrypt(plainText,shiftKey);
      System.out.println("Your Plain Text :" + plainText);
      System.out.println("Your Cipher Text :" + cipher_Text);
      break;
case 2:
      System.out.println("Enter the Cipher Text - ");
      String cipherText = sc.next();
      System.out.println("Enter the Shift Key - ");
      int d_shiftKey=sc.nextInt();
      String cPlainText = cc.decrypt(cipherText,d_shiftKey);
      System.out.println("Your Plain Text :" + cPlainText);
      break;
case 3:
      System.out.println("Enter the Cipher Text - ");
      String cText = sc.next();
      cc.brute(cText);
      break;
case 4:
      System.out.println("Enter the Cipher Text - ");
      String c_Text = sc.next();
      cc.frequency(c_Text);
      break;
case 5:
      System.exit(0);
}
}
}
}
