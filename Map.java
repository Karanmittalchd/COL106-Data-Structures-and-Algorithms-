package col106.assignment4.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class Map<V> {
	static PrintStream out;
	public PrintStream fileout(){
		return out;
	}
	
	public Map() {
		// write your code here	
	}
	public void eval(String inputFileName, String outputFileName)throws IOException, FileNotFoundException{
		HashMap<String> hash = new HashMap<String>((int)1000000);
		WeakAVLMap<String,String> avl = new WeakAVLMap<String,String>();
		File file;
		file = new File(inputFileName);
		out = new PrintStream(new FileOutputStream(outputFileName, false), true);
		System.setOut(out);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String st = br.readLine().trim();
			int n= Integer.parseInt(st);
			//System.out.println(n);
			long hashremove=0;
			long avlremove=0;
			long hashput=0;
			long avlput=0;
			long difference;
			long endTime;
			long beginTime;
			String str;
			Vector<String> v=new Vector<String>();

			for(int i=0;i<n;i++){
				str=br.readLine().trim();
				v.add(str);
				//System.out.println(st);
			}
			for(int i=0;i<v.size();i++){
				if(v.get(i).charAt(0)=='I'){
					int comma = 0;
					for(int j=2;j<v.get(i).length();j++){
						if(v.get(i).charAt(j)==',')
							comma = j;
					}
					beginTime = System.currentTimeMillis();
					hash.put(v.get(i).substring(2,comma),v.get(i).substring(comma+2,v.get(i).length()));
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					hashput = hashput + difference;
					beginTime = System.currentTimeMillis();

					avl.put(v.get(i).substring(2,comma),v.get(i).substring(comma+2,v.get(i).length()));
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					avlput = avlput + difference;
				}
				if(v.get(i).charAt(0)=='D'){
					beginTime = System.currentTimeMillis();
					hash.remove(v.get(i).substring(2,v.get(i).length()));
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					hashremove = hashremove + difference;
					beginTime = System.currentTimeMillis();
					avl.remove(v.get(i).substring(2,v.get(i).length()));
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					avlremove = avlremove + difference;
				}
			}

			System.out.println("Operations" + "\t" + "WAVL" + "\t" + "HashMap");
			System.out.println("Insertions" + "\t" + avlput + "\t" + "\t" + hashput);
			System.out.println("Deletions" + "\t" + avlremove + "\t" + "\t" + hashremove);

		}catch (FileNotFoundException e) {
				System.err.println("Input file Not found. ");
		}catch (Exception e) {
					e.printStackTrace();
		} 
	}

}

