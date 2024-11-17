//N гости во еден ресторан, во ист временски момент, му порачуваат на готвачот N различни јадења. За секое од јадењата се знае колку време му е потребно на готвачот да го приготви. Секој од гостите, од моментот на нарачка, почнува да му брои казнени поени на готвачот. Му дава онолку казнени поени колку што време чека да му стигне јадењето. Кој е минималниот број на казнени поени кои може да ги добие готвачот.

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Внеси број на јадења: ");
        int N = scanner.nextInt();
        int[] vreme = new int[N];
        System.out.println("Внеси времиња за подготовка на секое јадење:");
        for (int i = 0; i < N; i++) {
            vreme[i] = scanner.nextInt();
        }
        Arrays.sort(vreme);
        int Penali = 0;
        int vremeJadenje = 0;
        
        for (int time : vreme) {
            vremeJadenje += time;  
            Penali += vremeJadenje;  
        }
        
        System.out.println("Минималниот број на казнени поени е: " + Penali);
    }
}