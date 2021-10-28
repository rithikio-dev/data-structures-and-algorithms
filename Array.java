@SuppressWarnings("unchecked")
public class Array<T> implements AbstractTypes<T>, Iterable<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private transient T[] array;
    private int capacity;
    private int size;

    public Array() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public Array(T[] initial) {
        this.array = initial;
        this.size = initial.length;
        this.capacity = this.size + this.size / 2;
    }

    public Array(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Array inital size cannot be " + capacity);
        this.size = 0;
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (size == 0)
            throw new IllegalAccessError("Stack is empty");
        return array[size - 1];
    }

    public T peekAt(int index) {
        return array[index];
    }

    public T push(T element) {
        if (null == element)
            throw new IllegalArgumentException("Element cannot be null");

        if (capacity <= size) {
            capacity += (capacity == 1) ? 1 : (capacity / 2);
            Object[] newArray = new Object[capacity];
            for (int i = 0; i < size; i++)
                newArray[i] = this.array[i];
            this.array = (T[]) newArray;
        }

        this.array[size++] = element;
        return element;
    }

    public T pop() {
        if (this.isEmpty())
            throw new IllegalAccessError("Stack is empty");
        T popped = this.array[size - 1];
        this.array[size-- - 1] = null;
        return popped;
    }

    public void reverse() {
        this.array = AbstractTypes.super.reverse(this.array, this.capacity);
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("[");
        for (int i = 0; i < size; i++)
            toString.append(this.array[i] + ", ");
        return toString.substring(0, toString.length() - 2) + "]";
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return peekAt(current) != null;
            }

            @Override
            public T next() {
                return array[current++];
            }

        };
    }

}