package amazon.oa.y2017;

import java.util.*;

/**
 * public class Movie
 {
 int movieId;.
 float rating;
 List<Movie> similarMovies
 other getters

 }
 find movie's k most similar movies
 public static List<Movie> getNearest(Movie movie, int numSimilar)
 * Created by andrew on 5/16/2017.
 */
class Movie {
    private int id;
    private float rating;
    private List<Movie> similarMovies;

    public Movie(int id, float rate, List<Movie> neighbors) {
        this.id = id;
        this.rating = rate;
        this.similarMovies = neighbors;
    }

    public float getRating(){
        return rating;
    }

    public int getId(){
        return id;
    }
    public List<Movie> getSimilarMovies(){
        return similarMovies;
    }
}

public class MovieNetwork {
    //find movie from movie network,  can be hashmap
    Movie findMovie(int id){
        //todo: provide code
        return null;
    }

    Set<Integer> getMovieRecommendations( Movie movie, int k){
        Set<Movie> visited = new HashSet<>();
        Queue<Movie> q = new LinkedList<>();
        PriorityQueue<Movie> pq = new PriorityQueue<Movie>((o1, o2) -> (int)(o2.getRating()-o1.getRating()));
        q.add(movie);
        while(!q.isEmpty()){
            Movie m = q.poll();
            //processed, continue
            if(!visited.add(m))continue;
            //otherwise, all similar movies adding to pq. do not add movie itself
            if(m!=movie) {
                pq.add(m);
            }
            for(Movie e:m.getSimilarMovies()){
                q.add(e);//adding neighbors
            }
        }
        Set<Integer> res = new LinkedHashSet<>();
        for(int i=0;i<k;i++){
            if(!pq.isEmpty())
                res.add(pq.poll().getId());
            else
                break;//no more element to retrieve from pq
        }
        return res;
    }
}
