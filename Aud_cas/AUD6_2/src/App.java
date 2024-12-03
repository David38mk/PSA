
import java.util.ArrayList;

//comparable go koristime poradi sto e genericki podatok E
class BNode<E extends Comparable<E>>{
    public E info;
    public BNode<E> left,right;
    

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

class BSTree<E extends Comparable<E>>{
    public BNode<E> root;

    public BSTree(){
        root = null;
    }

    public BSTree(E info){
        root = new BNode(info);
    }

    public BNode<E> insert(E info, BNode<E> r){
        if(r == null){
            return new BNode(info);
        }else{
            //compareTo vrakja -x za pomalo +x za pogolemo i 0 za ednakvi
            int comp = info.compareTo(r.info);
            if(comp<0){//left
                r.left = insert(info,r.left);
            }else if(comp>0){ //desno
                r.right = insert(info, r.right);
            }else{
                //ne pravi nisto
            }
            return r;
        }
    }   

    public boolean contains(E info,BNode<E> r){
        if(r == null){
            return false;
        }
        int comp = info.compareTo(r.info);
        if(comp<0){
            return contains(info, r.left);
        }else if(comp>0){
            return contains(info, r.right);
        }else{
            return true;
        }
    }

    public BNode<E> remove(E info,BNode<E> r){
        if(r == null){
            return r;
        }
        int comp = info.compareTo(r.info);
        if(comp<0){
            r.left = remove(info, r.left);
        }else if(comp>0){
            r.right = remove(info, r.right);
        }else{//brisi jazol info
            if(r.left != null && r.right != null){
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
                
            }else{
                if(r.left !=null){
                    return r.left;
                }else if (r.right != null) {
                    return r.right;
                }else{
                    return null;
                }
            }
        }
        return r;
    }

    private BNode<E> findMin(BNode<E> r){
        if(r==null){
            return null;
        }else if(r.left==null){
            return r;
        }else{
            return findMin(r.left);
        }
    }

    public void inorder(BNode<E> r){
        if(r!=null){
            inorder(r.left);
            System.out.print(r.info+" ");
            inorder(r.right);
        }
    }


    //funkcija za sporedba na drva
    public boolean checkTreeEqual(BNode<E> r1, BNode<E> r2){
        if(r1==null && r2 == null){
            return true;
        }

        if(r1!=null && r2!=null){
            return r1.info.compareTo(r2.info) == 0 && checkTreeEqual(r1.right, r2.right) && checkTreeEqual(r1.left, r2.left);
        }

        return false;
    }


    //dali listovi se isti
    public boolean checkIfLeavesEqual(BNode<E> r1, BNode<E> r2){
        if(r1.left==null && r2.left==null && r1.right==null && r2.right==null){
            return r1.info.compareTo(r2.info)==0;
        }else if(r1.left!=null && r2.left!=null && r1.right!=null && r1.right!=null){
            return checkIfLeavesEqual(r1.left, r2.left) && checkIfLeavesEqual(r1.right, r2.right);
        }else if (r1.left==null && r2.left==null && r1.right!=null && r1.right!=null) {
            return checkIfLeavesEqual(r1.right, r2.right);
        }else if (r1.left!=null && r2.left!=null && r1.right==null && r1.right==null) {
            return checkIfLeavesEqual(r1.left, r2.left);
        }
        return false;
    }

    //dali listovite se isti za neslicni drva
    public void getLeaves(BNode<E> r, ArrayList<E> list){
        if(r.left==null && r.right==null){
            list.add(r.info);
        }
        if(r.left!=null){
            getLeaves(r.left, list);
        }
        if(r.right!=null){
            getLeaves(r.right, list);
        }
    }

    //da se najde Ntiot minimalen clen
    static int count = 0;
    
    private BNode<E> findNMin(BNode<E> r,int N){
        if(r==null){
            return null;
        }
        BNode<E> left = findNMin(r.left, N);

        if(left!=null){
            return left;
        }

        count++;
        if(count==N){
            return r;
        }

        return findNMin(r.right, N);
    }


    //
}

public class App {
    
    public static BNode makeTree(int [] array, int left, int right){
        if(left>right){
            return null;
        }
        if(left == right){
            return new BNode<Integer>(array[left]);
        }
        int mid = (left+right) /2;
        BNode<Integer> root = new BNode<Integer>(array[mid]);
        root.left=makeTree(array, left, mid-1);
        root.right=makeTree(array, mid+1, right);
        return root;
    }
    
    public static void main(String[] args) throws Exception {
        int i;
        BSTree<Integer> tree = new BSTree(10);
        tree.insert(8, tree.root);
        tree.insert(9, tree.root);
        tree.insert(6, tree.root);
        tree.insert(7, tree.root);
        tree.insert(2, tree.root);
        tree.insert(3, tree.root);
        tree.insert(12, tree.root);
        tree.insert(11, tree.root);
        tree.insert(18, tree.root);
        tree.insert(20, tree.root);
        tree.insert(15, tree.root);
        tree.insert(15, tree.root);
        tree.insert(13, tree.root);
        tree.insert(16, tree.root);
        tree.insert(4, tree.root);
        tree.insert(1, tree.root);


        BSTree<Integer> tree1 = new BSTree(10);
        tree1.insert(8, tree1.root);
        tree1.insert(9, tree1.root);
        tree1.insert(6, tree1.root);
        tree1.insert(7, tree1.root);
        tree1.insert(2, tree1.root);

        BSTree<Integer> tree2 = new BSTree(10);
        tree2.insert(8, tree2.root);
        tree2.insert(9, tree2.root);
        tree2.insert(6, tree2.root);
        tree2.insert(7, tree2.root);
        tree2.insert(2, tree2.root);

        tree.inorder(tree.root);
        tree.remove(12, tree.root);
        System.out.println("");
        tree.inorder(tree.root);

        //proverka na drva
        System.out.println(tree.checkTreeEqual(tree.root,tree1.root));
        System.out.println(tree.checkTreeEqual(tree.root,tree.root));

        //proverka za isti listovi
        System.out.println(tree.checkIfLeavesEqual(tree.root,tree1.root));
        System.out.println(tree.checkIfLeavesEqual(tree.root,tree.root));

        //proverka za listi na neslicni drva
        //test na funk
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        tree1.getLeaves(tree1.root, list1);
        tree2.getLeaves(tree1.root, list2);
        if(list1.size()!=list2.size()){
            System.out.println("Error");
        }else{
            for(i= 0;i<list1.size();i++){
                if(list1.get(i)!=list2.get(i)){
                    System.out.println("Error");
                    break;
                }
            }
            if(i==list1.size()){
                System.err.println("Ednakvi se");
            }
        }
        System.out.println(list1.size());
        for(i=0;i<list1.size();i++){
            System.out.println(list1.get(i)+", ");
        }


        //binarno drvo so najmala mozna visocina
        BSTree<Integer> tree3 = new BSTree<Integer>();
        int [] array = {1,2,4,5,6,8,9};
        tree3.root = makeTree(array, 0, 6);
        tree3.inorder(tree3.root);
    }
}
