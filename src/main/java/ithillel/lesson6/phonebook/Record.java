package ithillel.lesson6.phonebook;

import java.util.Objects;

public record Record(String fullName, String phoneNumber) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return fullName.equals(record.fullName) && phoneNumber.equals(record.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, phoneNumber);
    }
}
