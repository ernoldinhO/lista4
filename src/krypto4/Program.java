package krypto4;

import java.io.IOException;
import java.util.Scanner;

public class Program {
	private static Scanner scanner;

	public static void main(String[] args) throws IOException {
		scanner = new Scanner(System.in);
		int k = 0;
		int d=256;
		//Dane wejsciowe : k d (k - ilosc liczb, d - dlugosc liczby)
	if(args.length==2)
	{
	d=Integer.parseInt(args[1]);
	k=Integer.parseInt(args[0]);
	Watki.odpalWatki(k, d,"wypisz");
	}
	else if(args.length==0)
	{
	System.out.println("NIE PODANO PARAMETROW, GENERUJE LICZBY PIERWSZE !");
	System.out.println("Podaj d³ugoœæ liczby pierwszej : ");
	d =Integer.parseInt(scanner.nextLine());
	System.out.println("Podaj ilosc liczb pierwszych : ");
	k =Integer.valueOf(scanner.nextLine());
	Watki.odpalWatki(k, d, "wypisz");
	}
	//Dane wejsciowe : Dec (deszyfruj)
	else if(args.length==1)
	{
		if(args[0].equals("Dec"))
		{
			long start = System.nanoTime();
			RSA.odczytPlikuTekstowegoD();
			long end = System.nanoTime();
			System.out.println("Odszyfrowalem");
			double czas = (end-start)*0.000000001;
            System.out.println("Czas wykonania : "+czas+" sec");
		}else System.out.println("Bledne dane");
	}
	//Dane wejsciowe : Enc k d (szyfruj k-ilosc skladowych klucza d-dlugosc skladowych)
	else if(args.length==3)
	{
		long start = System.nanoTime();
		k=Integer.parseInt(args[1]);
		d=Integer.parseInt(args[2]);
		Watki.odpalWatki(k, d,"");
		if(args[0].equals("Enc")){
			do{
			System.out.println("czekam na watki\n");
			}while (Lista.obliczone!=k);
			String plik="C:\\Users\\Erni\\Desktop\\testowo.txt";
			RSA.odczytPlikuTekstowegoE(plik);
			long end = System.nanoTime();
			System.out.println("Zaszyfrowalem");
			double czas = (end-start)*0.000000001;
            System.out.println("Czas wykonania : "+czas+" sec");
		}else System.out.println("Bledne dane"); 
	}
	  }
}
