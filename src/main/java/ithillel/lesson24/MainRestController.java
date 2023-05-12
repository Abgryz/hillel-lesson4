package ithillel.lesson24;

import ithillel.lesson21.Hero;
import ithillel.lesson22.HeroDto;
import ithillel.lesson22.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainRestController {
    private final HeroService heroService;

    @GetMapping("/heroes")
    public List<HeroDto> getAll(){
        return heroService.getHeroes();
    }

    @GetMapping("/heroes/{id}")
    public HeroDto getHero(@PathVariable Long id){
        return heroService.getById(id);
    }

    @PostMapping("/heroes")
    public HeroDto postHero(@RequestBody Hero hero){
        return heroService.create(hero);
    }

    @PutMapping("/heroes")
    public HeroDto putHero(@RequestBody Hero hero){
        return heroService.update(hero);
    }

    @DeleteMapping("/heroes/{id}")
    public void deleteHero(@PathVariable Long id){
        heroService.delete(id);
    }
}
//@RequestParam("name") String name