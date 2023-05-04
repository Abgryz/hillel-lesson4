package ithillel.lesson22;

import lombok.Data;

import java.util.List;

@Data
public class HeroDto {
    private String name;
    private List<String> movies;

    public static HeroDtoBuilder builder(){
        return new HeroDtoBuilder();
    }

    public static class HeroDtoBuilder{
        private final HeroDto heroDto = new HeroDto();
        public HeroDtoBuilder name(String name){
            heroDto.setName(name);
            return this;
        }
        public HeroDtoBuilder movies(List<String> movies){
            heroDto.setMovies(movies);
            return this;
        }
        public HeroDto build(){
            return heroDto;
        }
    }
}
