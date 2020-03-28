package col106.assignment4.WeakAVLMap;
import java.util.Vector;

public class WeakAVLMap<K extends Comparable,V> implements WeakAVLMapInterface<K,V>{

	
	class Node{
		K k;
		V val;
		Node left;
		Node right;
		int rank=0;
		int flag=0;
		Node(K key, V value){
			k = key;
			val = value; 
		}
		
	}
	public Vector<Node> ino = new Vector<Node>();
	public Node parent(Node n1){
			
			WeakAVLMap.Node np = this.root;
			while(true){
					if((n1.k).compareTo(np.k)<0){
						if(np.left==n1){
							n1.flag=0;
							break;
						}
						np = np.left;
					}
					if((n1.k).compareTo(np.k)>0){
						if(np.right==n1){
							n1.flag=1;
							break;
						}
						np = np.right;
					}


				}
			return np;

		}
	public int rotations=0;
	public Node root;

	
	public int nodes=0;
	public WeakAVLMap(){
		// write your code here
		this.root = null;
	}
	
	public V put(K key, V value){
		// write your code her 
		int flag2=0;
		V ans = null;
		WeakAVLMap.Node n1 = new WeakAVLMap.Node(key,value);
		if(this.nodes==0){
			
			n1.left = null;
			n1.right = null;
			this.root = n1;
			
		}else{
			WeakAVLMap.Node n2 = this.root;
			while(true){
				if((n1.k).compareTo(n2.k)<0){
					if(n2.left==null){
						n2.left = n1;
						break;
					}
					n2 = n2.left;
				}
				else if((n1.k).compareTo(n2.k)>0){
					if(n2.right==null){
						n2.right = n1;
						break;
					}
					n2 = n2.right;
				}else{
					ans = (V) n2.val;
					n1=n2;
					n1.val = value;
					flag2=1;
					
					return ans;
				}

			}
			n1.left=null;
			n1.right=null;
		}
		
		//np is the parent
		int flag1=0;
		n1.rank = 1;
		if(this.nodes>0 && (n1.rank==(this.parent(n1)).rank)){
			//Node has rank difference zero with its parent
			WeakAVLMap.Node np = this.parent(n1);
			while(np.rank==n1.rank){
				flag1=0;
				if(n1.flag==1){
					if(np.left!=null){
						if(np.rank-(np.left).rank==1){
							np.rank++;
							flag1=1;
						}
					}else{
						if(np.rank==1){
							np.rank++;
							flag1=1;
						}
					}
				}else{
					if(np.right!=null){
						if(np.rank-(np.right).rank==1){
							np.rank++;
							flag1=1;
						}
					}else{
						if(np.rank==1){
							np.rank++;
							flag1=1;
						}
					}

				}
				if(flag1==1){
					if(np==this.root)
						break;
					else{
						n1=np;
						np = this.parent(n1);
					}
				}
				if(flag1==0){
					if(n1.left!=null&&(n1.rank-(n1.left).rank==1)){
						if(n1.flag==0){
							this.rotations++;
							np.left=n1.right;
							if(np==this.root)
								this.root = n1;
							else{
								if((this.parent(np)).left==np)
									(this.parent(np)).left=n1;
								else
									(this.parent(np)).right=n1;

							}
							n1.right = np;
							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							break;
						}else{
							this.rotations = this.rotations + 2;
							WeakAVLMap.Node nc = n1.left;
							np.right = nc.left;
							n1.left = nc.right;
							if(np==this.root)
								this.root = nc;
							else{
								if((this.parent(np)).right==np)
									(this.parent(np)).right=nc;
								else
									(this.parent(np)).left=nc;
							}
							nc.right = n1;
							nc.left = np;
							nc.rank++;
							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							n1.rank--;
							if(n1.rank!=0&&n1.left==null&&n1.right==null)
								n1.rank=1;
							break;
						}
					}
					if(n1.right!=null&&(n1.rank-(n1.right).rank==1)){
						if(n1.flag==1){
							this.rotations++;
							np.right=n1.left;
							if(np==this.root)
								this.root = n1;
							else{
								if((this.parent(np)).right==np)
									(this.parent(np)).right=n1;
								else
									(this.parent(np)).left=n1;
							}
							n1.left = np;
							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							break;
						}else{
							this.rotations = this.rotations + 2;
							WeakAVLMap.Node nc = n1.right;
							np.left = nc.right;
							n1.right = nc.left;
							if(np==this.root)
								this.root = nc;
							else{
								if((this.parent(np)).left==np)
									(this.parent(np)).left=nc;
								else
									(this.parent(np)).right=nc;
							}
							nc.left = n1;
							nc.right = np;
							nc.rank++;

							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank = 1;
							n1.rank--;
							if(n1.rank!=0&&n1.left==null&&n1.right==null)
								n1.rank = 1;
							break;
						}

					}

				}

			}

		}


		this.nodes++;
		return null;
	}

