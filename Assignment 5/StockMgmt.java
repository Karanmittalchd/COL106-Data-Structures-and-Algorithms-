package col106.assignment5;

public class StockMgmt implements StockMgmtInterface {
	//!!!!!!!*********DON'T CREATE YOUR OWN OBJECTS OF LLMergeSort*********!!!!!!!
	//use these only this object to sort
	LLMergeSort mergeSortobj;

	LinkedList<CategoryNode> categories;

	//DO NOT MNODIFY THIS CONSTRUCTOR
	public StockMgmt() {

		mergeSortobj = new LLMergeSort();
		categories = new LinkedList<CategoryNode>();

		categories.add(new CategoryNode(1, "mobile"));
		categories.add(new CategoryNode(2, "utensils"));
		categories.add(new CategoryNode(3, "sanitary"));
		categories.add(new CategoryNode(4, "medicalEquipment"));
		categories.add(new CategoryNode(5, "clothes"));

	}

	public void addItem(int categoryId, int itemId, String itemName, int stock) {
		//Your code goes here
		
		ItemNode item1 = new ItemNode(itemId,itemName,stock);
		Node<CategoryNode> hd = categories.getHead();
		while(hd!=null){
			if(hd.getData().getCategoryId()==categoryId){
				hd.getData().getLinkedListOfCategory().add(item1);
				break;
			}
			hd=hd.next;
		}
		//System.out.println("Adding Items");  

	}

	public void addItemTransaction(int categoryId, int itemId, String itemName, int numItemPurchased, int day, int month, int year) {
		//Your code goes here
		
		PurchaseNode transaction1 = new PurchaseNode(numItemPurchased,day,month,year);
		Node<CategoryNode> hd1 = categories.getHead();
		while(hd1!=null){
			if(hd1.getData().getCategoryId()==categoryId){
				Node<ItemNode> itemhead= hd1.getData().getLinkedListOfCategory().getHead();
				while(itemhead!=null){
					if(itemhead.getData().itemId==itemId){
						itemhead.data.stock -= numItemPurchased;
						itemhead.data.addTransaction(transaction1);
						break;
					}
					itemhead=itemhead.next;
				}
				
			}
			hd1=hd1.next;
		}  
		
	}

	//Query 1
	public LinkedList<ItemNode> sortByLastDate(){
		//Your code goes here
		LinkedList<ItemNode> ll = new LinkedList<ItemNode>();
		Node<CategoryNode> hd2 = categories.getHead();
		while(hd2!=null){
			Node<ItemNode> itemhead = hd2.getData().getLinkedListOfCategory().getHead();
			while(itemhead!=null){
				ll.add(itemhead.getData());
				itemhead = itemhead.next;
			}
			hd2=hd2.next;

		}
		ll = mergeSortobj.MergeSort(ll,1);
		
		return ll;
	}

