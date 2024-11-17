public class App {
    public static void main(String[] args) throws Exception {
        int [][] mat = new int[10][4];
        for(int i =0;i<10;i++){
            for(int j=0;j<4;j++){
                mat[i][j] = i+j;

            }
        }
        System.out.println(maxZbir(mat,10,4));
    }
        
    private static int maxZbir(int[][] a, int m, int n) {
            int [][] b = new int[m][n];

            b[0][0] = a[0][0];

            for(int i = 1;i<m;i++){
                b[i][0] = b[i-1][0]+a[i][0];
            }
            for(int j =1;j<n;j++){
                b[0][j] = b[0][j-1]+a[0][j];
            }
            for(int i =1;i<m;i++){
                for(int j=1;j<n;j++){
                    b[i][j] = Math.max(b[i-1][j],b[i][j-1]) + a[i][j];
                }
            }
            return b[m-1][n-1];
        }
}
