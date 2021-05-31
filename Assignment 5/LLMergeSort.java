package col106.assignment5;

import java.util.Comparator;

/*
Implementation of MergeSort Algorithm :
    1. you get linked list of size <=1 you just return the list as it is already sorted
    2. Find mid node using findSplit method(Don't forget to add mid element to globalList before returning it)
    3. Create two LinkedList lt (with head = head and tail = mid) and rt (with head = mid.next and tail = tail)
    4. Now recursively MergSort lt and rt Linked lists(Maintain this order)
    5. Now merge these two lists that we got from recursive calls using given crieteria for ordering
    6. Return merged Linked list
*/ 
class purchasedate{
	public int compare3(Node<ItemNode> a,Node<ItemNode>b){
		int answer=0;
		if(a.getData().ratio<b.getData().ratio){
  				answer =  1;
  			}else if(a.getData().ratio>b.getData().ratio){
  				answer = -1;

  			}else{
  				if(a.getData().getItemName().compareTo(b.getData().getItemName())>0){
  					answer = 1;	
  				}else{
  					answer = -1;
  				}
  			}
  		return answer;
	
	}
}
class datecompare{
	public int max(int a, int b){
		if(a>b)
			return a;
		else
			return b;
	}

	public int compare(Node<ItemNode> a,Node<ItemNode> b){
		Node<PurchaseNode> p1 = a.getData().getPurchaseHead();
		Node<PurchaseNode> p2 = b.getData().getPurchaseHead();
		Node<PurchaseNode> p1d = p1;
		Node<PurchaseNode> p2d = p2;
		int max_year1=0;
		int max_month1=0;
		int max_day1=0;
		int max_year2=0;
		int max_month2=0;
		int max_day2=0;
		if(p1==null){
			max_year1 = 1970;
			max_month1 = 8;
			max_day1 = 1;
		}
		if(p2==null){
			max_year2 = 1970;
			max_month2 = 8;
			max_day2 = 1;
		}
		while(p1d!=null){
			max_year1 = max(max_year1,p1d.getData().getDate().getYear());
			p1d=p1d.getNext();
		}
		p1d = p1;
		
		while(p1d!=null){
			if(p1d.getData().getDate().getYear()==max_year1){
				max_month1 = max(max_month1,p1d.getData().getDate().getMonth());
			}
			p1d=p1d.getNext();
		}
		p1d = p1;
		
		while(p1d!=null){
			if(p1d.getData().getDate().getYear()==max_year1&&p1d.getData().getDate().getMonth()==max_month1){
				max_day1 = max(max_day1,p1d.getData().getDate().getDay());
			}
			p1d=p1d.getNext();
		}
		
		while(p2d!=null){
			max_year2 = max(max_year2,p2d.getData().getDate().getYear());
			p2d=p2d.getNext();
		}
		p2d = p2;
		
		while(p2d!=null){
			if(p2d.getData().getDate().getYear()==max_year2){
				max_month2 = max(max_month2,p2d.getData().getDate().getMonth());
			}
			p2d=p2d.getNext();
		}
		p2d = p2;
		
		while(p2d!=null){
			if(p2d.getData().getDate().getYear()==max_year2&&p2d.getData().getDate().getMonth()==max_month2){
				max_day2 = max(max_day2,p2d.getData().getDate().getDay());
			}
			p2d=p2d.getNext();
		}
		int answer = 1;
		if(max_year1>max_year2){
			answer = -1;
		}else if(max_year2>max_year1){
			answer = 1;
		}else{
			if(max_month1>max_month2){
				answer = -1;
			}else if(max_month1<max_month2){
				answer = 1;
			}else{
				if(max_day1>max_day2){
					answer =  -1;
				}else if(max_day1<max_day2){
					answer =  1;
				}else{
					if(a.getData().getItemName().compareTo(b.getData().getItemName())>0)
						answer =  1;
					else
						answer = -1;
				}
			}
		}
		return answer;

	}

}
class stockcompare{
	public int compare1(Node<ItemNode> a,Node<ItemNode> b){
		int answer=0;
		if(a.getData().getStockLeft()>b.getData().getStockLeft()){
  				answer =  1;
  			}else if(a.getData().getStockLeft()<b.getData().getStockLeft()){
  				answer = -1;

  			}else{
  				if(a.getData().getItemName().compareTo(b.getData().getItemName())>0){
  					answer = 1;	
  				}else{
  					answer = -1;
  				}
  			}
  		return answer;
	}
}
class newdatecompare{
	public int max(int a, int b){
		if(a>b)
			return a;
		else
			return b;
	}
	public int compare2(Node<ItemNode> a,Node<ItemNode> b){
		Node<PurchaseNode> p1 = a.getData().getPurchaseHead();
		Node<PurchaseNode> p2 = b.getData().getPurchaseHead();
		Node<PurchaseNode> p1d = p1;
		Node<PurchaseNode> p2d = p2;
		int max_year1=0;
		int max_month1=0;
		int max_day1=0;
		int max_year2=0;
		int max_month2=0;
		int max_day2=0;
		if(p1==null){
			max_year1 = 1970;
			max_month1 = 8;
			max_day1 = 1;
		}
		if(p2==null){
			max_year2 = 1970;
			max_month2 = 8;
			max_day2 = 1;
		}
		while(p1d!=null){
			max_year1 = max(max_year1,p1d.getData().getDate().getYear());
			p1d=p1d.getNext();
		}
		p1d = p1;
		
		while(p1d!=null){
			if(p1d.getData().getDate().getYear()==max_year1){
				max_month1 = max(max_month1,p1d.getData().getDate().getMonth());
			}
			p1d=p1d.getNext();
		}
		p1d = p1;
		
		while(p1d!=null){
			if(p1d.getData().getDate().getYear()==max_year1&&p1d.getData().getDate().getMonth()==max_month1){
				max_day1 = max(max_day1,p1d.getData().getDate().getDay());
			}
			p1d=p1d.getNext();
		}
		
		while(p2d!=null){
			max_year2 = max(max_year2,p2d.getData().getDate().getYear());
			p2d=p2d.getNext();
		}
		p2d = p2;
		
		while(p2d!=null){
			if(p2d.getData().getDate().getYear()==max_year2){
				max_month2 = max(max_month2,p2d.getData().getDate().getMonth());
			}
			p2d=p2d.getNext();
		}
		p2d = p2;
		
		while(p2d!=null){
			if(p2d.getData().getDate().getYear()==max_year2&&p2d.getData().getDate().getMonth()==max_month2){
				max_day2 = max(max_day2,p2d.getData().getDate().getDay());
			}
			p2d=p2d.getNext();
		}
		int answer = 1;
		if(max_year1>max_year2){
			answer = 1;
		}else if(max_year2>max_year1){
			answer = -1;
		}else{
			if(max_month1>max_month2){
				answer = 1;
			}else if(max_month1<max_month2){
				answer = -1;
			}else{
				if(max_day1>max_day2){
					answer =  1;
				}else if(max_day1<max_day2){
					answer =  -1;
				}else{
					if(a.getData().getItemName().compareTo(b.getData().getItemName())>0)
						answer = 1;
					else
						answer = -1;
				}
			}
		}
		return answer;

	}
}
class printlist{
	public void plist(LinkedList<ItemNode> ll){
		Node<ItemNode> he = ll.getHead();
		while(he!=null){
			he = he.next;
		}
	}
} 
public class LLMergeSort <T>  {

