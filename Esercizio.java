//LEGGERE LE ISTRUZIONI NEL FILE README.md

//Import di Classi Java necessarie al funzionamento del programma
import java.util.Random;
import java.util.Scanner;

// Classe principale, con metodo main
class Esercizio {
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int a, n, m, i, j, e, ora, iMax;

        System.out.println("Inserisci il numero di pesci che saranno pescati: ");
        a = Integer.parseInt( input.nextLine() );

        // Alloco spazio al più per A animali, perchè nel caso peggiore il vettore non crescerà più di A elementi.
        int[] v = new int[a];
        int[] o = new int[a];

        // La dimensione effettiva del vettore inizialmente è 0.
        n = 0;
        m = 0;
        i = 1;
        ora = 9;
        while (i <= a) {

            // Genero un animale di dimensione fra 30 e 100 cm e lo inserisco in coda al vettore. Considero solo misure multiple di 10 (30, 40, 50, ecc...)
            e = (2 + random.nextInt(9) ) * 50;
            ora = (ora + 1) % 24;
            System.out.println("Pescato pesce di " + e + " grammi. ");
            n = inserisciInVettore(v, n, e, n);
            m = inserisciInVettore(o, m, ora, m);

            // Elimino tutti gli animali più piccoli arrivati prima
            j = 0;
            while (j < n) {
                if (v[j] < v[n - 1]) {
                    System.out.println(Integer.toString(v[j]) + " scappa via. ");
                    n = eliminaDaVettore(v, n, j);
                    m = eliminaDaVettore(o, m, j);
                } else {
                    j = j + 1;
                }
            }

            // Visualizzo lo stato del lago, dopo l'arrivo dell'ultimo animale
            System.out.println("Lago: ");
            visualizzaPescato(v, o, n);
            i = i + 1;
        }

        // Ricavi i dati sul pesce più pesante
        iMax = calcolaMassimo(v, n);
        System.out.println(Integer.toString(iMax) + "; Pesce più pesante: " + v[iMax] + "; pescato alle ore " + o[iMax]);
        n = rimuoviDuplicati(v, o, n);

        // Devo aggiornare la dimensione di O allo stesso valore della dimensione di V.
        m = n;
        System.out.println("Animali con dimensioni diverse rimaste al lago:");
        visualizzaPescato(v, o, n);
    }
    
    //Restituisce l'indice del massimo elemento di un vettore
    public static int calcolaMassimo(int[] v, int n) {
        int iMax;
        int i;

        iMax = 0;
        i = 0;
        while (i <= n - 1) {
            if (v[i] > v[iMax]) {
                iMax = i;
            }
            i = i + 1;
        }
        
        return iMax;
    }
    
    //Elimina un elemento da un vettore e restituisce la nuova dimensione del vettore
    public static int eliminaDaVettore(int[] v, int n, int ie) {
        int i, n2;

        n2 = n - 1;
        i = ie;
        while (i <= n - 2) {
            v[i] = v[i + 1];
            i = i + 1;
        }
        
        return n2;
    }
    
    //Inserisce un elemento in un vettore e restituisce la nuova dimensione del vettore
    public static int inserisciInVettore(int[] v, int n, int e, int ie) {
        int i, n2;

        n2 = n + 1;
        i = n2 - 1;
        while (i >= ie + 1) {
            v[i] = v[i - 1];
            i = i - 1;
        }
        v[ie] = e;
        
        return n2;
    }
    
    //Rimuove i duplicati dai vettori paralleli e restituisce la nuova dimensione dei vettori
    public static int rimuoviDuplicati(int[] v, int[] o, int n) {
        int i, j;

        // V e O sono paralleli, hanno stessa dimensione. Quindi la dimensione di O non la passo come parametro. La inizializzo da N, la dimensione di V.
        int m;

        m = n;
        i = 0;
        while (i <= n - 2) {
            j = i + 1;
            while (j <= n - 1) {
                if (v[i] == v[j]) {
                    System.out.println("Elimino " + v[j] + " da cella " + j);
                    n = eliminaDaVettore(v, n, j);
                    m = eliminaDaVettore(o, m, j);
                } else {
                    j = j + 1;
                }
            }
            i = i + 1;
        }
        
        return n;
    }
    
    //Visualizza i vettori paralleli, pesi e ore
    public static void visualizzaPescato(int[] v, int[] o, int n) {
        int i;

        i = 0;
        while (i < n) {
            System.out.println(Integer.toString(i) + "; peso: " + v[i] + "; ora: " + o[i]);
            i = i + 1;
        }
    }
    
    //Visualizza un vettore di interi
    public static void visualizzaVettore(int[] v, int n) {
        int i;

        i = 0;
        while (i < n) {
            System.out.println(Integer.toString(i) + ": " + v[i]);
            i = i + 1;
        }
    }
}
