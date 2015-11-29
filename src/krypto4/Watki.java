package krypto4;

//klasa tworz¹ca i uruchamiaj¹ca w¹tki
public class Watki {
public static void odpalWatki(int k, int bity, String w)
{ 
	Runnable[] runners = new Runnable[k];
	Thread[] threads = new Thread[k];

for(int i=0; i<k; i++) {
    runners[i] = new Pierwsza(bity, i , w);
}

for(int i=0; i<k; i++) {
    threads[i] = new Thread(runners[i]);
}

for(int i=0; i<k; i++) {
    threads[i].start();
}
}
}