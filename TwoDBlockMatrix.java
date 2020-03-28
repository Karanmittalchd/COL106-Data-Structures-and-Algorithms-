import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
class InverseDoesNotExistException extends Exception{
	public InverseDoesNotExistException(String s){
		super(s);
	}
}
class SubBlockNotFoundException extends Exception{
	public SubBlockNotFoundException(String s){
		super(s);
	}
}
class IncompatibleDimensionException extends Exception{
	public IncompatibleDimensionException(String s){
		super(s);
	}
}


public class TwoDBlockMatrix{
	public float[][] arr;
	public static TwoDBlockMatrix buildTwoDBlockMatrix(java.io.InputStream in) throws FileNotFoundException{
		Scanner s1 = new Scanner(in);
		Vector<String> str = new Vector<String>();
		int i=0;
		while(s1.hasNextLine()){
			str.add(s1.nextLine());
		}
		int rows=0;
		int col = 0;
		int max_rows=0;
		int max_col=0;
		i=0;
		int rcount=0;
		int ccount=0;
		while(i<str.size()){
			rcount =0;
			ccount=0;
			String sp[] = str.get(i).split("\\s+");
			rows = Integer.parseInt(sp[0]);
			col = Integer.parseInt(sp[1]);
			i++;
			String spc[] = str.get(i).split("\\s+"); 
			ccount = spc.length;
			while(!str.get(i).equals("#")){
				i++;
				rcount++;
			}
			rows = rows+rcount-1;
			col = col + ccount-1;
			if(max_rows<rows)
				max_rows=rows;
			if(max_col<col)
				max_col=col;
			i++;
		}
		float[][] matrix = new float[max_rows][max_col];
		i=0;
		int j=0;
		for(i=0;i<max_rows;i++){
			for(j=0;j<max_col;j++){
				matrix[i][j]=0;
			}
		}
		i=0;
		j=0;
		int q=0;
		while(q<str.size()){
			String spn[]=str.get(q).split("\\s+");
			i=Integer.parseInt(spn[0])-1;
			j=Integer.parseInt(spn[1])-1;
			int tempj=j;
			int tempi=i;
			q++;
			while(!str.get(q).equals("#")){
				String sub = str.get(q).substring(0,str.get(q).length()-1);
				String spb[]=sub.split(" ");
				tempj=j;
				for(int g=0;g<spb.length;g++){
					matrix[tempi][tempj]=Float.parseFloat(spb[g]);
					tempj++;
				}
				tempi++;
				q++;
			}
			q++;
		}
		TwoDBlockMatrix twoDMatrix = new TwoDBlockMatrix(matrix);
		return twoDMatrix;
	}
	public TwoDBlockMatrix(float[][] array){
		
		this.arr = array;
	}

	public String toString(){
		int r = this.arr.length;
		int c = this.arr[0].length;
		float[][] test = new float[r][c];
		float temp=0;
		DecimalFormat format2dec = new DecimalFormat("0.00");
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				test[i][j]=this.arr[i][j];
			}
		}


		String str = new String();
		int q=0;
		int c1=0;
		int c2=0;
		int flag1=0;
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				if(test[i][j]!=0){
					q=j;
					c1=j;
					str = str + (i+1) + " " + (j+1) +"\n";
					//System.out.println(q);
					while(test[i][q]!=0){
						str = str + format2dec.format(test[i][q]) + " ";
						test[i][q]=0;
						q++;
						if(q==c){
							break;
						}

					}
					str = str.substring(0,str.length()-1);
					str = str + ";\n";
					c2=q-1;
					flag1=0;
					int e=0;
					int f=0;
					for(int a=i+1;a<r;a++){
						for(int b=c1;b<=c2;b++){
							if(test[a][b]!=0){
								f=a;
							}else{
								flag1=1;
								f=a-1;
								break;
							}

						}
						if(flag1==1)
							break;
						
						
					}
					
					for(int a=i+1;a<=f;a++){
						for(int b=c1;b<=c2;b++){
							str=str+format2dec.format(test[a][b])+" ";
							test[a][b]=0;
						}
						str = str.substring(0,str.length()-1);
						str = str+";\n";

					}

					str = str+"#\n";
					//System.out.println("b "+e);
					j=q;
					//System.out.println(q);
				}
			}
		}
		return str;
	}
	public TwoDBlockMatrix transpose(){
		int r = this.arr.length;
		int c = this.arr[0].length;
		float[][] tmat = new float[c][r];
		for(int i=0;i<c;i++){
			for(int j=0;j<r;j++){
				tmat[i][j]=this.arr[j][i];
			}
		}
		TwoDBlockMatrix tMatrix = new TwoDBlockMatrix(tmat);
		return tMatrix;
	}
	public TwoDBlockMatrix getSubBlock(int row_start,int col_start,int row_end, int col_end) throws SubBlockNotFoundException{
		int r = row_end-row_start;
		int c = col_end-col_start;
		float[][] mtx = new float[r][c];
		if((row_start<row_end)&&(col_start<col_end)&&(0<row_start)&&(row_end<=this.arr.length)&&(0<col_start)&&(col_end<=this.arr[0].length)){
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				mtx[i][j]=arr[i+row_start-1][j+col_start-1];
			}
		}
		TwoDBlockMatrix mt = new TwoDBlockMatrix(mtx);
		return mt;
		}
		else
			throw new SubBlockNotFoundException("SubBlockNotFound");

	}
	public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException{
		float[][] product = new float[this.arr.length][other.arr[0].length];
		if(this.arr[0].length==other.arr.length){
			for(int i=0;i<this.arr.length;i++){
			for(int j=0;j<other.arr[0].length;j++){
				product[i][j]=0;
				for(int k=0;k<this.arr[0].length;k++){
					product[i][j]=product[i][j]+(this.arr[i][k])*(other.arr[k][j]);
				}
			}
		}
		TwoDBlockMatrix pdt = new TwoDBlockMatrix(product);
		return pdt;
		}
		else
			throw new IncompatibleDimensionException("IncompatibleDimension");
	}
	public TwoDBlockMatrix inverse() throws InverseDoesNotExistException{
		int n = this.arr.length;
		if((determinant(this.arr,n)!=0)&& (this.arr[0].length==n)){
			
		
		float[][] aug = new float[n][2*n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				aug[i][j]=this.arr[i][j];
			}
		}
		for(int i=0;i<n;i++){
			for(int j=n;j<2*n;j++){
				if(j==(i+this.arr.length))
					aug[i][j]=1;
				else
					aug[i][j]=0;
			}
		}
		for(int i=n-1;i>0;i--){
			if(aug[i-1][0]<aug[i][0]){
				for(int j=0;j<2*n;j++){
					float tmp=aug[i][j];
					aug[i][j]=aug[i-1][j];
					aug[i-1][j]=tmp;
				}
			}
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(j!=i){
					float tmp=aug[j][i]/aug[i][i];
					for(int k=0;k<2*n;k++){
						aug[j][k]=aug[j][k]-aug[i][k]*tmp;
					}
				}
			}
		}
		for(int i=0;i<n;i++){
			float tmp=aug[i][i];
			for(int j=0;j<2*n;j++){
				aug[i][j]=aug[i][j]/tmp;
			}
		}
		float[][] inver = new float[n][n];
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				inver[i][j]=aug[i][j+n];
		}
		TwoDBlockMatrix invers = new TwoDBlockMatrix(inver);
		return invers;
	}else
			throw new InverseDoesNotExistException("InverseDoesNotExist");
	}

