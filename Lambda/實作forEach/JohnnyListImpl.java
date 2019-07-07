import java.util.Arrays;

public class JohnnyListImpl<E> implements JohnnyList<E> {
    private Object[] dataList;
    private int capacity;
    private int index;


    public JohnnyListImpl(int init){
        if(init > 0 && init < 1000) {
            capacity = init;
            dataList = new Object[capacity];
        }else {
            throw new RuntimeException("初始容量錯誤");
        }

    }

    public JohnnyListImpl() {
        capacity = 16;
        dataList = new Object[capacity];
    }

    @Override
    public void add(E e) {
        if(index < capacity){
            dataList[index] = e;
            ++index;
        }else {
            dataList = Arrays.copyOf(dataList, capacity + 16);
            dataList[index] = e;
            ++index;
        }
    }

    @Override
    public E get(int index) {
        if(index <= this.index) {
            return (E) dataList[index];
        }else {
            throw new RuntimeException("index 超出範圍");
        }
    }

    @Override
    public int size() {
        return index;
    }
}
