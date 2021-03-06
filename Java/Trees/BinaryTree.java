/*
 * The MIT License
 *
 * Copyright 2015 cory.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Trees;

/**
 *
 * @author Cory Buck
 * @param <T1>  Key used for comparison, insertion, removal
 * @param <T2>  Value being stored
 * 
 * 
 * 
 */
public class BinaryTree<T1 extends Comparable<T1>,T2> implements TreeInterface<T1,T2>{
    private Node root;
    private int size;

    //initialize Binary tree
    public BinaryTree(){
        root = null;
        size = 0;
    }

    //insertion algorithms
    @Override
    public void insert(T1 key, T2 val){
        if(isEmpty()){
            root = new Node(key, val);
            size++;
        }else{
            insertHelper(new Node(key, val), root);
        }
    }

    public void insertHelper(Node temp, Node node){
        if(temp.compareTo(node) < 0){
            if(node.getLeftChild() == null){
                node.setLeftChild(temp);
                size++;
            }else{
                insertHelper(temp, node.getLeftChild());
            }
        }else if(temp.compareTo(node) > 0){
            if(node.getRightChild() == null){
                node.setRightChild(temp);
                size++;
            }else{
                insertHelper(temp,node.getRightChild());
            }
        }
    }

    //removal algorithms
    @Override
    public void remove(T1 key){
        if(!isEmpty()){
            if(key.compareTo(root.getKey()) == 0){
                if(root.getLeftChild() == null && root.getRightChild() == null) root = null;
                else if(root.getLeftChild() == null) root = root.getRightChild();
                else if(root.getRightChild() == null) root = root.getLeftChild();
                else{
                    Node temp2, temp3;
                    temp2 = temp3 = root.getRightChild();
                    while(temp3.getLeftChild() != null){
                        temp2 = temp3;
                        temp3 = temp3.getLeftChild();
                    }
                    root.setKey(temp3.getKey());
                    root.setValue(temp3.getValue());
                    temp2.setLeftChild(null);
                }                
                size--;
                
            } else removeHelper(key, root);
        }
    }
    
    public void removeHelper(T1 key, Node parent){
        //-----SCENARIOS-------
        //1. no subtree
        //2. one subtree
        //3. two subtrees
        if(parent.getLeftChild() != null){
            Node temp = parent.getLeftChild();
            if(key.compareTo(temp.getKey()) != 0){
                removeHelper(key, temp);
            }else{
                if(temp.getLeftChild() == null && temp.getRightChild() == null) parent.setLeftChild(null);
                else if(temp.getLeftChild() == null) parent.setLeftChild(temp.getRightChild());
                else if(temp.getRightChild() == null) parent.setLeftChild(temp.getLeftChild());
                else{
                    Node temp2, temp3;
                    temp2 = temp3 = temp.getRightChild();
                    while(temp3.getLeftChild() != null){
                        temp2 = temp3;
                        temp3 = temp3.getLeftChild();
                    }
                    temp.setKey(temp3.getKey());
                    temp.setValue(temp3.getValue());
                    temp2.setLeftChild(null);
                }
                size--;
            }
        }
        if(parent.getRightChild() != null){
            Node temp = parent.getRightChild();
            if(key.compareTo(temp.getKey()) != 0){
                removeHelper(key, temp);
            }else{
                if(temp.getLeftChild() == null && temp.getRightChild() == null) parent.setRightChild(null);
                else if(temp.getLeftChild() == null) parent.setRightChild(temp.getRightChild());
                else if(temp.getRightChild() == null) parent.setRightChild(temp.getLeftChild());
                else{
                    Node temp2, temp3;
                    temp2 = temp3 = temp.getRightChild();
                    while(temp3.getLeftChild() != null){
                        temp2 = temp3;
                        temp3 = temp3.getLeftChild();
                    }
                    temp.setKey(temp3.getKey());
                    temp.setValue(temp3.getValue());
                    temp2.setLeftChild(null);
                }
                size--;
            }
        }
    }
    
