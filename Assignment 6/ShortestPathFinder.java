package col106.assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.Comparator;

class DistanceComparator implements Comparator<VertexDistance>{
	public int compare(VertexDistance a,VertexDistance b){
		if(a.distance>b.distance){
  			return 1;
  		}else{
  			return -1;

  		}	
	
	}
}
public class ShortestPathFinder implements ShortestPathInterface {
    /**
     * Computes shortest-path from the source vertex s to destination vertex t 
     * in graph G.
     * DO NOT MODIFY THE ARGUMENTS TO THIS CONSTRUCTOR
     *
     * @param  G the graph
     * @param  s the source vertex
     * @param  t the destination vertex 
     * @param left the cost of taking a left turn
     * @param right the cost of taking a right turn
     * @param forward the cost of going forward
     * @throws IllegalArgumentException unless 0 <= s < V
     * @throws IllegalArgumentException unless 0 <= t < V
     * where V is the number of vertices in the graph G.
     */
    int left;
    int right;
    int forward;
    Vertex source;
    Vertex target;
    Digraph dg;
    int dualedge=0;
    HashMap<Integer,DualGraphNode> dualmap = new HashMap<>();
    ArrayList<DualGraphEdge>[] dualadj;
    HashMap<Vector,Integer> hm1 = new HashMap<>();
    Vector parent;
    int [] distance;
    int sd;
    public ShortestPathFinder (final Digraph G, final int[] s, final int[] t, 
    final int left, final int right, final int forward) {
        // YOUR CODE GOES HERE
        this.dg = G;
        this.source = new Vertex(s[0],s[1],s[0]* this.dg.W()+s[1]);
        this.target = new Vertex(t[0],t[1],t[0]* this.dg.W()+t[1]);
        this.left = left;
        this.right = right;
        this.forward = forward;
	}

    // Return number of nodes in dual graph
    public int numDualNodes() {
        // YOUR CODE GOES HERE
        int a = (this.dg).E()+1;
        return a;
    }

    // Return number of edges in dual graph
    public int numDualEdges() {
        // YOUR CODE GOES HERE
        dualGraph();
        return this.dualedge;
    }

