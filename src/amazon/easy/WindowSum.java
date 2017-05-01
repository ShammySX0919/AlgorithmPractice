package amazon.easy;

/**
 * Created by 212595974 on 4/27/2017.
 */
public class WindowSum {
    /**
     *
     * k is window size
     * @param arr
     * @param k
     * @return
     */
    public static void getWindowSum(int[] arr,int k){
        long sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];//adding coming element to sum, this is right boundary of window
            if(i+1>=k){
                //if reaching to point of k length
                System.out.println(sum);//print it out
                sum-=arr[i+1-k];//and minus left boundary
            }
        }
        //in case window size is bigger than array length, it is just the sum
        if(arr.length<k) {
            System.out.println(sum);
        }
    }
    public static void main(String[] args){
        getWindowSum(new int[]{1,2,3,4,5,6,7,8,9},20);
    }
}