public float determinant( float matrix [][], int n) {   
   float det = 0;
   float[][] sub = new float[n][n];
   if (n == 2)
      return ((matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]));
   else {
      for (int i = 0; i < n; i++) {
            int si = 0; 
            for (int j = 1; j < n; j++) {
               int sj = 0;
               for (int k = 0; k < n; k++) {
                  if (k == i)
                  continue;
                  sub[si][sj] = matrix[j][k];
                  sj++;
               }
               si++;
            }
            int a = 1;
            if(i%2==0)
            	det = det + (a * matrix[0][i] * determinant( sub, n - 1 ));
            else
            	det = det + (-a * matrix[0][i] * determinant( sub, n - 1 ));
      }
   }
   return det;
}
		/*if((n==this.arr[0].length) && (determinant(this.arr,n)!=0)){
		
			float[][] aug = new float[n][2*n];
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					aug[i][j]=this.arr[i][j];
				}
			}
			for(int i=0;i<n;i++){
				for(int j=n;j<2*n;j++){
					if(j==(i+this.arr.length))
						aug[i][j]=1;
					else
						aug[i][j]=0;
				}
			}
			for(int i=n-1;i>0;i--){
				if(aug[i-1][0]<aug[i][0]){
					for(int j=0;j<2*n;j++){
						float tmp=aug[i][j];
						aug[i][j]=aug[i-1][j];
						aug[i-1][j]=tmp;
					}
				}
			}
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(j!=i){
						float tmp=aug[j][i]/aug[i][i];
						for(int k=0;k<2*n;k++){
							aug[j][k]=aug[j][k]-aug[i][k]*tmp;
						}
					}
				}
			}
			for(int i=0;i<n;i++){
				float tmp=aug[i][i];
				for(int j=0;j<2*n;j++){
					aug[i][j]=aug[i][j]/tmp;
				}
			}
			float[][] inver = new float[n][n];
			
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++)
					inver[i][j]=aug[i][j+n];
			}
			TwoDBlockMatrix invers = new TwoDBlockMatrix(inver);
			return invers;
		}
		else
			throw new InverseDoesNotExistException("InverseDoesNotExist");
	}
public float determinant( float matrix [][], int n) {   
   
   float[][] sub = new float[n][n];
   float det = 0;
   if (n == 2)
      return ((matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]));
   else {
      for (int i = 0; i < n; i++) {
            int si = 0; 
            for (int j = 1; j < n; j++) {
               int sj = 0;
               for (int k = 0; k < n; k++) {
                  if (k == i)
                  continue;
                  sj++;
                  sub[si][sj] = matrix[j][k];
               }
               si++;
            }
            int a = 1;
            if(i%2==0)
            	det = det + (a * matrix[0][i] * determinant( sub, n - 1 ));
            else
            	det = det + (-a * matrix[0][i] * determinant( sub, n - 1 ));
      }
   }
   return det;
}*/
	

}