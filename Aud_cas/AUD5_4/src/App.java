//n logn kompleksnost; 

public class App {
    public static void main(String[] args) throws Exception {
            int [] a = {14,2,3,13,10,9,5,6,7};

            mergesort(a, 0, a.length-1);

            for(int i = 0;i<a.length;i++){
                System.out.print(a[i] + ", ");
            }
    }

    private static void mergesort(int [] a, int l, int r){
        if(l == r){
            return;
        }

        int mid = (l+r)/2;
        mergesort(a, l, mid);
        mergesort(a, mid+1, r);
        merge(a, l, mid, r);
    }

    private static void merge(int []a, int l,int mid, int r){
        int brel = r - l +1;
        int pom[] = new int[a.length];
        int i=l, j=mid+1, k = 0;

        while(i<=mid && j<=r){
            if(a[i]<a[j]){
                pom[k]=a[i++];
                k++;
            }else{
                pom[k]=a[j++];
                k++;
            }
        }

        while (i<=mid) { 
            pom[k++] = a[i++];
        }
        while (j<=r){
            pom[k++] = a[j++];
        }
        for(k=0;k<brel;k++){
            //l poradi toa sto podelbata ja pravime od l pa mora i od tamu da ja vratime
            a[l+k] = pom[k];
        }
    }
}
