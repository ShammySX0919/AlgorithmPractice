package amazon.oa.y2017;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 438
 * Created by andrew on 5/15/2017.
 */
public class FindAllAnagrams {

    int[] buildStats(String s, int start, int len){
        int[] stats = new int[26];
        for(int i=start;i<start+len;i++){
            stats[s.charAt(i)-'a']++;
        }
        return stats;
    }
    private boolean featureMatch(String s,int index,int len,int[] stats){
        int[] newStats = new int[26];
        System.arraycopy(stats,0,newStats,0,26);
        for(int i=index;i<index+len;i++){
            char c = s.charAt(i);
            int ind = c-'a';
            newStats[ind]--;
            if(newStats[ind]<0)return false;
        }
        for(int i=0;i<26;i++){
            if(newStats[i]>0)return false;
        }
        return true;
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if(s==null ||s.length()==0||s.length()<p.length())return result;
        //build up pattern feature
        int[] statsP = buildStats(p,0,p.length());

        for(int i=0;i<=s.length()-p.length();i++){
            if(featureMatch(s,i,p.length(),statsP)){
                result.add(i);
            }
        }
        return result;
    }
    //sliding windows
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if(s == null || p == null || s.length() < p.length())   return list;
        int count = p.length();
        int lengths = s.length();
        int[] stats = new int[26];
        //collect statistics.
        for(int i = 0; i < count; i ++){
            ++stats[p.charAt(i) - 'a'];
        }
        //boundary of windows. right is moving to check
        int left = 0, right = 0;
        while(right < lengths){
            //move right in each loop, if the character exists in p's stats, decrease the count
            //current stat value >= 1 means the character is existing in p
            if(stats[s.charAt(right) - 'a']-- > 0){ count--;}
            right++;
            //when all characters are found, add left index to result list
            if(count == 0)list.add(left);
            //if we find the window's size equals to p, then we have to move left (narrow the window)
            // to find the new match window
            //++ to reset the stat because we now exclude the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the stat, cuz it won't go below 0
            if(right - left == p.length() ){
                if(stats[s.charAt(left) - 'a']++ >= 0){
                    count++;
                }
                left++;
            }
        }
        return list;
    }
}
