package ithillel.lesson22;

import ithillel.lesson21.Hero;
import ithillel.lesson21.HeroDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroServiceTest {

    @Test
    void getHeroes() {
        HeroDao heroDaoMock = Mockito.mock(HeroDao.class);
        List<Hero> expectedHeroList = List.of(
                Hero.builder()
                        .name("Saul Goodman")
                        .gender("male")
                        .race("human")
                        .alignment("neutral")
                        .build(),
                Hero.builder()
                        .name("Walter White")
                        .gender("male")
                        .race("human")
                        .alignment("neutral")
                        .build());
        Mockito.when(heroDaoMock.findAll()).thenReturn(expectedHeroList);

        HeroService heroService = new HeroService(heroDaoMock);
        List<HeroDto> expectedHeroDtoList = List.of(
                HeroDto.builder()
                        .name("Saul Goodman")
                        .movies(List.of("Saul Goodman Movie 1", "Saul Goodman Movie 2", "Saul Goodman Movie 3"))
                        .build(),
                HeroDto.builder()
                        .name("Walter White")
                        .movies(List.of("Walter White Movie 1", "Walter White Movie 2", "Walter White Movie 3"))
                        .build());
        assertEquals(heroService.getHeroes(), expectedHeroDtoList);
    }
}