	//Query 2
	public LinkedList<ItemNode> sortByPurchasePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		//Your code goes here
		int after_year=0;
		int before_year=0;
		int after_month=0;
		int before_month=0;
		int after_day=0;
		int before_day=0;
		if(year2<year1){
			before_year = year1;
			after_year = year2;
			before_month = month1;
			after_month = month2;
			before_day = day1;
			after_day = day2;
		}else if(year2>year1){
			before_year = year2;
			after_year = year1;
			before_month = month2;
			after_month = month1;
			before_day = day2;
			after_day = day1;
		}else{
			before_year = year2;
			after_year = year1;
			if(month2<month1){
				before_month = month1;
				after_month = month2;
				before_day = day1;
				after_day = day2;
			}else if(month2>month1){
				before_month = month2;
				after_month = month1;
				before_day = day2;
				after_day = day1;
			}else{
				before_month = month2;
				after_month = month1;
				if(day2<day1){
					before_day = day1;
					after_day = day2;
				}else{
					before_day = day2;
					after_day = day1;
				}
			}
		}
		float max_year = 0;
		float min_year = 10000000;
		LinkedList<ItemNode> ll = new LinkedList<ItemNode>();
		Node<CategoryNode> hd2 = categories.getHead();
		while(hd2!=null){
			Node<ItemNode> itemhead = hd2.getData().getLinkedListOfCategory().getHead();
			while(itemhead!=null){
				Node<PurchaseNode> pd = itemhead.getData().getPurchaseHead();
				if(pd==null){
					StockMgmt sm = new StockMgmt();
					sm.addItemTransaction(hd2.getData().getCategoryId(),itemhead.getData().getItemId(),itemhead.getData().getItemName(),0,1,8,1970);
					pd = itemhead.getData().getPurchaseHead();
				}
				max_year = 0;
				min_year = 10000000;
				while(pd!=null){
					if(pd.getData().getDate().getYear()>after_year&&pd.getData().getDate().getYear()<before_year){
						itemhead.getData().purchased_in_tp += pd.getData().numItemsPurchased();
						if(pd.getData().getDate().getYear()>=max_year){
							max_year = pd.getData().getDate().getYear();
						}
						if(pd.getData().getDate().getYear()<min_year){
							min_year = pd.getData().getDate().getYear();
						}
						pd=pd.next;
						continue;
					}
					if(pd.getData().getDate().getYear()==after_year){
						if(pd.getData().getDate().getMonth()>after_month){
							itemhead.getData().purchased_in_tp += pd.getData().numItemsPurchased();
							if(pd.getData().getDate().getYear()>=max_year){
								max_year = pd.getData().getDate().getYear();
							}
							if(pd.getData().getDate().getYear()<min_year){
								min_year = pd.getData().getDate().getYear();
							}
							pd=pd.next;
							continue;
						}
						if(pd.getData().getDate().getMonth()==after_month){
							if(pd.getData().getDate().getDay()>=after_day){
								itemhead.getData().purchased_in_tp += pd.getData().numItemsPurchased();
								if(pd.getData().getDate().getYear()>=max_year){
									max_year = pd.getData().getDate().getYear();
								}
								if(pd.getData().getDate().getYear()<min_year){
									min_year = pd.getData().getDate().getYear();
								}
								pd=pd.next;
								continue;
							}
						}
					}
					if(pd.getData().getDate().getYear()==before_year){
						if(pd.getData().getDate().getMonth()<before_month){
							itemhead.getData().purchased_in_tp += pd.getData().numItemsPurchased();
							if(pd.getData().getDate().getYear()>=max_year){
								max_year = pd.getData().getDate().getYear();
							}
							if(pd.getData().getDate().getYear()<min_year){
								min_year = pd.getData().getDate().getYear();
							}
							pd=pd.next;
							continue;
						}
						if(pd.getData().getDate().getMonth()==before_month){
							if(pd.getData().getDate().getDay()<=before_day){
								itemhead.getData().purchased_in_tp += pd.getData().numItemsPurchased();
								if(pd.getData().getDate().getYear()>=max_year){
									max_year = pd.getData().getDate().getYear();
								}
								if(pd.getData().getDate().getYear()<min_year){
									min_year = pd.getData().getDate().getYear();
								}
								pd=pd.next;
								continue;
							}
						}
					}
					pd=pd.next;				
				}

				itemhead.getData().ratio = (itemhead.getData().purchased_in_tp)/(max_year-min_year+1);
				ll.add(itemhead.getData());

				itemhead = itemhead.next;
			}
			hd2=hd2.next;

		}
		ll = mergeSortobj.MergeSort(ll,2);
		return ll ;
		//return null;
	}

	//Query 3
	public LinkedList<ItemNode> sortByStockForCategory(int category) {
		//Your code goes here
		LinkedList<ItemNode> ll = new LinkedList<ItemNode>();
		Node<CategoryNode> hd2 = categories.getHead();
		while(hd2!=null){
			Node<ItemNode> itemhead = hd2.getData().getLinkedListOfCategory().getHead();
			if(hd2.getData().getCategoryId()==category){
			while(itemhead!=null){
				ll.add(itemhead.getData());
				itemhead = itemhead.next;
			}}
			hd2=hd2.next;

		}
		ll = mergeSortobj.MergeSort(ll,3);
		return ll;
		//return null;
	}

	//Query 4
	public LinkedList<ItemNode> filterByCategorySortByDate(int category) {
		//Your code goes here
		LinkedList<ItemNode> ll = new LinkedList<ItemNode>();
		Node<CategoryNode> hd2 = categories.getHead();
		while(hd2!=null){
			Node<ItemNode> itemhead = hd2.getData().getLinkedListOfCategory().getHead();
			if(hd2.getData().getCategoryId()==category){
			while(itemhead!=null){
				ll.add(itemhead.getData());
				itemhead = itemhead.next;
			}}
			hd2=hd2.next;

		}
		ll = mergeSortobj.MergeSort(ll,4);
		
		return ll;
		//return null;
	}

	//!!!!!*****DO NOT MODIFY THIS METHOD*****!!!!!//
	public LinkedList<ItemNode> checkMergeSort() {
		LinkedList<ItemNode> retLst = mergeSortobj.getGlobalList();
		mergeSortobj.clearGlobalList();
		return retLst;
	}
}
