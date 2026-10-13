
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
    //Can i like this???
    public void dodadiDete(BNode<E> dete, BNode<E> r){
        if(r.left != null){
            dodadiDete(dete, r.left);
        }else{
            addChild(r, 1, dete.info);
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
        BTree<Integer> drvo2 = new BTree(10);

        drvo.addChild(drvo.root, 1, 2);
        drvo.addChild(drvo.root, 2, 3);
        drvo.addChild(drvo.root.left, 1, 4);
        drvo.addChild(drvo.root.left, 2, 5);
        drvo.addChild(drvo.root.left.left, 1, 8);
        drvo.addChild(drvo.root.left.right, 1, 6);
        drvo.addChild(drvo.root.left.right.left, 2, 7);

        dodadiRoditeli(drvo.root, drvo2);

        drvo2.inorder(drvo2.root);

    }

    public static  void dodadiRoditeli(BNode<Integer> root, BTree<Integer> drvo){
        if(root == null){
            return;
        }
        if(root.left != null && root.right != null){
            if(Math.abs((root.left.info - root.right.info))==root.info){
                drvo.dodadiDete(root, drvo.root);
            }
            dodadiRoditeli(root.left, drvo);
            dodadiRoditeli(root.right, drvo);
        }else if (root.left != null && root.right == null) {
            dodadiRoditeli(root.left, drvo);
        }else if (root.left == null && root.right != null) {
            dodadiRoditeli(root.right, drvo);
        }else{
            return;
        }
    }
    
}