    // Return hooks in dual graph
    // A hook (0,0) - (1,0) - (1,2) with weight 8 should be represented as
    // the integer array {0, 0, 1, 0, 1, 2, 8}
    public ArrayList<int[]> dualGraph() {
        // YOUR CODE GOES HERE
        int key_code = 0;
        this.dualadj = new ArrayList[this.numDualNodes()];
        for (int a = 0; a < this.numDualNodes(); a++) { 
            this.dualadj[a] = new ArrayList<DualGraphEdge>(); 
        }		
    			

        HashMap<Vector,Integer> hm = new HashMap<>();
        
    	ArrayList<int[]> dualgr = new ArrayList<int[]>();
    	int start=0;
    	int end=0;
    	int count1=0;
    	for(Edge e1 : dg.adj(this.source.key)){
    		int[] arr = new int[7];
    		Vector v = new Vector();
    		Vector<Integer> node1 = new Vector<>();
    		Vector<Integer> node2 = new Vector<>();
	    	arr[0]=-1;
	    	arr[1]=-1;
	    	arr[2]=(this.source).i;
	    	arr[3]=(this.source).j;
    		arr[4] = dg.nodemap(e1.to()).i;
    		arr[5] = dg.nodemap(e1.to()).j;
    		arr[6] = (int)e1.weight();
    		count1++;
    		this.dualedge++;
    		v.add(arr[0]);
    		v.add(arr[1]);
    		v.add(arr[2]);
    		v.add(arr[3]);
    		v.add(arr[4]);
    		v.add(arr[5]);
    		v.add(arr[6]);
    		node1.add(arr[0]);
    		node1.add(arr[1]);
    		node1.add(arr[2]);
    		node1.add(arr[3]);
    		node2.add(arr[2]);
    		node2.add(arr[3]);
    		node2.add(arr[4]);
    		node2.add(arr[5]);
    		if(!(this.hm1.containsKey(node1))){
    			
    			this.hm1.put(node1,key_code);

    			DualGraphNode dgn1 = new DualGraphNode(node1.get(0),node1.get(1),node1.get(2),node1.get(3),key_code);

    			this.dualmap.put(key_code,dgn1);
    			key_code++;
    		}
    		if(!(this.hm1.containsKey(node2))){
    			this.hm1.put(node2,key_code);
    			DualGraphNode dgn2 = new DualGraphNode(node2.get(0),node2.get(1),node2.get(2),node2.get(3),key_code);
    			this.dualmap.put(key_code,dgn2);
    			key_code++;
    		}
    		if(!(hm.containsKey(v))){
    			dualgr.add(arr);
    			hm.put(v,1);
    			DualGraphEdge dge = new DualGraphEdge(this.hm1.get(node2),arr[6]);
    			this.dualadj[this.hm1.get(node1)].add(dge);
    		}
    	}
    	int it;
    	int flag=0;
    	while(true){
	    	start = end;
	    	end = count1;
	    	flag=0;
	    	for(it=start;it<end;it++){
	    		for(Edge e1 : dg.adj(dualgr.get(it)[4]*this.dg.W()+dualgr.get(it)[5])){
	    			int[] arr = new int[7];
	    			Vector v = new Vector();
	    			Vector<Integer> node1 = new Vector();
    				Vector<Integer> node2 = new Vector();
			    	arr[0]=dualgr.get(it)[2];
			    	arr[1]=dualgr.get(it)[3];
			    	arr[2]=dualgr.get(it)[4];
			    	arr[3]=dualgr.get(it)[5];
		    		arr[4] = dg.nodemap(e1.to()).i;
		    		arr[5] = dg.nodemap(e1.to()).j;
		    		if(arr[3]-arr[1]==1&&arr[0]==arr[2]){
		    			if(arr[5]-arr[3]==1){
		    				arr[6] = (int) e1.weight()+this.forward;
		    			}else if(arr[4]-arr[2]==1){
		    				arr[6] = (int) e1.weight()+this.right;
		    			}else{
		    				arr[6] = (int) e1.weight()+this.left;

		    			}
		    		}
		    		if(arr[2]-arr[0]==1&&arr[1]==arr[3]){
		    			if(arr[4]-arr[2]==1){
		    				arr[6] = (int) e1.weight()+this.forward;
		    			}else if(arr[5]-arr[3]==-1){
		    				arr[6] = (int) e1.weight()+this.right;
		    			}else{
		    				arr[6] = (int) e1.weight()+this.left;

		    			}
		    		}
		    		if(arr[2]-arr[0]==-1&&arr[1]==arr[3]){
		    			if(arr[4]-arr[2]==-1){
		    				arr[6] = (int) e1.weight()+this.forward;
		    			}else if(arr[5]-arr[3]==1){
		    				arr[6] = (int) e1.weight()+this.right;
		    			}else{
		    				arr[6] = (int) e1.weight()+this.left;

		    			}
		    		}
		    		if(arr[3]-arr[1]==-1&&arr[0]==arr[2]){
		    			if(arr[5]-arr[3]==-1){
		    				arr[6] = (int) e1.weight()+this.forward;
		    			}else if(arr[4]-arr[2]==-1){
		    				arr[6] = (int) e1.weight()+this.right;
		    			}else{
		    				arr[6] = (int) e1.weight()+this.left;

		    			}
		    		}

		    		v.add(arr[0]);
		    		v.add(arr[1]);
		    		v.add(arr[2]);
		    		v.add(arr[3]);
		    		v.add(arr[4]);
		    		v.add(arr[5]);
		    		v.add(arr[6]);
		    		node1.add(arr[0]);
		    		node1.add(arr[1]);
		    		node1.add(arr[2]);
		    		node1.add(arr[3]);
		    		node2.add(arr[2]);
		    		node2.add(arr[3]);
		    		node2.add(arr[4]);
		    		node2.add(arr[5]);
		    		if(!(this.hm1.containsKey(node1))){
		    			this.hm1.put(node1,key_code);
		    			DualGraphNode dgn1 = new DualGraphNode(node1.get(0),node1.get(1),node1.get(2),node1.get(3),key_code);
		    			this.dualmap.put(key_code,dgn1);
		    			key_code++;
		    		}
		    		if(!(this.hm1.containsKey(node2))){
		    			this.hm1.put(node2,key_code);
		    			DualGraphNode dgn2 = new DualGraphNode(node2.get(0),node2.get(1),node2.get(2),node2.get(3),key_code);
		    			this.dualmap.put(key_code,dgn2);
		    			key_code++;
		    		}
		    		if(!hm.containsKey(v)){
    					dualgr.add(arr);
    					hm.put(v,1);
    					DualGraphEdge dge = new DualGraphEdge(this.hm1.get(node2),arr[6]);
    					this.dualadj[(this.hm1.get(node1))].add(dge);
    					flag=1;
    					count1++;
    					this.dualedge++;
    				}
	    		}
	    	}
	    	if(flag==0)
	    		break;
	    }

    	int it1=0;
    	return dualgr;
    }

