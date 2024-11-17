

public class App {
    public static void main(String[] args) throws Exception {
        int [] pillars = {7,0,4,2,5,0,6,4,0,5};
        System.out.println(WaterDist(pillars));
    }

    private static int WaterDist(int[] pillars) {
        int left = 0, right =pillars.length -1;
        int localArea, maxArea = 0;
        while(left<right){
            localArea = (right-left) * Math.min(pillars[left],pillars[right]);
            maxArea = Math.max(localArea, maxArea);
            if(pillars[left]<pillars[right]){
                left++;
            }else{
                right--;
            }
        }


        return maxArea;
    }
}
