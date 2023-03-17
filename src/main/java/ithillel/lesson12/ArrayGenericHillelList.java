package ithillel.lesson12;

public class ArrayGenericHillelList<T> implements GenericHillelList<T> {

    private T[] elements = (T[]) new Object[]{};

    public ArrayGenericHillelList(){}
    public ArrayGenericHillelList(T[] items){elements = items;}

    public Object[] getAll() {
        return elements;
    }

    public int size() {
        return elements.length;
    }

    public void add(T item){
        T[] temp = (T[]) new Object[size() + 1];
        temp[size()] = item;
        System.arraycopy(elements, 0, temp, 0, size());
        elements = temp;
    }

    public boolean contains(T item){
        return indexOf(item) != -1;
    }

    public int indexOf(T item){
        for (int i = 0; i < size(); i++){
            if (item.equals(elements[i])){
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        return elements[index];
    }

    public T remove(int index){
        T item = elements[index];
        T[] temp = (T[]) new Object[size() - 1];
        System.arraycopy(elements, 0, temp, 0, index);
        System.arraycopy(elements, index + 1, temp, index, size() - index - 1);
        elements = temp;
        return item;
    }
}
