package ithillel.lesson6;


import java.util.*;

public class ListMethod {
    public static int countOccurrence(List<String> list, String str){
        int count = 0;
        for (String elem: list){
            if (elem == str)
                count++;
        }
        return count;
    }

    public static List<Integer> toList(int[] arr){
        List<Integer> list = new ArrayList<>();
        for (int elem: arr){
            list.add(elem);
        }
        return list;
    }

    public static List<Integer> findUnique(List<Integer> list){
        List<Integer> uniqueList = new ArrayList<>();
        for (int elem: list){
            if(!uniqueList.contains(elem)){
                uniqueList.add(elem);
            }
        }
        return uniqueList;
    }

    public static void calcOccurrence(List<String> list) {
        System.out.println(findOccurrence(list).toString());
    }

    public static List<ElemOccurrence> findOccurrence(List<String> list){
        List<ElemOccurrence> structList = new ArrayList<>();
        Set<String> wordSet = new HashSet<>();
        for (String str: list){
            if (!wordSet.contains(str)){
                wordSet.add(str);
                structList.add(new ElemOccurrence(str, 1));
            } else
            for (ElemOccurrence elem: structList){
                if (Objects.equals(elem.getName(), str)){
                    elem.incrementOccurrence();
                    break;
                }
            }
        }
        return structList;
    }
}