//Дадена е низа од научни списанија, при што за секоје списание се знае местото кое го зафаќа на полица по широчина и височина. Дополнително, се знае должината на полиците на кои треба да се сместат списанијата. Кога со списанија ќе пополни едно ниво од полиците, се минува на следното ниво полици, при што висината на една полица зависи од највисокото списание поставено на полицата. Да се одреди минималната можна височина на полиците, откако на неа ќе бидат сместени сите списанија од низата.


import java.util.ArrayList;

class Spisanija{
    int h, w;
    public Spisanija(){
        h = 0;
        w = 0;
    }
    public Spisanija(int h, int w){
        this.h = h;
        this.w = w;
    }
}
class Polica{
    int h, w;
    final int maxWidth = 10;
    public Polica(){
        h = 0;
        w = 0;
    }
    public Polica(int h, int w){
        this.h = h;
        this.w = w;
    }
    public void setHeight(int h){
        this.h = h;
    }
    public void setWidth(int w){
        this.w = w;
    }
    public int getMaxWidth(){
        return maxWidth;
    }
    public void dodadiSirina(int w){
        this.w+=w;
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Spisanija> spis = new ArrayList<Spisanija>();
        Spisanija s1 = new Spisanija(30, 2);
        Spisanija s2 = new Spisanija(12, 4);
        Spisanija s3 = new Spisanija(16, 9);
        Spisanija s4 = new Spisanija(24, 6);
        Spisanija s5 = new Spisanija(5, 8);
        
        spis.add(s1);
        spis.add(s2);
        spis.add(s3);
        spis.add(s4);
        spis.add(s5);

        sort(spis);

        int maximun = najdivisina(spis);

        System.out.println(maximun);
    }
        
        private static void sort(ArrayList<Spisanija> spis) {
            for(int i = 0;i<spis.size()-1;i++){
                for(int j=i+1;j<spis.size();j++){
                    if(spis.get(j).h>spis.get(i).h){
                        Spisanija temp = spis.get(i);
                        spis.set(i, spis.get(j));
                        spis.set(j, temp);
                    }
                }
            }
        }

    private static int najdivisina(ArrayList<Spisanija> spis) {
        int maxHeight = 0;
        ArrayList<Polica> polici = new ArrayList<Polica>();
        for(int i = 0; i< spis.size();i++){
            Spisanija s = spis.get(i);
            if(polici.isEmpty()){
                Polica newPolica = new Polica();
                newPolica.setHeight(s.h);
                newPolica.setWidth(s.w);
                polici.add(newPolica);
                maxHeight += s.h;
            }else{
                int k = 0;
                for(int j = 0; j<polici.size();j++){
                    Polica pom = polici.get(j);
                    if((pom.getMaxWidth() - s.w )>= pom.w){
                        pom.dodadiSirina(s.w);
                        k=1;
                        // break;
                    }
                }
                if(k==0){
                Polica newPolica = new Polica();
                newPolica.setHeight(s.h);
                newPolica.setWidth(s.w);
                polici.add(newPolica);
                maxHeight += s.h;
                }
            }
        }

        return maxHeight;
    }
}

