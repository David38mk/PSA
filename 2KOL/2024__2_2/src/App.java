
import java.util.Stack;
import javax.swing.event.InternalFrameAdapter;

class BNode<E extends Comparable<E>>{
    public E info;
    public BNode<E> left,right;
    static int LEFT = 1, RIGHT = 2;

    public BNode(E info){
        this.info = info;
        this.left = null;
        this.right = null;
    }
    public BNode(E info, BNode<E> left, BNode<E> right){
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BTree<E extends Comparable<E>>{
    
    public BNode<E> root;

    public BTree(){
        root = null;
    }

    public BTree(E info){
        root = new BNode<E>(info);
    }

    public  BNode<E> addChild(BNode<E> node, int where, E x){
        BNode<E> temp = new BNode(x);
        
        if(where == BNode.LEFT){
            if(node.left!=null){
                return null;
            }
            node.left = temp;
        }else{
            if(node.right!=null){
                return null;
            }
            node.right = temp;
        }
        return temp;
    }


    //inorder izminuvanje na drvoso pomos na rekurzija
    public void inorder(BNode<E> r){
        if(r!=null){
            inorder(r.left);
            System.out.println(r.info);
            inorder(r.right);
        }
    }


    //preorder izminuvanje i pecatenje na drvo
    public void preorder(BNode<E> r){
        if(r!=null){
            System.out.println(r.info);
            preorder(r.left);
            preorder(r.right);
        }
    }


    //postorder izminuvanje na drvo i pecatenje
    public void postorder(BNode<E> r){
        if(r!=null){
            postorder(r.left);
            postorder(r.right);
            System.out.println(r.info);
        }
    }

    public void inorderUsingStack(BNode<E> r){
        Stack<BNode<E>> s = new Stack();
        
        BNode<E> p = root;

        while (true) { 
            while(p!=null){
                s.push(p);
                p = p.left;
            }

            if(s.isEmpty()){
                break;
            }

            p = s.pop();

            System.out.println(p.info.toString());

            p = p.right;


        }
    }

    public int numLeaves(BNode<E> r){
        if(r == null){
            return 0;
        }
        if(r.left == null && r.right==null){
            return 1;
        }
        else{
            return numLeaves(r.left) + numLeaves(r.right);
        }
    }

    public int insideNodes(BNode<E> r){
        if(r == null){
            return 0;
        }
        if(r.left == null && r.right==null){
            return 0;
        }
        else{
            return 1 + insideNodes(r.left) + insideNodes(r.right);
        }
    }

    //funkcija za proverka vo drvo lab2
    public boolean contains(E vrednost, BNode<E> r){
        if(r == null){
            return false;
        }else{
            if(r.info.equals(vrednost)){
                return true;
            }else{
                return contains(vrednost, r.left) || contains(vrednost, r.right);
            }
        }
    }
}
class Diameter {
    public int value;

    public Diameter() {
        this.value = 0;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        BTree<Integer> drvo = new BTree(1);

        drvo.addChild(drvo.root, 1, 2);
        drvo.addChild(drvo.root, 2, 3);
        drvo.addChild(drvo.root.left, 1, 4);
        drvo.addChild(drvo.root.left, 2, 5);
        drvo.addChild(drvo.root.left.left, 1, 8);
        drvo.addChild(drvo.root.left.right, 1, 6);
        drvo.addChild(drvo.root.left.right.left, 2, 7);

        Diameter dijameter = new Diameter();
        System.out.println(najdidlabocina(drvo.root));
        najdidijametar(drvo.root, dijameter);
        System.out.println(dijameter.value);

    }
    

    private static void najdidijametar(BNode<Integer> root, Diameter diametar) {
        if(root == null){
            return;
        }

        int levo = najdidlabocina(root.left);
        int desno = najdidlabocina(root.right);
        if(diametar.value < levo + desno + 1){
            diametar.value = 1 + levo + desno;
        }
        if(root.left != null){
            najdidijametar(root.left, diametar);
        }
        if(root.right != null){
            najdidijametar(root.right, diametar);
        }
    }

    private static int najdidlabocina(BNode<Integer> root){
        if(root == null){
            return 0;
        }
        if(root.left != null && root.right != null){
            return 1 + Math.max(najdidlabocina(root.left) , najdidlabocina(root.right));
        }else if (root.left != null && root.right == null) {
            return 1 + najdidlabocina(root.left);
        }else if (root.left == null && root.right != null) {
            return 1 + najdidlabocina(root.right);
        }else{
            return 1;
        }
    }
}
