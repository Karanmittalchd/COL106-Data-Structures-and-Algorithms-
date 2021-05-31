package col106.assignment3.BST;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;
//Root analysis for deletion
//Two children deletion


public class BST<T extends Comparable, E extends Comparable> implements BSTInterface<T, E>  {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code
	 * start writing your code from here
	 */
	class Node{
	Node left;
	Node right;
	String name;
	String district;
	String state;
	String constituency;
	String party;

	T k;
	E v;
		Node(T key, E value){
			k = key;
			v = value;
		}
		Node(){
			left = null;
			right= null;
		}
	}

	public int nodes=0;
	public Node root;
	public Node createNode(T key, E value, String name, String district, String state, String constituency, String party){
		BST.Node n1 = new BST.Node(key,value);
		n1.name = name;
		n1.district = district;
		n1.state = state;
		n1.constituency = constituency;
		n1.party = party;
		return n1;
	}
	public Node mainN = new Node();
	//write your code here 
    public void insert(T key, E value) {
		//write your code here
		int n = this.nodes;
		BST.Node n1 = createNode(key,  value, mainN.name, mainN.district, mainN.state, mainN.constituency, mainN.party);
		//BST.Node n1 = createNode(key,value,n1.name1,n1.district1,n1.state1,n1.constituency1,n1.party1);
		if(n==0)
			this.root = n1;
		BST.Node n2 = this.root;
		if(n>0){
			while(true){
				
				if((n1.v).compareTo(n2.v)<0){
					if(n2.left==null){
						n2.left = n1;
						break;
					}
					n2 = n2.left;
				}else{
					if(n2.right==null){
						n2.right = n1;
						break;
					}
					n2 = n2.right;
				}
			}

    	}
    	this.nodes++;

    }

    public void update(T key, E value) {
		//write your code here
		delete(key);
		insert(key,value);
    }

