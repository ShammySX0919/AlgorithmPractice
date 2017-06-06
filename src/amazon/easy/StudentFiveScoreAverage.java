package amazon.easy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Input is List<result> Where result is: result { int id; int score; } Output
 * should be a map. The question is there are a list of students where each of
 * student has at least five scores. We want to output a map where key is the
 * student and value is the average of studrtns's highest five scores
 * 
 * @author Andrew
 *
 */
public class StudentFiveScoreAverage {
	static class Result {
		int id, score;
	}

	public Map<Integer, Double> scoresAverage(List<Result> results) {

		Map<Integer, PriorityQueue<Integer>> scoresMap = new HashMap<Integer, PriorityQueue<Integer>>();
		for (Result result : results) {
			if (scoresMap.containsKey(result.id)) {
				PriorityQueue<Integer> pq = scoresMap.get(result.id);
				pq.add(result.score);
				scoresMap.put(result.id, pq);
			} else {
				// Remember this to save time. no need to implement Comparator
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
						Collections.reverseOrder());
				pq.add(result.score);
				scoresMap.put(result.id, pq);
			}
		}

		Map<Integer, Double> resultMap = new HashMap<Integer, Double>();
		for (Map.Entry<Integer, PriorityQueue<Integer>> entry : scoresMap
				.entrySet()) {
			PriorityQueue<Integer> pq = entry.getValue();
			double average = getAverage(pq, 5);
			resultMap.put(entry.getKey(), average);
		}
		return resultMap;
	}

	private double getAverage(PriorityQueue<Integer> pq, int number) {
		double total = 0.0;
		int count = 0;//can be less than number
		while (count < number && !pq.isEmpty()) {
			total = total + pq.remove();
			count++;
		}
		return total / count;
	}
}
