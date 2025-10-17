package org.ccasro.level3;

public class Person {
    private String name;
    private String surname;
    private String dni;

    public Person(String name, String surname, String dni) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (surname == null || surname.trim().isEmpty()){
            throw new IllegalArgumentException("Surname cannot be empty");
        }
        if(!dni.matches("\\d{8}[A-Za-z]")){
            throw new IllegalArgumentException("DNI must have 8 digits followed by a letter");
        }

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
