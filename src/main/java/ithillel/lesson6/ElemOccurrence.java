package ithillel.lesson6;

import java.util.Objects;

public class ElemOccurrence{
    private String name;
    private int occurrence;

    public ElemOccurrence(String name, int occurrence) {
        this.name = name;
        this.occurrence = occurrence;
    }

    public String getName() {
        return name;
    }
    public int getOccurrence() {
        return occurrence;
    }
    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public void incrementOccurrence() {
        occurrence++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElemOccurrence that = (ElemOccurrence) o;
        return occurrence == that.occurrence && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, occurrence);
    }

    @Override
    public String toString() {
        return "ElemOccurrence{" +
                "name='" + name + '\'' +
                ", occurrence=" + occurrence +
                '}';
    }
}
