
import java.util.ArrayList;
import java.util.Scanner;


class Bukva{
    char znak;
    int count;
    public Bukva(char znak, int count){
        this.znak = znak;
        this.count = count;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        String s1, s2;
        Scanner s = new Scanner(System.in);
        s1 = s.nextLine();
        s2 = s.nextLine();
        char c;
        ArrayList<Bukva> prva = new ArrayList<>();
        ArrayList<Bukva> vtora = new ArrayList<>();
        for(int i=0;i<s1.length();i++){
            c = s1.charAt(i);
            if(c==' '){
                continue;
            }
            int k =0;
            for(int j=0;j<prva.size();j++){
                if(c==prva.get(j).znak){
                    prva.get(j).count++;
                    k=1;
                }
            }
            if(k==0){
                Bukva b = new Bukva(c, 1);
                prva.add(b);
            }
        }
        for(int i=0;i<s2.length();i++){
            c = s2.charAt(i);
            if(c==' '){
                continue;
            }
            int k =0;
            for(int j=0;j<vtora.size();j++){
                if(c==vtora.get(j).znak){
                    vtora.get(j).count++;
                    k=1;
                }
            }
            if(k==0){
                Bukva b = new Bukva(c, 1);
                vtora.add(b);
            }
        }

        boolean sporedi = Sporedba(prva,vtora);

        if(sporedi){
            System.out.println("Da se");
        }else{
            System.out.println("Ne ne se");
        }

    }

    private static boolean Sporedba(ArrayList<Bukva> prva, ArrayList<Bukva> vtora) {
        boolean dali = true;
        for(int i=0;i<prva.size();i++){
            boolean k = false;
            for(int j=0;j<vtora.size();j++){
                if(prva.get(i).znak == vtora.get(j).znak && prva.get(i).count == vtora.get(j).count){
                    k = true;
                }
            }
            if(k==false){
              dali = false;
              break;       
            }
        }

        return dali;
    }
}
