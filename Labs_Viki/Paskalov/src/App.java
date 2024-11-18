
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Vnesi Nti red od paskalov triagolnik: ");
        int N;
        Scanner s = new Scanner(System.in);
        N = s.nextInt();

        pascal(N);
    }

    private static void pascal(int N) {
        ArrayList<ArrayList<Integer>> triagolnik = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int pom = triagolnik.get(i - 1).get(j - 1) + triagolnik.get(i - 1).get(j);
                    row.add(pom);
                }
            }
            triagolnik.add(row);
        }

        // Print Pascal's Triangle
        for (int i = 0; i < triagolnik.size(); i++) {
            for (int j = 0; j < triagolnik.get(i).size(); j++) {
                System.out.print(triagolnik.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
