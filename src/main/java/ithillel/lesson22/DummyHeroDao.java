package ithillel.lesson22;

import ithillel.lesson21.Hero;
import ithillel.lesson21.HeroDao;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DummyHeroDao implements HeroDao {
    private final List<Hero> db;

    @Override
    public List<Hero> findAll() {
        return db;
    }

    @Override
    public List<Hero> findByName(String name) {
        return db.stream()
                .filter((hero) -> hero.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void create(Hero hero) {
        db.add(hero);
    }

    @Override
    public void update(Hero hero) {
        Hero newHero = db.get(db.indexOf(hero));
        newHero = hero;
    }

    @Override
    public boolean delete(long id) {
        return db.remove((int) id) != null;
    }
}
