//binarno drvo

import java.util.Stack;

class BNode<E>{
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

class BTree<E>{
    
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
}

public class App {
    public static void main(String[] args) throws Exception {
        BTree<Integer> drvo = new BTree<Integer>(10);
        drvo.addChild(drvo.root, 1, 5);
        drvo.addChild(drvo.root.left, 1, 4);
        drvo.addChild(drvo.root, 0, 7);
        drvo.addChild(drvo.root.left, 0, 2);
        drvo.addChild(drvo.root.right, 1, 3);

        drvo.inorder(drvo.root);
        System.out.println("");
        drvo.preorder(drvo.root);
        System.out.println("");
        drvo.postorder(drvo.root);
        System.out.println("");
        drvo.inorderUsingStack(drvo.root);
        System.out.println("");
        System.err.println(drvo.numLeaves(drvo.root));
    }
}