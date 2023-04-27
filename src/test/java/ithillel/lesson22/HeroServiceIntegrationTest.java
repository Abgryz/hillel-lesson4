package ithillel.lesson22;

import ithillel.lesson21.Hero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroServiceIntegrationTest {
    private final HeroService target = HeroFabric.createService(List.of(
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
                    .build()));
    private final List<HeroDto> expectedHeroDtoList = List.of(
            HeroDto.builder()
                    .name("Saul Goodman")
                    .movies(List.of("Saul Goodman Movie 1", "Saul Goodman Movie 2", "Saul Goodman Movie 3"))
                    .build(),
            HeroDto.builder()
                    .name("Walter White")
                    .movies(List.of("Walter White Movie 1", "Walter White Movie 2", "Walter White Movie 3"))
                    .build());
    @Test
    void getHeroes() {
        assertEquals(target.getHeroes(), expectedHeroDtoList);
    }
}