package av01_t1;

import java.util.ArrayList;

class Counter {
    protected int count;
    
    Counter() {
        count = 0;
    }
    
    public int getCount() {
        return count;
    }
    
    public void incrementCount() {
        count++;
    }
    
    public void decrementCount() {
        count--;
    }
}

public class AV01_t1 {
    public enum Day {MON, TUE, WED};
    
    public static void main(String[] args) {
        Counter c = new Counter();
        c.incrementCount();
        System.out.println(c.getCount());
        
        boolean flag = false;
        char ch = 'A';
        int i = 5;
        long l = 890l;
        float f = 3.14159F;
        double d = 2.18921;
        
        System.out.println("flag = " + flag);
        System.out.println("ch = " + ch);
        System.out.println("i = " + i);
        System.out.println("l = " + l);
        System.out.println("f = " + f);
        System.out.println("d = " + d);
        
        Integer ii = new Integer(5);
        System.out.println(i == ii.intValue());
        
        String s1 = new String("Test");
        String s2 = "Test";
        char [] charArray = {'a', 'b', 'c'};
        String s3 = new String(charArray);
        
        System.out.println(s1 + ' ' + s2 + s3);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        
        System.out.println(s1.compareTo(s3));
        System.out.println(s1.startsWith("Te"));
        System.out.println(s1.indexOf("es"));
        System.out.println(s1.length());
        System.out.println(s1.toUpperCase());
        s1 = s1.toUpperCase();
        System.out.println(s1.replace('T', '*'));
        
        System.out.println("1" + 1);
        System.out.println('1' + 1);
        System.out.println("1" + 1 + 1);
        System.out.println("1" + (1 + 1));
        System.out.println('1' + 1 + 1);
        
        Day day = Day.MON;
        System.out.println(day);
        System.out.println(Day.valueOf("TUE"));
        
        int [] array = new int[10];
        int [] array_copy;
        
        for(int x = 0; x < array.length; x++) {
            array[x] = x;
            System.out.print(array[x] + ", ");
        }
        
        System.out.println();
        
        array_copy = array.clone();
        for(int x = 0; x < array_copy.length; x++) {
            System.out.print(array_copy[x] + ", ");
        }
        
        System.out.println();
        
        ArrayList<Integer> array_1 = new ArrayList<Integer>(10);
        for (int x = 0; x < 10; x++) {
            array_1.add(x + 1);
            
            if (x == 5) {
                array_1.set(x, 12);
            }
            
            System.out.print(array_1.get(x) + ", ");
        }
        
        
        
    }
}
