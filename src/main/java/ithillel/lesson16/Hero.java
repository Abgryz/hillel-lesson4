package ithillel.lesson16;

public record Hero(
        String name, // ім'я
        String gender, // стать
        String eyeColor, // колір очей
        String race, // раса
        String hairColor, // колір волосся
        double height, // зріст
        String publisher,  // видавець
        String skinColor, // колір шкіри
        String alignment, // добро / зло
        int weight // вага
) {
    public Hero(String[] heroAttributes){
        this(heroAttributes[1], heroAttributes[2], heroAttributes[3], heroAttributes[4], heroAttributes[5], Double.parseDouble(heroAttributes[6].replace(",", ".")), heroAttributes[7], heroAttributes[8],heroAttributes[9], Integer.parseInt(heroAttributes[10]));
    }
}
