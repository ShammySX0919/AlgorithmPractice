package expedia.online;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CanYouSort {
	public static void sortByrequencyAndNaturalOrder(int[] arr){
		if(arr==null ||arr.length==0)return;
		if(arr.length==1){System.out.println(arr[0]);return;}
		Map<Integer,Integer> stats = new TreeMap<>();
		//get frequency statistics
		for(int  i:arr){
			stats.put(i, stats.getOrDefault(i, 0)+1);
		}
		Map<Integer,List<Integer>> frequencyAndNum = new TreeMap<>();
		for(Map.Entry<Integer, Integer> e:stats.entrySet()){
			if(!frequencyAndNum.containsKey(e.getValue())){
				frequencyAndNum.put(e.getValue(), new ArrayList<Integer>());
			}
			frequencyAndNum.get(e.getValue()).add(e.getKey());
		}
		for(Map.Entry<Integer, List<Integer>> e:frequencyAndNum.entrySet()){
			Collections.sort(e.getValue());
			e.getValue().forEach(p->System.out.print(p+" "));
		}
	}

}
