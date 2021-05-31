package col106.assignment4.HashMap;

public class WordCounter {

	public WordCounter(){
		// write your code here
	}

	public int count(String str, String word){
		// write your code here
		int ans=0;
		int n1 = str.length();
		int n2 = word.length();
		if(n2>n1)
			return 0;
		HashMap<Integer> hash = new HashMap<Integer>((int)1000000);

		int i=0;
		while(i<n1){
			if(str.charAt(i)==word.charAt(0)){
				if(i+n2<=n1&&str.substring(i,i+n2).equals(word)){
				hash.put(word,ans);						
				ans++;

				}
			}
			i++;
		}

		return ans;
	}
	
}
