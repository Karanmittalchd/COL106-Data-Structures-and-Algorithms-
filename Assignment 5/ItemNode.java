package col106.assignment5;

public class ItemNode implements ItemInterface{

	int itemId;
	String itemName;
	int stock;
	LinkedList<PurchaseNode>  purchaseTransactions;
	float purchased_in_tp = 0;
	float ratio=1;

	public ItemNode(int itemId, String itemName, int stock){
		this.itemId = itemId;
		this.itemName = itemName;
		this.stock = stock;
		this.purchaseTransactions = new LinkedList<PurchaseNode>();
	}

	public int getItemId(){
		//Enter your code here
		return this.itemId;
	}

	public  String getItemName(){
		//Enter your code here
		return this.itemName;
	}

	public int getStockLeft(){
		//Enter your code here
		return this.stock;
	}

	public void addTransaction(PurchaseNode purchaseT){
		//Enter your code here
		purchaseTransactions.add(purchaseT);

	}

	public Node<PurchaseNode> getPurchaseHead(){
		//Enter your code here
		return this.purchaseTransactions.getHead();
		
	}
	

}
