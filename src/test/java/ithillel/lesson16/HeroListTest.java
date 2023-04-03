package ithillel.lesson16;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeroListTest {
    private final HeroList heroList = new HeroList(List.of(
            new Hero("A-Bomb","Male","yellow","Human","No Hair",203,"Marvel Comics","-","good",441),
            new Hero("Abe Sapien","Male","blue","Icthyo Sapien","No Hair",191,"Dark Horse Comics","blue","good",65),
            new Hero("Abin Sur","Male","blue","Ungaran","No Hair",185,"DC Comics","red","good",90),
            new Hero("Abomination","Male","green","Human / Radiation","No Hair",203,"Marvel Comics","-","bad",441),
            new Hero("Abraxas","Male","blue","Cosmic Entity","Black",-99,"Marvel Comics","-","bad",-99)
    ));

    @Test
    void avgHeight() {
        assertEquals(heroList.avgHeight(), 195.5);
    }

    @Test
    void nameOfHighest() {
        assertEquals(heroList.nameOfHighest(), "A-Bomb");
    }

    @Test
    void genderCounter() {
        assertEquals(heroList.genderCounter(), Map.of("Male", 5));
    }

    @Test
    void nameOfHeaviest() {
        assertEquals(heroList.nameOfHeaviest(), "A-Bomb");
    }

    @Test
    void alignmentCounter() {
        assertEquals(heroList.alignmentCounter(), Map.of("bad", 2, "good", 3));
    }

    @Test
    void theMostPopularPublishers() {
        assertEquals(heroList.theMostPopularPublishers(), List.of("Marvel Comics", "Dark Horse Comics", "DC Comics"));
    }

    @Test
    void theMostPopularHairColors() {
        assertEquals(heroList.theMostPopularHairColors(), List.of("No Hair", "Black"));
    }

    @Test
    void theMostPopularEyeColor() {
        assertEquals(heroList.theMostPopularEyeColor(), "blue");
    }
}