    public void delete(T key) {
		//write your code here
		
		Vector<Node> v1 = this.hmap();
		E val = (v1.get(0).v);
		for(int i=0;i<v1.size();i++){
			if(key==(v1.get(i)).k){
				val = (v1.get(i)).v;
				break;
			}
		}
		int isroot = 0;
		Node n1 = this.root;
		if(val.equals(n1.v)){
			isroot = 1;
		}
		if(isroot==1){
			int children = 0;
			if(n1.left!=null){
				children++;
			}
			if(n1.right!=null){
				children++;
			}
			if(children==0){
				this.root = null;
			}
			else if(children==1){
				if(n1.left!=null){
					this.root = n1.left;
				}else{
					this.root = n1.right;
				}
			}
			else{
				int flag1=0;
				Node nc1 = (this.root).left;
				Node nc2 = (this.root).right;
				Node nc2_1 = nc2;
				Node nc2p = this.root;
				if(nc2_1.left!=null){
					nc2_1 = nc2_1.left;
					nc2p = nc2p.right;
					flag1 = 1;
				}
				while(nc2_1.left!=null){
					nc2_1 = nc2_1.left;
					nc2p = nc2p.left;
					flag1 = 1;
				}
				if(flag1==0){
					this.root = nc2_1;
					nc2_1.left = (n1).left;
				}else if(flag1==1 && nc2_1.right==null){
					this.root = nc2_1;
					nc2_1.left = (n1).left;
										
					nc2p.left = null;
					nc2_1.right = nc2;
				}else if(flag1==1 && nc2_1.right!=null){
					//System.out.println("Hey");
					this.root = nc2_1;
					
					nc2_1.left = n1.left;
										
					nc2p.left = nc2_1.right;
					nc2_1.right = nc2;
				}

			}

		}else{

			
			Node nf = new Node();
			while(true){
				if(n1.left!=null && val.equals((n1.left).v)){
					nf = n1.left;
					break;
				}
				if(n1.right!=null && val.equals((n1.right).v)){
					nf = n1.right;
					break;
				}
				if(val.compareTo(n1.v)<0){
					n1 = n1.left;
					continue;
				}
				if(val.compareTo(n1.v)>0){
					n1 = n1.right;
				}

			}
			Node nc1 = new Node();
			Node nc2 = new Node();
			int children = 0;
			if(nf.left!=null){
				children++;
				nc1 = nf.left;
			}
			if(nf.right!=null){
				
				if(children==1)
					nc2 = nf.right;
				else
					nc1 = nf.right;
				children++;
			}
			if(children==0){
				if(val.compareTo(n1.v)>0)
					n1.right = null;
				else
					n1.left = null;
				
			}
			if(children==1){
				if(val.compareTo(n1.v)>0)
					n1.right = nc1;
				else
					n1.left = nc1;
			}
			Node nc2_1 = nc2;
			Node nc2p =  nf;
			int flag2=0;
			int flag1=0;
			if(children==2){
				if(nc2_1.left!=null){
					nc2_1 = nc2_1.left;
					nc2p = nc2p.right;
					flag1 = 1;
				}
				while(nc2_1.left!=null){
					nc2_1 = nc2_1.left;
					nc2p = nc2p.left;
					flag1 = 1;
				}
				if(flag1==0){
					if(val.compareTo(n1.v)<0)
						n1.left = nc2_1;
					if(val.compareTo(n1.v)>0)
						n1.right = nc2_1;
					
					nc2_1.left = nf.left;
				}else if(flag1==1 && nc2_1.right==null){
					if(val.compareTo(n1.v)<0)
						n1.left = nc2_1;
					if(val.compareTo(n1.v)>0)
						n1.right = nc2_1;
					
					nc2_1.left = nf.left;
										
					nc2p.left = null;
					nc2_1.right = nc2;
				}else if(flag1==1 && nc2_1.right!=null){
					//System.out.println("Hey");
					if(val.compareTo(n1.v)<0)
						n1.left = nc2_1;
					if(val.compareTo(n1.v)>0)
						n1.right = nc2_1;
					
					nc2_1.left = nf.left;
										
					nc2p.left = nc2_1.right;
					nc2_1.right = nc2;
				}

			}
		}
		this.nodes--;

    }

    public void printBST () {
		Vector<Node> v1 = this.hmap();
		for(int q=0;q<v1.size();q++){
			System.out.println((v1.get(q)).k + ", " + (v1.get(q)).v); 
		}
	}
	public Vector<Node> hmap(){
		int i=0;
		Node n1 = this.root;
		Vector<Node> v1 = new Vector<Node>();
		v1.add(n1);
		int j=i+1;
		int k=j;
		while(true){
			
			j=k;
			int tempj=j;

			for(int a=i;a<j;a++){
				Node n2 = v1.get(a);
				if(n2.left!=null){
					v1.add(n2.left);
					k++;
				}
				if(n2.right!=null){
					v1.add(n2.right);
					k++;
				}
			}
			if(k==tempj)
				break;
			i = tempj;
		}
		return v1;
	}
	// public static void main(String[] args){
	// 	BST<Integer,Integer> b1 = new BST();
	// 	b1.insert(1,1200);
	// 	b1.insert(2,1000);
	// 	b1.insert(3,1500);
	// 	b1.insert(4,2000);
	// 	b1.insert(5,500);
	// 	b1.insert(6,4000);
	// 	b1.insert(7,2100);
	// 	b1.insert(8,800);
	// 	b1.insert(9,1600);
	// 	//b1.insert(10,23);
	// 	b1.printBST();
	// 	System.out.println("TMKBMC");
	// 	b1.delete(1);
	// 	b1.delete(5);
	// 	b1.delete(3);
	// 	b1.printBST();

	// 	//System.out.println("TMKBMC");
	// 	//b1.update(9,300);
	// 	//b1.printBST();

	// 	//System.out.println("TMKBMC");
	// }
			

}