    // Return true if there is a path from s to t.
    public boolean hasValidPath() {
        // YOUR CODE GOES HERE
    	if(ShortestPathValue()<Integer.MAX_VALUE)
    		return true;
        return false;
    }

    // Return the length of the shortest path from s to t.
    public int ShortestPathValue() {
        // YOUR CODE GOES HERE
        dualGraph();
        this.parent = new Vector();
        int i;
        for(i=0;i<this.numDualNodes();i++){
        	this.parent.add(0);
        }
    	PriorityQueue<VertexDistance>pq = new PriorityQueue<VertexDistance>(this.numDualNodes(),new DistanceComparator());
    		i = 1;
    		this.distance = new int[this.numDualNodes()];
    		this.distance[0]=0;
    		for(i=1;i<this.numDualNodes();i++){
    			this.distance[i] = Integer.MAX_VALUE;
    		}
    		i=1;
    		VertexDistance source = new VertexDistance(0,0);
    		pq.add(source);
    		while(i < this.numDualNodes()){
    			VertexDistance vd = new VertexDistance(i,Integer.MAX_VALUE);
    			pq.add(vd);
    			i++;
    		}
    		int top=0;
    		while(pq.peek()!=null){
    			top = pq.peek().vkey;
    			pq.remove();
    			for(int j=0;j<this.dualadj[top].size();j++){
    				
    				final int top1 = top;
    				final int j1 = j;

    				Predicate<VertexDistance> condition = a -> a.vkey == this.dualadj[top1].get(j1).end;
    				
    				this.distance[this.dualadj[top].get(j).end] = Math.min(this.distance[dualadj[top].get(j).end],this.distance[top]+dualadj[top].get(j).weight);
    				if(this.distance[this.dualadj[top].get(j).end] == this.distance[top]+dualadj[top].get(j).weight){
    					int q = (int)this.dualadj[top].get(j).end;
    					this.parent.set(q,top);
    				}
    				pq.removeIf(condition);
    				VertexDistance vd1 = new VertexDistance(this.dualadj[top].get(j).end,this.distance[this.dualadj[top].get(j).end]);
    				pq.add(vd1);

    			}

    		}
    		int answer = Integer.MAX_VALUE;
    		for(HashMap.Entry mapElement : this.hm1.entrySet()) { 
	            Vector v1 = (Vector)mapElement.getKey(); 
	            if((int)v1.get(2)==this.target.i&&(int)v1.get(3)==this.target.j){
	            	answer = Math.min(this.distance[(int)mapElement.getValue()],answer);
	            	
	            }
	  
	            
	        } 

   		this.sd = answer;
        return answer;
    }

    // Return the shortest path computed from s to t as an ArrayList of nodes, 
    // where each node is represented by its location on the grid.
    public ArrayList<int[]> getShortestPath() {
        // YOUR CODE GOES HERE
        int i=0;
        int target_key=0;
        
        ArrayList<int[]> a1 = new ArrayList<int[]>();
        ArrayList<int[]> b1 = new ArrayList<int[]>();
        int ans=Integer.MAX_VALUE;
        for(HashMap.Entry mapElement : this.hm1.entrySet()) { 
	            Vector v1 = (Vector)mapElement.getKey(); 
	            if((int)v1.get(2)==this.target.i&&(int)v1.get(3)==this.target.j){
	            	 if(this.distance[(int)mapElement.getValue()]==this.sd){
	            	 	target_key = (int)mapElement.getValue();
	            	 }

	            }
	  
	            
	        }
	    i=target_key;
	    while(i!=0){
	    	int[] a = new int[2];
        	a[0] = dualmap.get(i).i2;
        	a[1] = dualmap.get(i).j2;
        	a1.add(a);
	    	i = (int)this.parent.get(i);
	    }
	    int[] b = new int[2];
        b[0] = dualmap.get(i).i2;
        b[1] = dualmap.get(i).j2;
        a1.add(b);
        i=0;
        for(i=a1.size()-1;i>=0;i--){
        	b1.add(a1.get(i));
        } 
        return b1;
    }
}