	public V remove(K key){
		// write your code her
		if(this.nodes==0)
			return null;
		V ans = null;
		WeakAVLMap.Node n1 = this.root;
		while(true){
			if((key).compareTo(n1.k)<0){
				n1 = n1.left;
				if(n1==null)
					return null;
			}
			else if((key).compareTo(n1.k)>0){
				n1 = n1.right;
				if(n1==null)
					return null;
			}else{
				ans = (V) n1.val;
				break;
			}


		}
		int flagcheck=0;
		WeakAVLMap.Node nc = new WeakAVLMap.Node(null,null);
		WeakAVLMap.Node np = new WeakAVLMap.Node(null,null);
		if(this.nodes==1){
			this.root.rank=0;
			this.root = null;
		}
		if(this.nodes==2){
			if(this.root.left!=null){
				if(n1==this.root.left){
					this.root.left = null;
				}
				if(n1==this.root){
					this.root = this.root.left;
					this.root.left = null;
				}
			}
			if(this.root.right!=null){
				if(n1==this.root.right){
					this.root.right = null;
				}
				if(n1==this.root){
					this.root = this.root.right;
					this.root.right = null;
				}
			}
			this.root.rank = 1;
		}
		if(this.nodes==3){
			if(n1==this.root.left){
				this.root.left = null;
			}
			if(n1==this.root.right){
				this.root.right = null;
			}
			if(n1==this.root){
				this.root.k = this.root.right.k;
				this.root.val = this.root.right.val;
				this.root.right = null;
				this.root.rank = 2;
			}

		}
		if(this.nodes>3){
			if(n1!=this.root){
				np = this.parent(n1);
			}
		
			int children=0;
			int flagc=0;
			if(n1.left!=null){
				children++;
				flagc++;
			}
			if(n1.right!=null){
				children++;
			}
			if(children==0){
				
				if(n1.flag==0){
					np.left = null;
					nc.flag=0;
				}
				else{
					np.right = null;
					nc.flag=1;
				}
				nc.rank=0;
				if(np.left==null&&np.right==null){				
					np.rank=1;
					nc = np;
					np = this.parent(nc);
				}

			}
			if(children==1){
				if(flagc==0){
					nc = n1.right;
				}else{
					nc = n1.left;
				}

				if(n1.flag==0)
					np.left = nc;
				else
					np.right = nc;
				np = this.parent(nc);
				
			}
			if(children==2){
				WeakAVLMap.Node nr = n1.right;
				while(nr.left!=null)
					nr = nr.left;
				if(nr.right!=null){
					nc = nr.right;
					np = this.parent(nr);
					if(nr.flag==0)
						np.left = nc;
					else
						np.right = nc;
					np = this.parent(nc);

				}
				if(nr.right==null){
					np = this.parent(nr);
					
					if(nr.flag==0){
						np.left = null;
						nc.flag=0;
					}
					else{
						np.right = null;
						nc.flag=1;
					}
					nc.rank=0;
					if(np.left==null&&np.right==null){
						np.rank=1;
						nc = np;
						np = this.parent(nc);
					}
				}
				n1.k = nr.k;
				n1.val = nr.val;				
				
			}
			while(np.rank-nc.rank>2){
					WeakAVLMap.Node ns = new WeakAVLMap.Node(null,null);
					if(nc.flag==0)
						ns = np.right;
					else
						ns = np.left;
				
					if(ns!=null&&np.rank-ns.rank==2){
						np.rank--;
						if(np==this.root)
							break;
						nc = np;
						np = this.parent(nc);
						continue;
					}
					if(ns==null&&np.rank==2){
						np.rank--;
						if(np==this.root)
							break;
						nc = np;
						np = this.parent(nc);
						continue;
					}
					if((ns!=null&&np.rank-ns.rank==1)||(ns==null&&np.rank==1)){
						if(ns.left!=null && ns.right!=null && (ns.rank-(ns.left).rank)==2 && (ns.rank-(ns.right).rank==2)){
							ns.rank--;
							if(ns.rank!=0&&ns.left==null&&ns.right==null)
								ns.rank=1;
							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							if(np==this.root)
								break;
							nc = np;
							np = this.parent(nc);
							continue;
						}
						WeakAVLMap.Node np1 = new WeakAVLMap.Node(null,null);
						np = this.parent(ns);
						if(ns.left!=null && (ns.rank-(ns.left).rank)==1 && ns.flag==0){
							np.left = ns.right;
							if(np==this.root){
								this.root = ns;
							}else{
								np1 = this.parent(np);
								if(np.flag==0)
									np1.left = ns;
								else
									np1.right = ns;
							}
							ns.right = np;
							ns.rank++;
							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							this.rotations++;
							break;
						}
						if(ns.right!=null && (ns.rank-(ns.right).rank)==1 && ns.flag==1){
							
							np.right = ns.left;
							if(np==this.root){
								this.root=ns;
							}else{
								np1 = this.parent(np);
								if(np.flag==0)
									np1.left = ns;
								else
									np1.right = ns;
							}
							ns.left = np;
							ns.rank++;
							np.rank--;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							this.rotations++;
							break;

						}
						if(ns.right!=null && (ns.rank-(ns.right).rank)==1 && ns.flag==0){
							WeakAVLMap.Node nt = ns.right;
							np.left = nt.right;
							ns.right = nt.left;
							if(np==this.root){
								this.root = nt;
							}else{
								np1 = this.parent(np);
								if(np.flag==0)
									np1.left = nt;
								else
									np1.right = nt;
							}
							nt.left = ns;
							nt.right = np;
							ns.rank--;
							if(ns.rank!=0&&ns.left==null&&ns.right==null)
								ns.rank=1;

							np.rank = np.rank-2;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							nt.rank = nt.rank+2;
							this.rotations = this.rotations+2;
							break;

						}
						if(ns.left!=null && (ns.rank-(ns.left).rank)==1 && ns.flag==1){
							WeakAVLMap.Node nt = ns.left;
							np.right = nt.left;
							ns.left = nt.right;
							if(np==this.root){
								this.root = nt;
							}else{
								np1 = this.parent(np);
								if(np.flag==0)
									np1.left = nt;
								else
									np1.right = nt;
							}
							nt.right = ns;
							nt.left = np;
							ns.rank--;
							if(ns.rank!=0&&ns.left==null&&ns.right==null)
								ns.rank=1;
							np.rank = np.rank-2;
							if(np.rank!=0&&np.left==null&&np.right==null)
								np.rank=1;
							nt.rank = nt.rank+2;
							this.rotations = this.rotations+2;
							break;

						}


					}


				}

		}


	 	this.nodes--;
		return ans;
	}

