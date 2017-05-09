package triplebyte;

/**
 * Created by andrew on 5/8/2017.
 */
public class ResizableArray {
    private int[] items = new int[8];//give it a start size
    private int size = 0;//starting size is 0

    public int getSize(){return size;}

    public void set(int index, int val){
        if(index<0||index>=size){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        items[index] = val;
    }
    public int get(int index, int val){
        if(index<0||index>=size){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return items[index];
    }
    public void append(int val){
        ensureCapacity();
        items[size] = val;
    }

    private void ensureCapacity() {
        if(size==items.length){
            int[] copy = new int[2*size];
            System.arraycopy(items,0,copy,0,size);
            items = copy;
        }
    }
}
