package ithillel.lesson5;

public class ArrayHillelList implements HillelList{

    private String[] elements = {};

    public ArrayHillelList(){}
    public ArrayHillelList(String[] items){elements = items;}

    public String[] getAll() {
        return elements;
    }

    public int size() {
        return elements.length;
    }

    public void add(String item){
        String[] temp = new String[size() + 1];
        temp[size()] = item;
        System.arraycopy(elements, 0, temp, 0, size());
        elements = temp;
    }

    public boolean contains(String item){
        return indexOf(item) != -1;
    }

    public int indexOf(String item){
        for (int i = 0; i < size(); i++){
            if (item.equals(elements[i])){
                return i;
            }
        }
        return -1;
    }

    public String get(int index) {
        return elements[index];
    }

    public String remove(int index){
        String item = elements[index];
        String[] temp = new String[size() - 1];
        System.arraycopy(elements, 0, temp, 0, index);
        System.arraycopy(elements, index + 1, temp, index, size() - index - 1);
        elements = temp;
        return item;
    }
}