	public V get(K key){
		// write your code her
		if(this.nodes==0)
			return null;
		V ans = null;
		WeakAVLMap.Node n1 = this.root;
		while(true){
			if((key).compareTo(n1.k)<0){
				n1 = n1.left;
				if(n1==null)
					return null;
			}
			else if((key).compareTo(n1.k)>0){
				n1 = n1.right;
				if(n1==null)
					return null;
			}else{
				ans = (V) n1.val;
				break;
			}

		} 
		return ans;
	}
	public void inorder(Node n){
		if(n.left!=null)
			inorder(n.left);
		this.ino.add(n);
		if(n.right!=null)
			inorder(n.right);
	}

	public Vector<V> searchRange(K key1, K key2){
		// write your code her
		Vector<V> values = new Vector<V>();
		if(this.nodes==0)
			return values;
		this.inorder(this.root);
		Vector<Node> valuesN = this.ino;
		for(int i=0;i<valuesN.size();i++){
			if(valuesN.get(i).k.compareTo(key1)>=0&&valuesN.get(i).k.compareTo(key2)<=0)
				values.add(valuesN.get(i).val);
		}
		this.ino.clear();
		return values;
	

		
	}

	public int rotateCount(){
		// write your code her 
		int ans = this.rotations;
		return ans;
	}

