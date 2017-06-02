package lintcode.ladder.searchandrecursion;

import java.util.*;

/**
 * Created by andrew on 5/28/2017.
 */
public class WordLadderII {
    /**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return a list of lists of string
     */
    //abservaion:from start to end, every time, the difference is one
    //character, and you forget about the previous word when you start from
    //current word
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        //all possible paths--shortest--result of BFS, traversed by DFS
        List<List<String>> ladders = new ArrayList<List<String>>();
        //adjacency list of word connections. R way graph or tree
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //
        Map<String, Integer> distance = new HashMap<String, Integer>();

        //bfs prepare adjacency list of all possible transitions dict words
        //distance here is rather a hashset to avoid cycle
        bfs(map, distance, start, end, dict);

        List<String> path = new ArrayList<String>();//to capture the paths
        Set<String> visited = new HashSet<String>();//mark visited

        dfs(ladders, path, start, end, map,visited);//
        //above algorithm added all possible paths
        //let's keep just shorted paths
        Map<Integer,ArrayList<List<String>>> sorting = new HashMap<Integer,ArrayList<List<String>>>();
        int minLen = Integer.MAX_VALUE;
        for(List<String> al:ladders){
            if(sorting.containsKey(al.size())){
                sorting.get(al.size()).add(al);
            }else{
                ArrayList<List<String>> als = new ArrayList<List<String>>();
                als.add(al);
                sorting.put(al.size(),als);
            }
            minLen =  Math.min(minLen,al.size());
        }
        return sorting.get(minLen);
    }
    //using dfs to figure out all of paths between start and end
    void dfs(List<List<String>> ladders, List<String> path,
             String start, String end,
             Map<String, List<String>> map,
             Set<String> visited) {
        if (visited.contains(start))return;
        else
            visited.add(start);
        path.add(start);
        if (end.equals(start)) {
            ladders.add(new ArrayList<String>(path));
        }
        else {
            //do dfs for each neighbors
            for (String next : map.get(start)) {

                dfs(ladders, path, next, end, map,visited);

            }
        }
        //reset status so that same position can be revisited for different starting points.
        //path and visited need to avoid cycle only in each of single traversal
        path.remove(path.size() - 1);
        visited.remove(start);
    }
    //forget about distance fow now
    //then this bfs is to prepare an adjacency list
    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
             String start, String end, Set<String> dict) {
        //use queue to assist bfs
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        //for each word in queue--possible starting point,
        //parepare a list of word that is only one character difference from
        //given word, and they are in dict
/*      I do not think this is goood idea. rather to build adjacency list for
only those being involbed. and it works.
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
*/
        while (!q.isEmpty()) {
            String startWord = q.poll();

            List<String> nextList = getAllOneDistanceDictWord(startWord, dict);
            map.put(startWord,nextList);//use this to replace the above for loop
            //if(startWord.equals(end))break;//do not do extra
            for (String next : nextList) {
                //map.get(next).add(startWord);//removed along with above for loop
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(startWord) + 1);
                    q.offer(next);
                    // if(next.equals(end)){//do not do extra
                    //   break;//stop at this level because end was found
                    //}
                }
            }
        }
    }

    List<String> getAllOneDistanceDictWord(String startWord, Set<String> dict) {
        List<String> adjacencyWords = new ArrayList<String>();

        for (int i = 0; i < startWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != startWord.charAt(i)) {//only for different words
                    String expanded = startWord.substring(0, i) + ch
                            + startWord.substring(i + 1);
                    if (dict.contains(expanded)) {
                        adjacencyWords.add(expanded);
                    }
                }
            }
        }

        return adjacencyWords;
    }

}
