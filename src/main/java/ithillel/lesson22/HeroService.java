package ithillel.lesson22;

import ithillel.lesson21.HeroDao;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HeroService {
    private final HeroDao dao;
    public List<HeroDto> getHeroes(){
        return dao.findAll().stream()
                .map((heroDao) -> HeroDto.builder()
                        .name(heroDao.getName())
                        .movies(HeroMovieService.getPlayedIn(heroDao.getName()))
                        .build())
                .collect(Collectors.toList());
    }
}
