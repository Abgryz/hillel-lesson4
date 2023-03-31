package ithillel.lesson16;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HeroList {
    private final List<Hero> heroList;

    public double avgHeight(){
        return heroList.stream()
                .mapToDouble(Hero::height)
                .filter(height -> height > 0)
                .average()
                .orElse(0);
    }

    public String nameOfHighest(){
        return heroList.stream()
                .max(Comparator.comparingDouble(Hero::height))
                .orElseThrow()
                .name();
    }

    public String nameOfHeaviest(){
        return heroList.stream()
                .max(Comparator.comparingInt(Hero::weight))
                .orElseThrow()
                .name();
    }

    public Map<String, Integer> genderCounter(){
        return heroList.stream()
                .collect(Collectors.toMap(
                        Hero::gender,
                        (hero) -> 1,
                        Integer::sum
                ));
    }

    public Map<String, Integer> alignmentCounter(){
        return heroList.stream()
                .collect(Collectors.toMap(
                        Hero::alignment,
                        (hero) -> 1,
                        Integer::sum
                ));
    }

    public List<String> theMostPopularPublishers(){
        return heroList.stream()
                .collect(Collectors.toMap(
                        Hero::publisher,
                        (hero) -> 1,
                        Integer::sum
                ))
                .entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
    }

    public List<String> theMostPopularHairColors(){
        return heroList.stream()
                .collect(Collectors.toMap(
                        Hero::hairColor,
                        (hero) -> 1,
                        Integer::sum
                ))
                .entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .filter((entryHero) -> !Objects.equals(entryHero.getKey(), "-"))
                    .limit(3)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
    }

    public String theMostPopularEyeColor(){
        return heroList.stream()
                .collect(Collectors.toMap(
                        Hero::eyeColor,
                        (hero) -> 1,
                        Integer::sum
                ))
                .entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .filter((entryHero) -> !Objects.equals(entryHero.getKey(), "-"))
                    .map(Map.Entry::getKey)
                    .findFirst()
                        .orElseThrow();
    }

    public HeroList(String pathToFile){
        heroList= new ArrayList<>();
        try (FileReader reader = new FileReader(pathToFile)) {
            Scanner scanner = new Scanner(reader);
            scanner.nextLine();
            while (scanner.hasNext()){
                String[] hero = scanner.nextLine().split(";");
                heroList.add(new Hero(hero));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HeroList(List<Hero> heroList){
        this.heroList = heroList;
    }

    public Hero get(int index){
        return heroList.get(index);
    }
    public void add(Hero hero){
        heroList.add(hero);
    }
    public Hero remove(int index){
        return heroList.remove(index);
    }

    public static void main(String[] args) {
        final String PATH_TO_RESOURCE = "src/main/resources/heroes_information.csv";
        final HeroList heroList = new HeroList(PATH_TO_RESOURCE);

        System.out.println("Average height: "+heroList.avgHeight());
        System.out.println("Highest: "+heroList.nameOfHighest());
        System.out.println("Heaviest: "+heroList.nameOfHeaviest());
        System.out.println("Gender counts: "+heroList.genderCounter());
        System.out.println("Alignments count: "+heroList.alignmentCounter());
        System.out.println("The most popular publishers: "+heroList.theMostPopularPublishers());
        System.out.println("The most popular hair colors: "+heroList.theMostPopularHairColors());
        System.out.println("The most popular eye color: "+heroList.theMostPopularEyeColor());
    }
}


