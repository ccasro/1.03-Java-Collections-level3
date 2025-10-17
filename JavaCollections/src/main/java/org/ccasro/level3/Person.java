package org.ccasro.level3;

public class Person {
    private String name;
    private String surname;
    private String dni;

    public Person(String name, String surname, String dni) {
        this.name = name.trim();
        this.surname = surname.trim();
        this.dni = dni.trim();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-10s", name, surname, dni);
    }
}
