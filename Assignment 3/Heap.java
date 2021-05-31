package col106.assignment3.Heap;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;

public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
	
	// write your code here	
	public int nodes = 0;	

	public E[] arrval= (E[]) new Comparable[10000];
	public T[] arrkey= (T[]) new Comparable[10000];
	// write your code here	
	public void insert(T key, E value) {
		//write your code here

		int n = this.nodes;
		//System.out.println(n);
		arrval[n] = value;
		arrkey[n] = key;
		T tempkey;
		E tempval;
		if(n>0){
			while((n-1)/2 >= 0){
				//System.out.println("printing before insert");
				if(arrval[n].compareTo(arrval[(n-1)/2])>0){
					//System.out.println("printing before insert");
					tempkey = arrkey[n];
					arrkey[n] = arrkey[(n-1)/2];
					arrkey[(n-1)/2] = tempkey;
					tempval = arrval[n];
					arrval[n] = arrval[(n-1)/2];
					arrval[(n-1)/2] = tempval;
					
				}
				n=(n-1)/2;
				//System.out.println(n);
				if(n==0)
					n=-1;
				//System.out.println((n-1)/2);
			}
		}
		this.nodes++;
		
	}
		
	

	public E extractMax() {
		//write your code here
		int n = this.nodes;
		E answer = arrval[0];
		this.delete(arrkey[0]);
		// arrval[0] = arrval[n-1];
		// arrkey[0] = arrkey[n-1];
		// this.nodes--;
		// n = this.nodes;
		// int i = 0;
		// T tempkey;
		// E tempval;
		// while(2*i+1<n){
		// 	if(arrval[2*(i+1)].compareTo(arrval[2*(i+2)])>=0){
		// 		tempval = arrval[i];
		// 		arrval[i] = arrval[2*(i+1)];
		// 		arrval[2*(i+1)] = tempval;
		// 		tempkey = arrkey[i];
		// 		arrkey[i] = arrkey[2*(i+1)];
		// 		arrkey[2*(i+1)] = tempkey;
		// 		i = 2*(i+1);
		// 	}else{
		// 		tempval = arrval[i];
		// 		arrval[i] = arrval[2*(i+2)];
		// 		arrval[2*(i+2)] = tempval;
		// 		tempkey = arrkey[i];
		// 		arrkey[i] = arrkey[2*(i+2)];
		// 		arrkey[2*(i+2)] = tempkey;
		// 		i = 2*(i+2);
		// 	}

		// }

		return answer;
	}
	

	public void delete(T key) {
		//write your code here
		int i = 0;
		int n = this.nodes;
		if(n==0)
			return;
		for(int j=0;j<n;j++){
			if(arrkey[j]==key)
				i = j; 
		}
		arrkey[i] = arrkey[n-1];
		arrval[i] = arrval[n-1];
		this.nodes--;
		int flag=0;
		n = this.nodes;
		T tempkey;
		E tempval;
		while((2*i+1)<n){
			if((2*i+2)<n && arrval[2*i+1].compareTo(arrval[2*i+2])>=0){
				if(arrval[i].compareTo(arrval[2*i+1])<0){
					tempval = arrval[i];
					arrval[i] = arrval[2*i+1];
					arrval[2*i+1] = tempval;
					tempkey = arrkey[i];
					arrkey[i] = arrkey[2*i+1];
					arrkey[2*i+1] = tempkey;
					i = 2*i+1;
				}else
					flag=1;
			}else if((2*i+2)<n && arrval[2*i+1].compareTo(arrval[2*i+2])<0){
				if(arrval[i].compareTo(arrval[2*i+2])<0){
					tempval = arrval[i];
					arrval[i] = arrval[2*i+2];
					arrval[2*i+2] = tempval;
					tempkey = arrkey[i];
					arrkey[i] = arrkey[2*i+2];
					arrkey[2*i+2] = tempkey;
					i = 2*i+2;
				}
				else
					flag = 1;
			}
			else if((2*i+2)>=n){
				if(arrval[i].compareTo(arrval[2*i+1])<0){
					tempval = arrval[i];
					arrval[i] = arrval[2*i+1];
					arrval[2*i+1] = tempval;
					tempkey = arrkey[i];
					arrkey[i] = arrkey[2*i+1];
					arrkey[2*i+1] = tempkey;
					i = 2*i+1;
				}else
					flag=1;

			}
			if(flag==1)
				break;
		}
		while((i-1)/2 >= 0){
				//System.out.println("printing before insert");
				if(arrval[i].compareTo(arrval[(i-1)/2])>0){
					//System.out.println("printing before insert");
					tempkey = arrkey[i];
					arrkey[i] = arrkey[(i-1)/2];
					arrkey[(i-1)/2] = tempkey;
					tempval = arrval[i];
					arrval[i] = arrval[(i-1)/2];
					arrval[(i-1)/2] = tempval;
					
				}
				i=(i-1)/2;
				//System.out.println(n);
				if(i==0)
					i=-1;
				//System.out.println((n-1)/2);
			}


	}

	public void increaseKey(T key, E value) {
		//write your code here
		int flag=0;
		int i = 0;
		int n = this.nodes;
		for(int j=0;j<n;j++){
			if(arrkey[j] == key)
				i = j;
		}
		arrval[i] = value;
		T tempkey;
		E tempval;
		while((i-1)/2>=0){
			if(arrval[i].compareTo(arrval[(i-1)/2])>0){
				tempkey = arrkey[i];
				arrkey[i] = arrkey[(i-1)/2];
				arrkey[(i-1)/2] = tempkey;
				tempval = arrval[i];
				arrval[i] = arrval[(i-1)/2];
				arrval[(i-1)/2] = tempval;
			}else{
				flag=1;
			}
			if(flag==1)
				break;
			i = (i-1)/2;
			if(i==0)
				i=-1;
		}
		
		/*else if((2*i+1)<n){
			if(arrval[i].compareTo(arrval[2*(i+1)])<0||arrval[i].compareTo(arrval[2*(i+2)])<0){
				while((2*i+1)<n){
					if((2*i+2)<n && arrval[2*i+1].compareTo(arrval[2*i+2])>=0){
						if(arrval[i].compareTo(arrval[2*i+1])<0){
							tempval = arrval[i];
							arrval[i] = arrval[2*i+1];
							arrval[2*i+1] = tempval;
							tempkey = arrkey[i];
							arrkey[i] = arrkey[2*i+1];
							arrkey[2*i+1] = tempkey;
							i = 2*i+1;
						}else
							flag=1;
					}else if((2*i+2)<n && arrval[2*i+1].compareTo(arrval[2*i+2])<0){
						if(arrval[i].compareTo(arrval[2*i+2])<0){
							tempval = arrval[i];
							arrval[i] = arrval[2*i+2];
							arrval[2*i+2] = tempval;
							tempkey = arrkey[i];
							arrkey[i] = arrkey[2*i+2];
							arrkey[2*i+2] = tempkey;
							i = 2*i+2;
						}
						else
							flag = 1;
					}
					else if((2*i+2)>=n){
						if(arrval[i].compareTo(arrval[2*i+1])<0){
							tempval = arrval[i];
							arrval[i] = arrval[2*i+1];
							arrval[2*i+1] = tempval;
							tempkey = arrkey[i];
							arrkey[i] = arrkey[2*i+1];
							arrkey[2*i+1] = tempkey;
							i = 2*i+1;
						}else
							flag=1;

					}
					if(flag==1)
						break;
				}
			}
		} */
		
	}

	public void printHeap() {
		//write your code here
		for(int i = 0; i<this.nodes; i++){
			System.out.println(arrkey[i] + ", " + arrval[i]);
		}
	}
	/*public static void main(String[] args) {
		Heap<Integer, Integer> heap = new Heap();
		
		heap.insert(1,100);
		heap.insert(2,10);
		
		heap.insert(3,30);
		heap.insert(4,50);
		heap.insert(5,150);
		heap.insert(6,1);
		heap.insert(7,3);
		heap.insert(8,230);
		heap.insert(9,2);
		heap.insert(10,130);
		heap.insert(11,90);
		heap.insert(12,65);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.delete(3);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.delete(7);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(11,240);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(12,235);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.delete(10);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.delete(1);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.delete(2);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.delete(4);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.extractMax();
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.extractMax();
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(8,240);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(8,290);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(6,151);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(6,300);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(9,152);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		heap.increaseKey(9,301);
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		//
		//heap.printHeap();
		
		
		
		/**System.out.println("Meri gaand boht moti hai");

		
		
		heap.printHeap();
		System.out.println("Meri gaand boht moti hai");
		System.out.println(heap.extractMax());
		heap.printHeap();*/
		/*System.out.println("printing after insert2");
		
		heap.extractMax();
		System.out.println("printing after extract max");
		heap.printHeap();
		heap.increaseKey(7,70);
		System.out.println("printing after inc key");
		heap.printHeap();*/
	
}