	public int getHeight(){
		// write your code her
		int ans = this.height(this.root);
		return ans;
	}
	public int height(Node n){
		int l=0;
		int r=0;
		if(this.nodes==0)
			return 0;
		else if(this.nodes==1)
			return 1;
		else{
			if(n.left==null&&n.right==null)
				return 1;
			if(n.left!=null)
				 l = height(n.left);
			if(n.right!=null)
				 r = height(n.right);
			if(n.left==null&&n.right!=null)
				return r+1;
			if(n.right==null&&n.left!=null)
				return l+1;
			
			if(l>r)
				return l+1;
			else
				return r+1;
		}
	}
	public Vector<K> BFS(){
		// write your code her
		int i=0;
		Node n1 = this.root;
		Vector<Node> v1 = new Vector<Node>();
		Vector<K> v2 = new Vector<K>();
		if(this.nodes==0)
			return v2;
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
		
		for(int w=0;w<v1.size();w++){
			v2.add((v1.get(w)).k);
		}
		return v2; 
	}
	/*public static void main(String[]args){
		WeakAVLMap<String,String> avl = new WeakAVLMap<String,String>();
		avl.put("wlrbbmqb", "41421");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put("darzowk", "89172");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put("hiddqs", "22862");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put("xrjmowfr", "61393");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put("jybldb", "13784");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put("rcbynecdy", "99170");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put("pklor", "50846");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.ino.clear();
		avl.inorder(avl.root);
		for(int i=0;i<avl.ino.size();i++){
			System.out.println("Key "+ avl.ino.get(i).k);
			System.out.println("Value "+ avl.ino.get(i).val);
			System.out.println("Rank "+ avl.ino.get(i).rank);
		}
		System.out.println("Rotations "+avl.rotateCount());
	
		avl.remove("wlrbbmqb");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.ino.clear();
		avl.inorder(avl.root);
		for(int i=0;i<avl.ino.size();i++){
			System.out.println("Key "+ avl.ino.get(i).k);
			System.out.println("Value "+ avl.ino.get(i).val);
			System.out.println("Rank "+ avl.ino.get(i).rank);
		}
		
		avl.remove("pklor");
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		
		/*System.out.println("\n");
		avl.put(33069,98167);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(47793);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(84421);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(2362);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(30886,59956);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(30886,1);
		avl.put(20059,2);
		avl.put(60492,3);   
		avl.put(33069,3);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(30886);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(33069);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(99932,95060);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(10012,36226);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(26652,60756);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(9441,53865);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(8117);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(26652,79497);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(55306,64683);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(26652,48829);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(10012,63368);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(55306);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(92379,97488);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		
		avl.remove(9441);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");


		avl.remove(60492);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		
		avl.put(53275,75407);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(31011);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(22404,64443);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(97369);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(22404);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(53275);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(6725);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");

		avl.remove(26652);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		////////////////////////
		System.out.println("\n");
		avl.put(38082,8542);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(10012);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(38082);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(86708,73416);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(90071,973);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(20059);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(11340,22090);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(92379);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(75321,1255);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(11340,73940);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(86708);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(96658,63920);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(99932);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(41222,39299);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(75321);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(88819);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(11340,85273);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(21860,48142);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(23205,67621);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(11340);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(2021,22842);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.remove(8872);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(21648,72890);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(90071,90368);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.put(73788,49797);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");
		avl.ino.clear();
		avl.inorder(avl.root);
		for(int i=0;i<avl.ino.size();i++){
			System.out.println("Key "+ avl.ino.get(i).k);
			System.out.println("Value "+ avl.ino.get(i).val);
			System.out.println("Rank "+ avl.ino.get(i).rank);
		}
		avl.remove(23205);
		for(int i=0;i<avl.BFS().size();i++){
			System.out.println(avl.BFS().get(i));
		}
		System.out.println("\n");*/

		


	//}*/
	

}


