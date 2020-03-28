package col106.assignment3.Election;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;
//import java.text.DecimalFormat;

public class Election implements ElectionInterface {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}
	/*
	 * end code
	 */
public BST<String,Integer> b1 = new BST(); 

    public void insert(String name, String candID, String state, String district, String constituency, String party, String votes){
		//write your code here 
		//BST<String,String> b = new BST();
		
		
		//BST<String,String> b1 = new BST();
		b1.mainN.name = name;
		b1.mainN.state = state;
		b1.mainN.district = district;
		b1.mainN.constituency = constituency;
		b1.mainN.party = party;

		
		b1.insert(candID, Integer.parseInt(votes));

	}
	public void updateVote(String name, String candID, String votes){
		
		//write your code here
		int a=0;
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		for(int q=0;q<v1.size();q++){
			if((v1.get(q)).k.equals(candID)){
				a=q;
				break;
			}
		}
		String state1 = v1.get(a).state;
		String district1 = v1.get(a).district;
		String constituency1 = v1.get(a).constituency;
		String party1 = v1.get(a).party;
		b1.delete((v1.get(a)).k);
		this.insert(name,candID,state1,district1,constituency1,party1,votes);
	}
	public void topkInConstituency(String constituency, String k){
		//write your code here
		int a = 0;
		Vector<Integer>index = new Vector<Integer>();
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		for(int q=0;q<v1.size();q++){
			if((v1.get(q)).constituency.equals(constituency)){
				index.add(q);
			}
		}
		int temp;
		for(int i=0;i<index.size();i++){
			for(int j=i;j<index.size();j++){
				if(v1.get(index.get(i)).v.compareTo(v1.get(index.get(j)).v)<0){
					temp = index.get(i);
					index.set((i) ,index.get(j));
					index.set((j) , temp);
				}
			}
		}
		int count1 = Integer.parseInt(k);
		if(index.size()<Integer.parseInt(k))
			count1 = index.size();
		 for(int c=0;c<count1;c++){
	 		System.out.println(v1.get(index.get(c)).name+", " +v1.get(index.get(c)).k+", " + v1.get(index.get(c)).party);
		 }

	}
	public void leadingPartyInState(String state){
	//write your code here
		Vector<Integer>index = new Vector<Integer>();
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		for(int i=0;i<v1.size();i++){
			if(v1.get(i).state.equals(state)){
				index.add(i);
			}
		}
		Vector<String>pparty = new Vector<String>();
		Vector<Integer>partyv = new Vector<Integer>();
		for(int a=0;a<index.size();a++){
			if(pparty.contains(v1.get(index.get(a)).party)){
				int i = pparty.indexOf(v1.get(index.get(a)).party);
				int ans = partyv.get(i) + (int)v1.get(index.get(a)).v;
				partyv.set(i,ans); 
				continue;
			}
			pparty.add(v1.get(index.get(a)).party);
			partyv.add((int)v1.get(index.get(a)).v);

		}
		// for(int j=0;j<partv.size()-1;j++){
		// 	for(int q=j+1;q<partv.size();q++)
			
		// }
		index.clear();
		Vector<String>winner = new Vector<String>();
		int tempvotes = 0;
		String tempparty = new String();
		for(int p=0;p<partyv.size();p++){
			for(int q=p;q<partyv.size();q++){
				if(partyv.get(p)<partyv.get(q)){
					tempvotes = partyv.get(q);
					partyv.set((q),partyv.get(p));
					partyv.set((p),tempvotes);
					tempparty = pparty.get(q);
					pparty.set((q),pparty.get(p));
					pparty.set(p,tempparty);
				}
			}
		}
		int votes1 = partyv.get(0);
		int count2=0;
		for(int c=0;c<partyv.size();c++){
			if(partyv.get(c)==votes1){
				count2++;
			}
		}
		String temp = new String();
		for(int c1=0;c1<count2;c1++){
			for(int c2=c1;c2<count2;c2++){
				if(pparty.get(c1).compareTo(pparty.get(c2))>0){
					temp = pparty.get(c1);
					pparty.set(c1,pparty.get(c2));
					pparty.set(c2,temp);
				}
			}
		}
		for(int c3=0;c3<count2;c3++){
			System.out.println(pparty.get(c3));
		}

	}
	public void cancelVoteConstituency(String constituency){
		//write your code here
		int a = 0;
		Vector<String>index = new Vector<String>();
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		for(int q=0;q<v1.size();q++){
			if((v1.get(q)).constituency.equals(constituency)){
				index.add((v1.get(q)).k.toString());
			}
		}
		for(int w=0;w<index.size();w++){
			for(int w1 = w;w1<index.size();w1++){
				if(index.get(w).compareTo(index.get(w1))>0){
					String s1 = index.get(w);
					index.set(w,index.get(w1));
					index.set(w1,s1);
				}
			}
			
		}
		for(int w2=0;w2<index.size();w2++){
			b1.delete(index.get(w2));
		}
	}
	public void leadingPartyOverall(){
		//write your code here
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		Vector<String>pparty = new Vector<String>();
		Vector<Integer>partyv = new Vector<Integer>();
		for(int a=0;a<v1.size();a++){
			if(pparty.contains(v1.get(a).party)){
				int i = pparty.indexOf(v1.get(a).party);
				int ans = partyv.get(i) + (int)v1.get(a).v;
				partyv.set(i,ans); 
				continue;
			}
			pparty.add(v1.get(a).party);
			partyv.add((int)v1.get(a).v);

		}
		int tempvotes = 0;
		String tempparty = new String();
		for(int p=0;p<partyv.size();p++){
			for(int q=p;q<partyv.size();q++){
				if(partyv.get(p)<partyv.get(q)){
					tempvotes = partyv.get(q);
					partyv.set((q),partyv.get(p));
					partyv.set((p),tempvotes);
					tempparty = pparty.get(q);
					pparty.set((q),pparty.get(p));
					pparty.set(p,tempparty);
				}
			}
		}
		int votes1 = partyv.get(0);
		int count2=0;
		for(int c=0;c<partyv.size();c++){
			if(partyv.get(c)==votes1){
				count2++;
			}
		}
		String temp = new String();
		for(int c1=0;c1<count2;c1++){
			for(int c2=c1;c2<count2;c2++){
				if(pparty.get(c1).compareTo(pparty.get(c2))>0){
					temp = pparty.get(c1);
					pparty.set(c1,pparty.get(c2));
					pparty.set(c2,temp);
				}
			}
		}
		for(int c3=0;c3<count2;c3++){
			System.out.println(pparty.get(c3));
		}


	}
	public void voteShareInState(String party,String state){
		//write your code here
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		int sum1=0;
		int sum=0;
		for(int i=0;i<v1.size();i++){
			if(v1.get(i).state.equals(state)){
				sum = sum + (int)v1.get(i).v;
				if(v1.get(i).party.equals(party)){
					sum1 = sum1 + (int)v1.get(i).v;
				}
			}
		}
		int ans = (int)((100*((double)sum1))/((double)sum));
		//DecimalFormat df = new DecimalFormat("#.#");

		//int ans1 = (int)ans*10;
		//ans = ans1/10;
		System.out.println(ans);
	}
	
	public void printElectionLevelOrder() {
		//write your code here
	
		Vector<BST<String,Integer>.Node> v1 = this.hmap1();
		for(int q=0;q<v1.size();q++){
			System.out.println((v1.get(q)).name + ", " +(v1.get(q)).k + ", " +(v1.get(q)).state + ", " +(v1.get(q)).district + ", "+(v1.get(q)).constituency + ", " +(v1.get(q)).party + ", "+ (v1.get(q)).v); 
		}
		
		
	}
	public Vector<BST<String,Integer>.Node> hmap1(){
		int i=0;
		BST.Node n1 = b1.root;
		Vector<BST<String,Integer>.Node> v1 = new Vector<BST<String,Integer>.Node>();
		v1.add(n1);
		int j=i+1;
		int k=j;
		while(true){
			
			j=k;
			int tempj=j;

			for(int a=i;a<j;a++){
				BST.Node n2 = v1.get(a);
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
}











