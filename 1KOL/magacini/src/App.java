
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Stack<Integer> mag = new Stack<Integer>();
        mag.push(3);
        mag.push(2);
        mag.push(1);
        mag.push(1);

        Preraboti(mag);
        }
        
        private static void Preraboti(Stack<Integer> mag) {
            Stack<Integer> pom = new Stack<Integer>();
            while(!mag.isEmpty()){
                pom.push(mag.pop());
            }
            while(!pom.isEmpty()){
                int el = pom.pop();
                if(mag.isEmpty()){
                    mag.push(el);
                }else{
                    int elpom = mag.peek();
                    if(el == elpom){
                        mag.pop();
                        int pom5 = 5;
                        if(mag.isEmpty()){
                            mag.push(pom5);
                        }else if(mag.peek()<pom5){
                            mag.pop();
                            if(mag.isEmpty()){
                                mag.push(pom5);
                            }else if(mag.peek()<pom5){
                                mag.pop();
                                mag.push(pom5);
                                
                            }else{
                                mag.push(pom5);
                            }   
                        }else{
                            mag.push(pom5);
                        }
                    }else if(elpom<el) {
                        while(el>mag.peek() && !mag.isEmpty()){
                            mag.pop();
                        }
                        if(el == mag.peek()){
                            mag.pop();
                            int pom5 = 5;
                            if(mag.isEmpty()){
                                mag.push(pom5);
                            }else if(mag.peek()<pom5){
                                mag.pop();
                                if(mag.isEmpty()){
                                    mag.push(pom5);
                                }else if(mag.peek()<pom5){
                                    mag.pop();
                                    mag.push(pom5);
                                    
                                }else{
                                    mag.push(pom5);
                                }   
                            }else{
                                mag.push(pom5);
                            }
                        mag.push(el);
                    }else{
                        mag.push(el);
                    }
                }
            }
        }
}