    @Override
    public T2 get(T1 key){
        if(!isEmpty()){
            return getHelper(key, root);
        }else return null;
    }
    
    public T2 getHelper(T1 key, Node temp){
        if(key.compareTo(temp.getKey()) < 0){ 
            if(temp.getLeftChild() != null) return getHelper(key, temp.getLeftChild());
            else return null;
        }else if(key.compareTo(temp.getKey()) > 0){
            if(temp.getRightChild() != null) return getHelper(key, temp.getRightChild());
            else return null;
        }else return temp.getValue();
    }

    //traversal algorithms
    @Override
    public void traversePreOrder(){
        System.out.println("\t-----------------Pre Order Traversal----------------------");
        if(root != null){
            traversePreOrderHelper(root);
        }else System.out.println("Tree has no values to show");
        System.out.println("\t----------------------------------------------------------");
    }

    public void traversePreOrderHelper(Node temp){
        System.out.println(temp.getKey() + " : " + temp.getValue());
        if(temp.getLeftChild() != null)traversePreOrderHelper(temp.getLeftChild());
        if(temp.getRightChild() != null)traversePreOrderHelper(temp.getRightChild());
    }
    
    @Override
    public void traverseInOrder(){
        System.out.println("---------------In Order Traversal of " + getSize() + " items------------------------");
        if(root != null){
            traverseInOrderHelper(root);
        }else System.out.println("Tree has no values to show");
        System.out.println("-----------------------------------------------------------------------");
    }

    public void traverseInOrderHelper(Node temp){
        if(temp.getLeftChild() != null)traverseInOrderHelper(temp.getLeftChild());
        System.out.println(temp.getKey() + " : " + temp.getValue());
        if(temp.getRightChild() != null) traverseInOrderHelper(temp.getRightChild());
    }
    
    @Override
    public void traversePostOrder(){
        System.out.println("---------------Post Order Traversal of " + getSize() + " items---------");
        if(!isEmpty()){
            traversePostOrderHelper(root);
        }else System.out.println("Tree has no values to show");
        System.out.println("-------------------------------------------------------------------------");
    }
    
    public void traversePostOrderHelper(Node temp){
        if(temp.getLeftChild() != null) traversePostOrderHelper(temp.getLeftChild());
        if(temp.getRightChild() != null) traversePostOrderHelper(temp.getRightChild());
        System.out.println(temp.getKey() + " : " + temp.getValue());
    }

    //returns size
    @Override
    public int getSize(){
        return size;
    }

    //checks if tree is empty
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    //---------------------------------------------------------------------------------//


    //node class
    public class Node implements Comparable<Node>{
        private T1 key;
        private T2 value;
        private Node left_child;
        private Node right_child;

        //constructors
        Node(T1 k, T2 v){
            key = k;
            value = v;
            left_child = null;
            right_child = null;
        }
        Node(T1 k, T2 v, Node lc){
            key = k;
            value = v;
            left_child = lc;
            right_child = null;
        }
        Node(T1 k, T2 v, Node lc, Node rc){
            key = k;
            value = v;
            left_child = lc;
            right_child = rc;
        }
        
        //comparator
        @Override
        public int compareTo(Node n){
            if(key.compareTo(n.getKey())< 0) return -1;
            else if(key.compareTo(n.getKey()) > 0) return 1;
            else return 0;
        }

        //setter methods
        public void setKey(T1 k){
            key = k;
        }
        public void setValue(T2 v){
            value = v;
        }
        public void setLeftChild(Node lc){
            left_child = lc;
        }
        public void setRightChild(Node rc){
            right_child = rc;
        }
        //getter methods
        public T1 getKey(){
            return key;
        }
        public T2 getValue(){
            return value;
        }
        public Node getLeftChild(){
            return left_child;
        }
        public Node getRightChild(){
            return right_child;
        }

    }

}
