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


    public int proveriDaliVazi(BNode<E> root){
        if(root==null){
            return 0;
        }
        if(root.left!=null && root.right!=null){
            // if(proveriDaliVazi(root.left) + proveriDaliVazi(root.right) > 0){
            //     return 1;
            // }else{
            //     return 0;
            // }
            if(proveriDaliVazi(root.left)>0){
                return 1;
            }
            if(proveriDaliVazi(root.right)>0){
                return 1;
            }
            return 0;
        }else if(root.left != null && root.right == null){
            if(root.left.right == null){
                return 1;
            }else{
                return proveriDaliVazi(root.left);
            }
        }else if(root.left == null && root.right != null){
            return proveriDaliVazi(root.right);
        }else{
            return 0;   
        }
    }

}


public class App {
    public static void main(String[] args) throws Exception {
        BSTree<Integer> drvo = new BSTree<Integer>(10);

        drvo.insert(8, drvo.root);
        drvo.insert(5, drvo.root);
        drvo.insert(7, drvo.root);
        drvo.insert(6, drvo.root);
        drvo.insert(9, drvo.root);
        drvo.insert(12, drvo.root);
        drvo.insert(11, drvo.root);
        drvo.insert(15, drvo.root);
        drvo.insert(13, drvo.root);

        drvo.inorder(drvo.root);

        if(drvo.proveriDaliVazi(drvo.root)==1){
            System.out.println("Vazi");
        }else{
            System.out.println("Ne vazi");
        }
    }
}
