import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Vnesi N za kvadratna matrica: ");
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int [][] mat = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                mat[i][j] = i+j;
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
        int pom;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                pom = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = pom;
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N/2;j++){
                pom = mat[i][j]; 
                mat[i][j] = mat[i][N-1-j];
                mat[i][N-1-j] = pom;
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
        
    }

}
