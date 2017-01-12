package hackerrank.crackthecodinginterview.graph.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ShortestReachInAGraph {
	public static class Graph {

		int vertices;
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

		public Graph(int size) {
			vertices = size;
		}

		public void addEdge(int first, int second) {
			// build up adjacency list
			if (adjList.containsKey(first)) {
				if (!adjList.get(first).contains(second)) {
					adjList.get(first).add(second);
				}
			} else {
				List<Integer> neighbors = new ArrayList<Integer>();
				neighbors.add(second);
				adjList.put(first, neighbors);
			}
			// undirected, both ways
			if (adjList.containsKey(second)) {
				if (!adjList.get(second).contains(first)) {
					adjList.get(second).add(first);
				}
			} else {
				List<Integer> neighbors = new ArrayList<Integer>();
				neighbors.add(first);
				adjList.put(second, neighbors);
			}
		}

		private int[] buildSPT(int startId) {
			// since edge's weight is all 1, no edge need to be built, they
			// comes in same priority
			// then you can do a simple BFS and keeps the path heights in an
			// array
			int[] edgeTo = new int[vertices];
			// initilize it with maximum distance
			// for(int i=0;i<vertices;i++){
			// edgeTo[i] = Integer.MAX_VALUE;
			// }
			Arrays.fill(edgeTo, Integer.MAX_VALUE);
			// set to itself is 0
			edgeTo[startId] = 0;
			boolean[] visited = new boolean[vertices];
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(startId);
			while (!q.isEmpty()) {
				int vertext = q.poll();
				if (visited[vertext])
					continue;
				if (adjList.get(vertext) != null
						&& adjList.get(vertext).size() > 0) {
					for (int adj : adjList.get(vertext)) {
						// 1 means 6

						if (edgeTo[adj] > edgeTo[vertext] + 1) {
							edgeTo[adj] = edgeTo[vertext] + 1;
						}
						q.add(adj);

					}
				}
				visited[vertext] = true;// relax it because it has been used to
			}
			return edgeTo;
		}

		public int[] shortestReach(int startId) { // 0 indexed
			int result[] = buildSPT(startId);
			for (int i = 0; i < vertices; i++) {
				if (result[i] == Integer.MAX_VALUE) {
					result[i] = -1;
				} else {
					result[i] *= 6;
				}
			}
			return result;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int queries = scanner.nextInt();

		for (int t = 0; t < queries; t++) {

			// Create a graph of size n where each edge weight is 6:
			Graph graph = new Graph(scanner.nextInt());
			int m = scanner.nextInt();

			// read and set edges
			for (int i = 0; i < m; i++) {
				int u = scanner.nextInt() - 1;
				int v = scanner.nextInt() - 1;

				// add each edge to the graph
				graph.addEdge(u, v);
			}

			// Find shortest reach from node s
			int startId = scanner.nextInt() - 1;
			int[] distances = graph.shortestReach(startId);

			for (int i = 0; i < distances.length; i++) {
				if (i != startId) {
					System.out.print(distances[i]);
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		scanner.close();
	}
}
