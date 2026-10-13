
//Na kolku najmalku cepkanja moze da se podeli eden string taka sto sekoj da e palindorm


public class App {
    public static void main(String[] args) throws Exception {
        String str = "xabaay";
        System.out.println(minparticii(str,0,str.length()-1));
    }

    private static int minparticii(String str, int start, int end) {
        if(start >= end || isPalin(str,start,end)){
            return 0;
        }
        int result = Integer.MAX_VALUE ,count;

        for(int i = start; i < end; i++){
            count = minparticii(str, start, i) + minparticii(str, i+1, end) + 1;
            result = Math.min(result,count);
        }

        return result;
    }
    private static boolean isPalin(String str, int start, int end){
        while(start<end){
            if(str.charAt(start)!=str.charAt(end))
            {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
}
