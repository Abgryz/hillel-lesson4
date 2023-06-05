package ithillel.lesson22;

import ithillel.lesson21.Hero;
import ithillel.lesson21.HeroDao;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HeroService {
    private final HeroDao dao;
    public List<HeroDto> getHeroes(){
        return dao.findAll().stream()
                .map(HeroService::mapHero)
                .collect(Collectors.toList());
    }
    public List<HeroDto> getHeroesByName(String name){
        return dao.findByName(name).stream()
                .map(HeroService::mapHero)
                .collect(Collectors.toList());
    }

    public HeroDto getById(Long id){
        return dao.findAll().stream()
                .filter(hero -> hero.getId().equals(id))
                .findFirst()
                .map(HeroService::mapHero)
                .orElse(null);
    }

    public HeroDto create(Hero hero){
        dao.create(hero);
        return mapHero(hero);
    }

    public HeroDto create(HeroDto hero){
        dao.create(Hero.builder()
                .name(hero.getName())
                .build());
        return hero;
    }

    public HeroDto update(Hero hero){
        dao.update(hero);
        return mapHero(hero);
    }

    public void delete(Long id){
        dao.delete(id);
    }

    private static HeroDto mapHero(Hero hero){
        return HeroDto.builder()
                .name(hero.getName())
                .movies(HeroMovieService.getPlayedIn(hero.getName()))
                .build();
    }
}
