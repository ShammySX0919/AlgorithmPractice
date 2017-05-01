package amazon.easy;

/**
 * Created by 212595974 on 4/27/2017.
 */
public class OverlapRectangle {
    static class Point{
        int x,y;
        Point(int x, int y){
            this.x = x;
            this.y=y;
        }
    }
    public static boolean isOverlap(Point lt1, Point rb1,Point lt2, Point rb2){
        if(lt1.x>rb2.x || lt2.x>rb1.x)return false;//if no overlap vertically
        if(rb1.y>lt2.y || rb2.y>lt1.y)return false;//if no overlap horizontally
        return true;
    }

    public static void main(String args[]){
        Point l1 = new Point(0, 10), r1 = new Point(10, 0);
        Point l2 = new Point(5, 5), r2 = new Point(15, 0);
        Point l3 = new Point(0, -55), r3 = new Point(15, 0);
        System.out.println(isOverlap(l1,r1,l2,r2));
        System.out.println(isOverlap(l1,r1,l3,r3));
    }
}
