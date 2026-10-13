import java.util.ArrayList;

class Apteka{
    protected int brLek;
    protected ArrayList<String> lekovi = new ArrayList<String>();
    protected float zarabotuvacka;
    public Apteka(){
        brLek = 0;
        lekovi = new ArrayList<String>();
        zarabotuvacka = 0;
    }
    public Apteka(int brLek, ArrayList<String> lekovi){
        this.brLek = brLek;
        this.lekovi = lekovi;
        this.zarabotuvacka = 300*brLek;
    }
    public float getZarabotka(){
        return zarabotuvacka;
    }
    public void pechati(){
        System.out.println("Broj na lekovi: "+brLek);
        for(int i=0;i<brLek;i++){
            System.out.println(lekovi.get(i));
        }
        System.out.println("Zarabotuvacka: "+ getZarabotka());
    }
}
class Gradska extends Apteka{
    private boolean w24;
    public Gradska(){
        super();
        w24 = false;
    }
    public Gradska(int brLek, ArrayList<String> lekovi, boolean w24){
        super(brLek, lekovi);
        this.w24 = w24;
    }
    @Override
    public float getZarabotka(){
        if(w24){
            return (float) (zarabotuvacka + zarabotuvacka*0.3);
        }else{
            return zarabotuvacka;
        }
    }
}

class Klinicka extends Apteka{
    private int brKliniki;
    public Klinicka(){
        super();
        brKliniki=0;
    }
    public Klinicka(int brLek, ArrayList<String> lekovi,int brKliniki){
        super(brLek,lekovi);
        this.brKliniki = brKliniki;
    }
    @Override
    public float getZarabotka(){
        float zarab = zarabotuvacka + (float) (brKliniki*zarabotuvacka*0.2);
        return zarab;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<String> lekovi = new ArrayList<>();
        lekovi.add("Paracetamol");
        lekovi.add("Ibuprofen");

        Gradska gradskaApteka = new Gradska(2, lekovi, true);
        gradskaApteka.pechati();

        Klinicka klinickaApteka = new Klinicka(2, lekovi, 3);
        klinickaApteka.pechati();
    }
}
