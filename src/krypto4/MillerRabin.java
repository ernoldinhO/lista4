package krypto4;

import java.math.BigInteger;
import java.security.SecureRandom;

public class MillerRabin {
	private static final SecureRandom rnd = new SecureRandom();
	//Miller Rabin test
			private static boolean miller_rabin_test(BigInteger a, BigInteger n) {
			    BigInteger n_minus_one = n.subtract(BigInteger.ONE);
			   // compute_s_and_d
			    BigInteger d = n_minus_one;
			    int s = d.getLowestSetBit();
			    d = d.shiftRight(s);
	    		//
			    BigInteger a_to_power = a.modPow(d, n);
			    //System.out.println("Wstepnie : "+n_minus_one.toString(2)+" pozniej :"+d.toString(2));
			    //System.out.println("mamy :"+a_to_power.toString(2)+" zamiast "+a.modPow(n_minus_one, n).toString(2));
			    if (a_to_power.equals(BigInteger.ONE)) return true;
			    for (int i = 0; i < s-1; i++) {
			        if (a_to_power.equals(n_minus_one)) return true;
			        a_to_power = a_to_power.multiply(a_to_power).mod(n);
			    }
			    if (a_to_power.equals(n_minus_one)) return true;
			    return false;
			}
//miller_rabin_generator
			    		public static boolean miller_rabin(BigInteger n) {
			    		    for (int ile_razy = 0; ile_razy < 50; ile_razy++) {
			    		        BigInteger a;
			    		        do {
			    		            a = new BigInteger(n.bitLength(), rnd);
			    		        } while (a.equals(BigInteger.ZERO));
			    		        if (!miller_rabin_test(a, n)) {
			    		            return false;
			    		        }
			    		    }
			    		    return true;
			    		}
//Generator liczby pierwszej			    		
			    		public static String GenerateString(int n)
			    		{
			    			//BigInteger pierwsza = new BigInteger(n - 2, rnd).add((new BigInteger("2").pow(n-2)));
			    			//String liczba=pierwsza.toString(2)+"1";
			    			BigInteger pierwsza = new BigInteger(n - 2, rnd).add((new BigInteger("2").pow(n-2))).shiftLeft(1).add(new BigInteger("1"));
			    			String liczba=pierwsza.toString(2);
			    			return liczba;
			    			
			    		}
			    		public static String ZnajdzPierwsza(int ileBitow)
			    		{
			    			BigInteger pierwsza;
			    			do {
		    	            	pierwsza =new BigInteger(GenerateString(ileBitow),2);
		    	            } while (!miller_rabin(pierwsza));
			    			//System.out.println("Liczba pierwsza : "+pierwsza);
			    			return pierwsza.toString();
			    		}
			    		public static String ZnajdzPierwsza(int ileBitow,String dane)
			    		{
			    			int licznik=0;
		    	            BigInteger pierwsza;
		    	            long start = System.nanoTime();
		    	            do {
		    	            	pierwsza =new BigInteger(GenerateString(ileBitow),2);
		    	                //pierwsza = new BigInteger(ileBitow, rnd);
		    	               //pierwsza = BigInteger.probablePrime(ileBitow, rnd);
		    	                licznik++;
		    	            } while (!miller_rabin(pierwsza));
		    	            long end = System.nanoTime();		    	        
		    	            //pierwsza.pow(10);
		    	            System.out.println("Liczba pierwsza : "+pierwsza);
		    	            System.out.println("D³ugosc liczby : "+pierwsza.bitLength()+" bitów"+" | Przetestowano : "+licznik+" liczb");
		    	            double czas = (end-start)*0.000000001;
		    	            System.out.println("Czas wykonania : "+czas+" sec");
		    	            return pierwsza.toString();
			    		}
			    	    
}