  LinkedList<T>  globalList = new LinkedList<T>();

  //CALL THIS METHOD AFTER EVERY CALL OF findSplit and DO NOT MODIFY THIS METHOD
  public void adjustGlobalPointer(T node){
      globalList.add(node);
  }

  // Utility function to get the middle of the linked list
  public Node<T> findSplit(LinkedList<T>  lst) {
    //find middle node of LL :
    Node<T> middle = lst.getHead();
    //Enter your code here
    
    Node<T> mid2 = middle.getNext();

    while(mid2!=null){
    	mid2=mid2.next;
    	if(mid2!=null){
    		middle = middle.getNext();
    		mid2 = mid2.getNext();
    	}
    }
   

    //!!!!!*****DO NOT REMOVE THIS METHOD CALL (change the argument apprpriately)*****!!!!!
    adjustGlobalPointer(middle.getData()); //Add object of ItemNode after finding mid in each call
    return middle.getNext();
  }


  public LinkedList<T>  MergeSort(LinkedList<T>  lst, int query) {
    //Recursively Apply MxergeSort, by calling function findSplit(..) to find middle node to split
    //Enter your code here
    if(lst.getSize()<=1)
    	return lst;
    Node<T>head3 = lst.getHead();
  	Node<T>mid = findSplit(lst);
  	LinkedList<T> lst_left = new LinkedList<T>();
  	while(head3!=mid){
  		lst_left.add(head3.getData());
  		if(head3.getNext()==mid){
  			break;
  		}
  		head3=head3.getNext();
  	}
  	LinkedList<T> lst_right = new LinkedList<T>();
  	while(mid!=null){
  		lst_right.add(mid.getData());
  		mid=mid.getNext();
  	}
  	lst_left=MergeSort(lst_left,query);
  	lst_right=MergeSort(lst_right,query);
  	lst = Mergell(lst_left,lst_right,query);
  	printlist pl = new printlist();
  	pl.plist((LinkedList<ItemNode>)lst);

    return lst;
  }
  public LinkedList<T> Mergell(LinkedList<T>left,LinkedList<T>right,int query){
  	LinkedList<T> merged_list = new LinkedList<T>();
  	Node<T> head1 = left.getHead();
  	Node<T> head2 = right.getHead();
  	while(true){
  		if(head1==null&&head2==null)
  			break;
  		if(head1==null){
  			merged_list.add(head2.getData());
  			head2 = head2.getNext();
			continue;  		
  		}
  		if(head2==null){
  			merged_list.add(head1.getData());
  			head1 = head1.getNext();
  			continue;
  	
  		}
  		if(query==1){
  			datecompare dc = new datecompare();
  			if(dc.compare((Node<ItemNode>)head1,(Node<ItemNode>)head2)==1){
  				merged_list.add(head1.getData());
  				head1=head1.getNext();
  			}else{
  				merged_list.add(head2.getData());
  				head2=head2.getNext();
  			}
  			
  			continue;
  		}
  		if(query==3){
  			stockcompare sc = new stockcompare();
  			if(sc.compare1((Node<ItemNode>)head1,(Node<ItemNode>)head2)==1){
  				merged_list.add(head1.getData());
  				head1=head1.getNext();
  				continue;
  			}
  			if(sc.compare1((Node<ItemNode>)head1,(Node<ItemNode>)head2)==-1){
  				merged_list.add(head2.getData());
  				head2=head2.getNext();
  				continue;
  			}
  		}
  		if(query==4){
  			newdatecompare ndc = new newdatecompare();
  			if(ndc.compare2((Node<ItemNode>)head1,(Node<ItemNode>)head2)==1){
  				merged_list.add(head1.getData());
  				head1=head1.getNext();
  			}else{
  				merged_list.add(head2.getData());
  				head2=head2.getNext();
  			}
  			
  			continue;
  		}
  		if(query==2){
  			purchasedate pd = new purchasedate();
  			if(pd.compare3((Node<ItemNode>)head1,(Node<ItemNode>)head2)==1){
  				merged_list.add(head1.getData());
  				head1=head1.getNext();
  				continue;
  			}
  			if(pd.compare3((Node<ItemNode>)head1,(Node<ItemNode>)head2)==-1){
  				merged_list.add(head2.getData());
  				head2=head2.getNext();
  				continue;
  			}
  		}


  	}
  	printlist pl = new printlist();
  	pl.plist((LinkedList<ItemNode>)merged_list);
  	return merged_list;
  }
  //DO NOT CALL OR MODIFY THESE METHODS IN YOUR CALL THIS IS FOR USE IN DRIVER CODE
  public LinkedList<T> getGlobalList() {
    return this.globalList;
  }

  public void clearGlobalList(){
    globalList  = new LinkedList<>();
  }
 

}
