package ithillel.lesson6.phonebook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {
    public final static List<Record> INPUT_RECORDS = List.of(
            new Record("Падун Жито Романович", "0974629426"),
            new Record("Гучок Кирило Тимурович", "0639463854"),
            new Record("Марунчак Петро Устимович", "0997748377"),
            new Record("Бурачинський Фауст Радимович", "0664739577"),
            new Record("Падун Жито Романович", "0948498742"),
            new Record("Бурачинський Фауст Радимович", "0509792692"),
            new Record("Петрушенко Твердислав Тимурович", "0637329922"),
            new Record("Падун Жито Романович", "0630728963")

    );
    public final static List<Record> OUTPUT_RECORDS = List.of(
            new Record("Падун Жито Романович", "0974629426"),
            new Record("Падун Жито Романович", "0948498742"),
            new Record("Падун Жито Романович", "0630728963")
    );
    public final static List<Record> ADD_NEW_RECORD = List.of(new Record("Петрушенко Твердислав Тимурович", "0637329922"));

    @Test
    void addTest() {
        PhoneBook book = new PhoneBook();
        book.add("Петрушенко Твердислав Тимурович", "0637329922");
        assertEquals(book.getRecords(), ADD_NEW_RECORD);
    }

    @Test
    void findTest() {
        PhoneBook book = new PhoneBook(INPUT_RECORDS);
        assertEquals(book.find("Падун Жито Романович"), new Record("Падун Жито Романович", "0974629426"));
        assertNull(book.find("noname"));
    }

    @Test
    void findAllTest() {
        PhoneBook book = new PhoneBook(INPUT_RECORDS);
        assertEquals(book.findAll("Падун Жито Романович"), OUTPUT_RECORDS);
        assertNull(book.findAll("noname"));
    }
}