package ithillel.lesson17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroValueTest {
    @Test
    public void test(){
        HeroValue hero = new HeroValue("A-Bomb", "Male", "yellow", "Human", "No Hair", 203, "Marvel Comics", "-", "good", 441);
        assertEquals(hero.getName(), "A-Bomb");
    }
}