
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Vnesi tekstualna niza:");
        String string = s.nextLine();
        System.out.println(otsrani(string));
    }

    private static String otsrani(String string){
        ArrayList<Character> tekst = new ArrayList<Character>();
        int i=0;
        while(i<string.length()){
            if (i + 1 < string.length() && Character.toLowerCase(string.charAt(i)) == Character.toLowerCase(string.charAt(i + 1)) &&string.charAt(i) != string.charAt(i + 1)){
                i+=2;
            }else{
                tekst.add(string.charAt(i));
                i++;
            }
        }
        
        string = "";
        for(int j=0;j<tekst.size();j++){
            string+=tekst.get(j);
        }
        return string;
    }
}
