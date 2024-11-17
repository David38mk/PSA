//Студентите полагаат два предмети: ПСАА и ОС. За секој студент се води евиденција за името и предметот кој го полагаат. Сите студенти се веќе сместени во една заеднички ред според времето на пријавување на анкета на портал. Потребно е да се креираат две посебни редици, по една за секој од двата предмети. Сместувањето на студентите се случува на тој начин што од заедничкиот ред се вади по еден студент за ПСАА и по еден студент за ОС. Ако во редицата има два или повеќе последователни студенти пријавени за ист предмет, тогаш првиот се додава во редот за соодветниот предмет, а останатите се враќаат на крајот на заедничката редица, се дури не се дојде до студент кој полага различен предмет. Постапката продолжува се додека не се групираат сите студенти во една листа.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Student {
    private String ime, predmet;

    public Student(String ime, String predmet) {
        this.ime = ime;
        this.predmet = predmet;
    }

    public void pechati() {
        System.out.println(ime + " " + predmet);
    }

    public String getP() {
        return predmet;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Queue<Student> studentiALL = new LinkedList<Student>();
        int N;
        System.out.println("Vnesi broj na studenti: ");
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        s.nextLine(); 

        String ime;
        int pred;
        for (int i = 0; i < N; i++) {
            System.out.println("Vnesi ime na studenti: ");
            ime = s.nextLine();
            System.out.println("Vnesi predmet(0 za PSAA): ");
            pred = s.nextInt();
            s.nextLine();  
            if (pred == 0) {
                Student st = new Student(ime, "PSAA");
                studentiALL.add(st);
            } else {
                Student st = new Student(ime, "OS");
                studentiALL.add(st);
            }
        }

        Queue<Student> PSAA = new LinkedList<Student>();
        Queue<Student> OS = new LinkedList<Student>();

        Order(studentiALL, PSAA, OS);

        while (!PSAA.isEmpty()) {
            Student pom = PSAA.remove();
            pom.pechati();
        }
        System.out.println("kraj psa");
        while (!OS.isEmpty()) {
            Student pom = OS.remove();
            pom.pechati();
        }
    }

    private static void Order(Queue<Student> studentiALL, Queue<Student> PSAA, Queue<Student> OS) {
        String posledenPredmet = "";

        while (!studentiALL.isEmpty()) {
            Student pom = studentiALL.remove();
            if (!pom.getP().equals(posledenPredmet)) {
                posledenPredmet = pom.getP();

                if (posledenPredmet.equals("PSAA")) {
                    PSAA.add(pom);
                } else {
                    OS.add(pom);
                }
            } else {
                studentiALL.add(pom);
                while (!studentiALL.isEmpty() && studentiALL.peek().getP().equals(posledenPredmet)) {
                    studentiALL.add(studentiALL.remove());
                }
            }
        }
    }
}
