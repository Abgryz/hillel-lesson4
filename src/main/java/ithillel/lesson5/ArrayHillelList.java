package ithillel.lesson5;

public class ArrayHillelList implements HillelList{

    private String[] ArrayList = {};

    public ArrayHillelList(){}
    public ArrayHillelList(String[] items){ArrayList = items.clone();}

    public String[] getAll() {
        return ArrayList;
    }

    public int size() {
        return ArrayList.length;
    }

    public void add(String item){
        String[] temp = new String[size() + 1];
        temp[size()] = item;
        System.arraycopy(ArrayList, 0, temp, 0, size());
        ArrayList = temp.clone();
    }

    public boolean contains(String item){
        for (String elem : ArrayList) {
            if (elem.equals(item)){
                return  true;
            }
        }
        return false;
    }

    public int indexOf(String item){
        for (int i = 0; i < size(); i++){
            if (item.equals(ArrayList[i])){
                return i;
            }
        }
        return -1;
    }

    public String get(int index) {
        return ArrayList[index];
    }

    public String remove(int index){
        String item = ArrayList[index];
        String[] temp = new String[size() - 1];
        System.arraycopy(ArrayList, 0, temp, 0, index);
        System.arraycopy(ArrayList, index + 1, temp, index, size() - index - 1);
        ArrayList = temp.clone();
        return item;
    }
}
