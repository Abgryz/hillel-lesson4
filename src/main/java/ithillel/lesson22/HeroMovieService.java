package ithillel.lesson22;

import java.util.List;

public class HeroMovieService {
    public static List<String> getPlayedIn(String heroName){
        return List.of(heroName + " Movie 1", heroName +  " Movie 2", heroName +  " Movie 3");
    }
}
