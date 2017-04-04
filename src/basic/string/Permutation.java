package basic.string;

/**
 * Created by Andrew Ma on 4/3/2017.
 * this is naturally what the permutation means: first position, picking one from n, second position, picking one from n-1,...
 * Another way is using swap and backtrack to do all permutation
 */
public class Permutation {
    /**
     * perm is what are formed so far, word is bank of available character to use for next step. word length is reducing level by level
     * @param perm
     * @param word
     */
    private static void permute(String perm,String word){
        if(word.isEmpty())
            System.out.println(perm);//done one permutation
        else{
            for(int i=0;i<word.length();i++){
                permute(perm+word.charAt(i),word.substring(0,i)+word.substring(i+1));
            }
        }
    }
    public static void main(String... args){
        Permutation.permute("","123");
    }
}
