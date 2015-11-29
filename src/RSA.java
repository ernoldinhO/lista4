package krypto4;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

/**
 * Simple RSA public key encryption algorithm implementation.
 */
public class RSA {
  private BigInteger n, d, e, m;
  private int bitlen;
  public static SecureRandom rnd = new SecureRandom();
/** Create an instance that can encrypt using someone elses public key. */
  //public RSA(BigInteger newn, BigInteger newe) {
    //n = newn;
   // e = newe;
 // }

  /** Create an instance that can both encrypt and decrypt. */
  public RSA(int bits) {
    bitlen = bits;
    //Standartowe obliczenie n
    /*
    BigInteger p = new BigInteger(MillerRabin.ZnajdzPierwsza(bitlen/2));
    BigInteger q = new BigInteger(MillerRabin.ZnajdzPierwsza(bitlen/2));
    n = p.multiply(q);
    */
    //N dla k liczb
    n= new BigInteger("1");
    for(BigInteger e:Lista.liczbyp)
	{
		n=n.multiply(e);
	}
    //obliczanie funkcji Eulera m=f(n)=(p-1)*(q-1)
    /*
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
        */
    m= new BigInteger("1");
    for(BigInteger e:Lista.liczbyp)
	{
		m=m.multiply(e.subtract(new BigInteger("1")));
	}
   // int zakresE = 2^(bits-2);
    e = new BigInteger(2^(bitlen*Lista.liczbyp.size()-2),rnd).shiftLeft(1).add(new BigInteger("1"));
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }
  /** Generate a new public and private key set. */
  /*public synchronized void generateKeys() {
    BigInteger p = new BigInteger(MillerRabin.ZnajdzPierwsza(bitlen/2));
    BigInteger q = new BigInteger(MillerRabin.ZnajdzPierwsza(bitlen/2));
    n = p.multiply(q);
    BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
        .subtract(BigInteger.ONE));
    e = new BigInteger(2^(bits-2),rnd).shiftLeft(1).add(new BigInteger("1"));
    while (m.gcd(e).intValue() > 1) {
      e = e.add(new BigInteger("2"));
    }
    d = e.modInverse(m);
  }
*/
  public static void odczytPlikuTekstowegoE(String nazwa) throws IOException {
	  	RSA rsa = new RSA(1024);
	  	BigInteger n=rsa.n;
	  	BigInteger d=rsa.d;
	  	BigInteger e=rsa.e;
	  	BufferedWriter writer = new BufferedWriter(new FileWriter("zaszyfrowany.txt"));
		BufferedWriter writern = new BufferedWriter(new FileWriter("n.txt"));
		BufferedWriter writerd = new BufferedWriter(new FileWriter("d.txt"));
		writern.write(new String(n.toString()));
		writerd.write(new String(d.toString()));
		writern.close();
		writerd.close();
		FileInputStream fileInput = new FileInputStream(nazwa);
		int r;
		BigInteger dlTextu=new BigInteger("1");
		String text="";
		while ((r = fileInput.read()) != -1) 
		{
		   char c = (char) r;
		   text+=(char)c;
		   dlTextu=new BigInteger(text.getBytes());
		   if(dlTextu.compareTo(n) >0 )
		   {
			   text=text.substring(0,text.length()-1);
			   dlTextu=new BigInteger(text.getBytes());
			   BigInteger wynik=dlTextu.modPow(e, n);
			   writer.write(new String(wynik.toString()));
			   writer.write(" ");
			   text=""+ c;
		   }
		}
		   BigInteger wynik=dlTextu.modPow(e, n);
		   writer.write(new String(wynik.toString()));
		   writer.close();
		   fileInput.close();
		}
		
  public static void odczytPlikuTekstowegoD() throws IOException {
		//dekrypt
		BufferedWriter writer = new BufferedWriter(new FileWriter("odszyfrowanie.txt"));
		FileInputStream fileInput = new FileInputStream("zaszyfrowany.txt");
		FileInputStream filen = new FileInputStream("n.txt");
		FileInputStream filed = new FileInputStream("d.txt");
		String nfile="";
		String dfile="";
		int r;		
		while ((r = filen.read()) != -1) 
		{
			nfile+=(char)r;
		}
		while ((r = filed.read()) != -1) 
		{
			dfile+=(char)r;
		}
		filen.close();
		filed.close();
		//System.out.println(dfile);
		//System.out.println(nfile);
		BigInteger n=new BigInteger(nfile);
		BigInteger d=new BigInteger(dfile);
		String content = new String(Files.readAllBytes(Paths.get("zaszyfrowany.txt")));
		String [] odczytane = content.split(" ");
		for(String o:odczytane)
		{		
			BigInteger wynik=new BigInteger(o).modPow(d, n);
			writer.write(new String(wynik.toByteArray()));
		}
		writer.close();
		fileInput.close();
		}

}

   
    
    
    
    
  