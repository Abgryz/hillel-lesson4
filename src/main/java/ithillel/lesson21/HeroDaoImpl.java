package ithillel.lesson21;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class HeroDaoImpl implements HeroDao{
    private final DataSource dataSource;

    @Override
    public List<Hero> findAll() {
        String sql = "select * from hero";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(sql);
            return mapHero(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Hero> findByName(String name) {
        String sql = "select * from hero where name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            return mapHero(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Hero hero) {
        String sql = """
                    insert into hero (name, gender, eye_color, race, hair_color, height, publisher_id, skin_color, alignment, weight) values
                    (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, hero.getName());
            statement.setObject(2, hero.getGender());
            statement.setObject(3, hero.getEyeColor());
            statement.setObject(4, hero.getRace());
            statement.setObject(5, hero.getHairColor());
            statement.setObject(6, hero.getHeight());
            statement.setObject(7, hero.getPublisherId());
            statement.setObject(8, hero.getSkinColor());
            statement.setObject(9, hero.getAlignment());
            statement.setObject(10, hero.getWeight());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Hero hero) {
        String sql = """
                update hero
                set name=?, gender=?, eye_color=?, race=?, hair_color=?, height=?, publisher_id=?, skin_color=?, alignment=?, weight=?
                where id = ?""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, hero.getName());
            statement.setObject(2, hero.getGender());
            statement.setObject(3, hero.getEyeColor());
            statement.setObject(4, hero.getRace());
            statement.setObject(5, hero.getHairColor());
            statement.setObject(6, hero.getHeight());
            statement.setObject(7, hero.getPublisherId());
            statement.setObject(8, hero.getSkinColor());
            statement.setObject(9, hero.getAlignment());
            statement.setObject(10, hero.getWeight());
            statement.setLong(11, hero.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from hero where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @SneakyThrows
    private ArrayList<Hero> mapHero(ResultSet result){
        var heroes = new ArrayList<Hero>();
        while (result.next()) {
            heroes.add(Hero.builder()
                    .id(result.getLong("id"))
                    .name(result.getString("name"))
                    .gender(result.getString("gender"))
                    .alignment(result.getString("alignment"))
                    .race(result.getString("race"))
                    .eyeColor(result.getString("eye_color"))
                    .height(result.getInt("height"))
                    .hairColor(result.getString("hair_color"))
                    .publisherId(result.getLong("publisher_id"))
                    .skinColor(result.getString("skin_color"))
                    .weight(result.getInt("weight"))
                    .build());
        }
        return heroes;
    }
}