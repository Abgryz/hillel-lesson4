package ithillel.lesson17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroLombokTest {
    @Test
    public void test(){
        HeroLombok hero = new HeroLombok("A-Bomb", "Male", "yellow", "Human", "No Hair", 203, "Marvel Comics", "-", "good", 441);
        hero.setName("Not A-Bomb");
        assertEquals(hero.getName(), "Not A-Bomb");
        assertEquals(hero.toString(), "HeroLombok(name=Not A-Bomb, gender=Male, eyeColor=yellow, race=Human, hairColor=No Hair, height=203.0, publisher=Marvel Comics, skinColor=-, alignment=good, weight=441)");
    }

}