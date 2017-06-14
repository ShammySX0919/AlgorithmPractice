package amazon.medium;

import java.util.*;

/**
 * Created by andrew on 6/13/2017.
 */
public class LastNonRepeatObjectInAStream <T>{
        Deque<T> nonRepeating = new LinkedList<T>();
        Set<T> metSoFar = new HashSet<T>();

        public T getLastNonRepeatObject(){
            if(nonRepeating.isEmpty())return null;
            return nonRepeating.getLast();
        }

        public void reset(){
            nonRepeating.clear();
            metSoFar.clear();
        }

        public void stream(Collection<T> inStream){

            stream(inStream.stream());

        }

        public void stream(java.util.stream.Stream<T> inStream){

            inStream.forEach(e->{

                if(!metSoFar.add(e)){
                    nonRepeating.remove(e);//this is from collection, and might be expensive and worst at O(n) depending on implementation
                }
                else
                    nonRepeating.addLast(e);
            });
        }


        public static void main(String[] args) {

            LastNonRepeatObjectInAStream s = new LastNonRepeatObjectInAStream();

            String[] str = new String[]{"abc","bcd","cde","def","fee"};

            String[] str2 = {"abc","bcd","cde","def","fee","fee"};

            s.stream(Arrays.asList(str));

            System.out.println("test1:"+s.getLastNonRepeatObject());

            s.reset();

            s.stream(Arrays.asList(str2));

            System.out.println("test2:"+s.getLastNonRepeatObject());

        }

    }