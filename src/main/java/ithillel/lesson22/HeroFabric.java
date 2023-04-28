package ithillel.lesson22;

import ithillel.lesson21.Hero;
import ithillel.lesson21.HeroDaoImpl;

import javax.sql.DataSource;
import java.util.List;

public class HeroFabric implements AbstractFabric{
    public static HeroService createService(DataSource dataSource){
        return new HeroService(new HeroDaoImpl(dataSource));
    }
    public static HeroService createService(List<Hero> heroList){
        return new HeroService(new DummyHeroDao(heroList));
    }
}
