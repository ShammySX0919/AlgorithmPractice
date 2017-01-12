package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * This is same as word break.
 * it is to figure out shortest path to end, BFS is first thought
 * Analysis:
 * BFS for all possible reaches, and return the minimum path.
 * while BFS, only access the ones in bank.
 *
 * Spent time on bug at continue statement: there, before continue, it should
 * check if it needs to go to next level and increase steps.
 *
 * Created by Andrew Ma on 10/28/2016.
 */
public class Draft433_MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        if(end!=null && end.equals(start))return 0;
        Set<String> dict = new HashSet<String>();
        int minSteps=0,curlevelChildren=1,nextLevelChildren=0;
        for(String s:bank){
            dict.add(s);
        }
        char[] validChars = new char[]{'A','C','G','T'};
        //bfs a  c   g    t   a  cgt's neighbors are
        //   cgt agt act acg cgt ...
        Set<String> visited = new HashSet<String>();
        Queue<String> q = new LinkedList<String>();
        q.add(start);
        boolean found=false;
        while(!q.isEmpty()){
            String curString = q.poll();
            curlevelChildren--;
            if(visited.contains(curString)){
                if(curlevelChildren==0){
                    curlevelChildren=nextLevelChildren;
                    nextLevelChildren=0;
                    minSteps++;//first loop set this to 0, so it counts properly one next level
                }
                continue;
            }
            if(end.equals(curString)){
                found=true;
                break;
            }
            visited.add(curString);
            for(int i=0;i<curString.length();i++){
                for(char c:validChars){
                    if(curString.charAt(i)!=c){
                        //make new string and add it to queue
                        String newString = curString.substring(0,i)+c+curString.substring(i+1);
                        if(dict.contains(newString)) {
                            q.add(newString);
                            nextLevelChildren++;
                        }
                    }
                }
            }
            if(curlevelChildren==0){
                curlevelChildren=nextLevelChildren;
                nextLevelChildren=0;
                minSteps++;//first loop set this to 0, so it counts properly one next level
            }

        }
        return found?minSteps:-1;
    }
    public static void main(String[] args){
        Draft433_MinimumGeneticMutation o = new Draft433_MinimumGeneticMutation();
//        System.out.println(o.minMutation("AACCGGTT","AACCGGTA",new String[]{"AACCGGTA"}));
//        System.out.println(o.minMutation("AACCGGTT","AAACGGTA",new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
//        System.out.println(o.minMutation("AAAAACCC","AACCCCCC",new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"}));
        System.out.println(o.minMutation("AAAAAAAA","CCCCCCCC",new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA","CCCCCCCC"}));
    }
}
