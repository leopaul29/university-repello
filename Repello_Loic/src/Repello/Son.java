package Repello;

/**
 * 
 * 
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;


// TODO: Auto-generated Javadoc
/**
 * La  Class Son.
 */
public class Son {
    
    /** Le fichier. */
    private String fichier;
    
    /** Le play. */
    private Player play; 

    /**
     * Constructeur de son.
     *
     * @param filename Constructeur
     */
    public Son(String filename) {
        this.fichier = filename;
    }
    
    /**
     * Cette méthode permet d'arreter un son en cour.
     */
    public void close() { if (play != null) play.close(); }

    /**
     * 
     * Cette méthide permet de lire une fois un son.
     * 
     */
    public void jouerUnefois() {
        try {
            FileInputStream f     = new FileInputStream(fichier);
            BufferedInputStream b = new BufferedInputStream(f);
            play = new Player(b);
        }
        catch (Exception e) {
            System.out.println("Probleme au niveau du " + fichier);
            System.out.println(e);
        }

        // On lance le son dans un thread
        //
        new Thread() {
            public void run() {
                try { play.play(); }
                catch (Exception e) { System.out.println(e); }
                
                close();
            }
        }.start();

    }


    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        String filename = new String("A.mp3");
        Son mp3 = new Son(filename);
        mp3.jouerUnefois();

    }

}
