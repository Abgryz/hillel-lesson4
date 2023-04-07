package ithillel.lesson17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroBuilderTest {
    @Test
    public void test(){
        HeroBuilder hero1 = HeroBuilder.builder()
                .name("A-Bomb")
                .gender("Male")
                .build();
        assertEquals(hero1, new HeroBuilder("A-Bomb", "Male", null, null, null, 0, null, null, null, 0));

        HeroBuilder hero2 = hero1.toBuilder()
                .height(200)
                .weight(120)
                .build();
        assertEquals(hero2, new HeroBuilder("A-Bomb", "Male", null, null, null, 200, null, null, null, 120));
    }
}