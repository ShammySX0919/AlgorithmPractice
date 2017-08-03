package ea;

public class PrintDiamond {
	//first version,use a matrix for easy thinking 
	static void print(int n){
		if(n<=0 ||n%2==0)return;
		int[][] mat = new int[n][n];
		int middle = n/2;
		int offset = 0;
		for(int r=middle;r>=0;r--){//for n/2 +1 rows
			offset = middle - r;//offset based on middle
			for(int c = 0;c<n;c++){//for each column
				if(c>=offset && c<n-offset){//condition to set 1
					mat[middle-offset][c] = 1;//offset is based on middle
					if(offset!=0){//middle row prints only once
						mat[middle+offset][c] = 1;
					}
				}
			}
		}
		
		for(int r=0;r<n;r++){
			for(int c=0;c<n;c++){
				System.out.print(mat[r][c]==0?" ":"*");
			}
			System.out.println();
		}
	}
	//second version, remove matrix
	static void print2(int n){
		if(n<=0 ||n%2==0)return;
		
		int middle = n/2;
		int offset = 0;//valid position away from middle. 
		for(int r=0;r<n;r++){//each row
			//offset is changing differently before and after middle
			if(r<=middle)
				offset = r;
			else
				offset = n-1-r;//last row offset is 0, so to verify n-1 - r is correct
			for(int c = 0;c<n;c++){//for each column
				if(c>=middle - offset && c<=middle+offset){
					System.out.print("*");
					
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	public static void main(String[] strings){
		print2(5);
	}

}
