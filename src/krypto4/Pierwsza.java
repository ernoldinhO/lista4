package krypto4;

import java.math.BigInteger;
//import java.util.ArrayList;

// klasa implementuj¹ca interfejs Runnable
class Pierwsza implements Runnable {
 // pole napis przechowuj¹ce ³añcuch tekstowy
private int dlugosc;
private int id;
private String w;
private String liczba;
//public ArrayList<BigInteger> liczbyp=new ArrayList<BigInteger>();
//RSA rsa;
 // konstruktor klasy
 public Pierwsza(int dlugosc, int id, String w) {
  this.dlugosc = dlugosc;
  this.id = id+1;
  this.w = w;
 }

 // zaimplementowana metoda run
 public void run() {
	 if(w=="wypisz") liczba=MillerRabin.ZnajdzPierwsza(dlugosc,w);
	 else  liczba=MillerRabin.ZnajdzPierwsza(dlugosc);
			Lista.liczbyp.add(new BigInteger(liczba));		
			Lista.obliczone+=1;
	 		System.out.println("Watek : "+id+ " znalazl"+"\n -------------");
	 		
  }
}
