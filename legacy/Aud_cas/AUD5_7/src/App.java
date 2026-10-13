import java.util.ArrayList;
import java.util.Stack;

class stackSet{
    public int capacity;
    ArrayList<Stack> stacks = new ArrayList<Stack>();

    public stackSet(int capacitet){
        this.capacity = capacitet;
    }

    public Stack getLastStack(){
        if(stacks.size() == 0)
            return null;
        return stacks.get(stacks.size()-1);
    }
    
    public void pushElement(int value){
        Stack last = getLastStack();

        if(last != null && last.size() != capacity){
            last.push(value);
        }else{
            Stack stack = new Stack();
            stack.push(value);
            stacks.add(stack);
        }
    }

    public void pushStackAt(int index, Stack s){
        if(s.size() == capacity){
            stacks.add(index, s);
        }
    }

    public Stack popStackAt(int index){
        Stack stack = stacks.get(index);

        stacks.remove(index);

        return stack;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        stackSet ss = new stackSet(3);
        for(int i = 1; i<11;i++){
            ss.pushElement(i);
        }
        Stack s = ss.popStackAt(2);
        ss.pushStackAt(1, s);
    }
}
