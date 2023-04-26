package ithillel.lesson21;

import org.postgresql.ds.PGSimpleDataSource;

public class Main {

    public static void main(String[] args) {
        var ds = connectTo("test", "postgres", "12345678", new String[] {"localhost"}, new int[] {5432});
        var heroDB = new HeroDaoImpl(ds);
        Hero hero = Hero.builder()
                .name("Saul Goodman")
                .hairColor("brown")
                .gender("male")
                .eyeColor("brown")
                .race("human")
                .alignment("good")
                .build();
        heroDB.create(hero);
        System.out.println("Created hero: " + heroDB.findByName("Saul Goodman").stream()
                .findFirst()
                .orElseThrow());

        hero = heroDB.findByName("Saul Goodman").stream()
                .findFirst()
                .orElseThrow();
        hero.setPublisherId(15L);
        hero.setAlignment("neutral");
        hero.setSkinColor("white");
        heroDB.update(hero);
        System.out.println("Updated hero: " + heroDB.findByName("Saul Goodman").stream().findFirst().orElseThrow());

        heroDB.delete(hero.getId());
        System.out.println("All heroes after delete: " + heroDB.findAll());
    }

    private static PGSimpleDataSource connectTo(String dataBaseName, String userName, String password, String[] serverNames, int[] portNumbers){
        var ds = new PGSimpleDataSource();
        ds.setDatabaseName(dataBaseName);
        ds.setUser(userName);
        ds.setPassword(password);
        ds.setServerNames(serverNames);
        ds.setPortNumbers(portNumbers);
        return ds;
    }
}
