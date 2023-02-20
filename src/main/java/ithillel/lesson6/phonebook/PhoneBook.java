package ithillel.lesson6.phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhoneBook {
    private List<Record> records = new ArrayList<>();
    public PhoneBook(){}
    public PhoneBook(List<Record> records) {this.records = records;}

    public List<Record> getRecords() {return records;}
    public void setRecords(List<Record> records) {this.records = records;}



    public void add(Record item){
        records.add(item);
    }
    public void add(String fullName, String phoneNumber){
        records.add(new Record(fullName, phoneNumber));
    }

    public Record find(String name){
        for (Record item: records){
            if (Objects.equals(item.fullName(), name))
                return new Record(item.fullName(), item.phoneNumber());
        }
        return null;
    }
    public List<Record> findAll(String name){
        List<Record> list = new ArrayList<>();
        for (Record item: records){
            if (Objects.equals(item.fullName(), name))
                list.add(new Record(item.fullName(), item.phoneNumber()));
        }
        if (list.size() != 0)
            return list;
        else return null;
    }
}
