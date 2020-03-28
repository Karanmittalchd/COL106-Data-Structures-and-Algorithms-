package col106.assignment4.HashMap;
import java.util.Vector;

public class HashMap<V> implements HashMapInterface<V> {
	class Node<V>{
		String k;
		V val;
	}
	public int nodes=0;

	public int lengt = 0;
	public HashMap(int size) {
		// write your code here
		this.lengt = size;
		Node[] arr = new Node[size];
		this.harr = arr;


	}
	public Node[] harr = new Node[lengt];

	public int hashfn(String key){
		int a = 1;
		int ans = 0;
		for(int i=0;i<key.length();i++){
			ans = (ans + (a*(key.charAt(i))%this.lengt)%(this.lengt))%this.lengt;
			a = (a*41)%(this.lengt); 
		}
		ans = ans%this.lengt;
		return ans;
	}

	public V put(String key, V value){
		// write your code here
		
		int ans = this.hashfn(key);
		//System.out.println("HashCode "+ans);
		if(this.harr[ans]==null){
			this.harr[ans] = new Node<V>();
			this.harr[ans].k = key;
			this.harr[ans].val = value;
		}else{
			V prev = null;
			prev = (V) this.harr[ans].val;
			if((this.harr[ans].k).equals(key)){
				this.harr[ans].val = value;
				return prev;
			}
			else{	
					ans = ans+1;
					ans = ans%this.lengt;

				while(this.harr[ans]!=null){
					
					if((this.harr[ans].k).equals(key)){
						prev = (V) this.harr[ans].val;
						this.harr[ans].val = value;
						return prev;
					}
					ans = ans+1;
					ans = ans%this.lengt;
				}
				this.harr[ans] = new Node<V>();
				this.harr[ans].k = key;
				this.harr[ans].val = value;
			}
		}
		this.nodes++;
		return null;
	}

	public V get(String key){
		// write your code here
		if(this.nodes==0)
			return null;
		
		int ans = this.hashfn(key);
		int j = ans+1;
		j = j%this.lengt;
		if(this.harr[ans]!=null){
			if((this.harr[ans].k).equals(key))
				return (V) this.harr[ans].val;
			else{
				while(j!=ans){
					if(this.harr[j]!=null){
						if((this.harr[j].k).equals(key))
							return (V) this.harr[j].val;
						else{
							j = j+1;
							j = j%this.lengt;
						}
					}
					else
						return null;
				}
			}
		}
		return null;
	}

	public boolean remove(String key){
		// write your code here
		if(this.nodes==0)
			return false;
		int flag=0;
		int ans = this.hashfn(key);
		int j = ans+1;
		j = j%this.lengt;
		if(this.harr[ans]!=null){
			if((this.harr[ans].k).equals(key)){
				this.harr[ans] = null;
				flag=1;
			}else{
				while(j!=ans){
					if(this.harr[j]==null)
						break;
					
					if((this.harr[j].k).equals(key)){
						this.harr[j] = null;
						flag=1;
						ans = j;
						break;
					}
					j = j+1;
					j = j%this.lengt;
				}
			}
			if(flag==0)
				return false;
			
			j = ans+1;
			j = j%this.lengt;
			int pans = ans;
			while(j!=ans){
				if(this.harr[j]==null)
					break;
				int hashans = this.hashfn(this.harr[j].k);
				if(j>=hashans&&pans>=hashans){
					if(j>ans){
						this.harr[pans] = this.harr[j];
						this.harr[j] = null;
						pans = j;
						
					}
					j = j+1;
					j = j%this.lengt;
					continue;
				}
				if(j<hashans&&pans>=hashans){
					this.harr[pans] = this.harr[j];
						this.harr[j] = null;
						pans=j;
						j = j+1;
						j = j%this.lengt;
						continue;

				}
				if(j>=hashans&&pans<hashans){
					j = j+1;
					j = j%this.lengt;
					continue;
				}
				if(j<hashans&&pans<hashans){
					if(j>pans){
						this.harr[pans] = this.harr[j];
						this.harr[j] = null;
						pans=j;
						
					}
					j = j+1;
					j = j%this.lengt;
					continue;
				}
			}
		}else
			return false;
		if(flag==1){
			this.nodes--;
			return true;
		}
		return false;
	}

	public boolean contains(String key){
		// write your code here
		if(this.nodes==0)
			return false;
		if(this.get(key)==null)
			return false;
		else 
			return true;
	}

	public Vector<String> getKeysInOrder(){
		// write your code here
		Vector<String>str = new Vector<String>();
		if(this.nodes==0)
			return str;
		for(int i = 0;i<this.lengt;i++){
			if(this.harr[i]!=null){
				if(this.harr[i].k!=null){
					str.add(this.harr[i].k);
				}
			}
		}
		return str;
	}

}
