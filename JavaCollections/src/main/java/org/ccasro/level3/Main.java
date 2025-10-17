package org.ccasro.level3;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "people.csv";
    private static List<Person> people = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        readCSV();

        int option;
        do {
            option = showMenu();

            switch(option){
                case 1 -> addPerson();
                case 2 -> showSorted(Comparator.comparing(Person::getName), false);
                case 3 -> showSorted(Comparator.comparing(Person::getName), true);
                case 4 -> showSorted(Comparator.comparing(Person::getSurname), false);
                case 5 -> showSorted(Comparator.comparing(Person::getSurname), true);
                case 6 -> showSorted(Comparator.comparing(Person::getDni), false);
                case 7 -> showSorted(Comparator.comparing(Person::getDni), true);
                case 0 -> {
                    saveCSV();
                    System.out.println(" Data saved. ");
                }
                default -> System.out.println("Invalid option. ");
            }
        } while (option != 0);
    }

    private static void addPerson() {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter surname: ");
        String surname = sc.nextLine();
        System.out.println("Enter DNI: ");
        String dni = sc.nextLine();

        try {
            people.add(new Person(name, surname, dni));
            System.out.println("Person added successfully");
        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showSorted(Comparator<Person> comparator, boolean reverse) {
        if (people.isEmpty()){
            System.out.println("Empty list.");
            return;
        }

        List<Person> sorted = new ArrayList<>(people);
        sorted.sort(reverse ? comparator.reversed() : comparator);

        System.out.printf("%-15s %-15s %-10s%n", "Name", "Surname", "DNI");
        for (Person p : sorted) {
            System.out.println(p);
        }
    }

    private static void readCSV(){
        File file = new File(FILE_NAME);
        if (!file.exists()){
            System.out.println("CSV file not found. Creating file...");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    people.add(new Person(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e){
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    private static void saveCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Person p : people) {
                pw.println(p.getName() + "," + p.getSurname() + "," + p.getDni());
            }
        } catch (IOException e) {
            System.out.println("Error saving CSV file: " + e.getMessage());
        }
    }

    private static int showMenu(){
        System.out.println("1. Add a person");
        System.out.println("2. Show people sorted by name (A-Z)");
        System.out.println("3. Show people sorted by name (Z-A)");
        System.out.println("4. Show people sorted by surname (A-Z)");
        System.out.println("5. Show people sorted by surname (Z-A)");
        System.out.println("6. Show people sorted by DNI (1-9)");
        System.out.println("7. Show people sorted by DNI (9-1)");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
        return readInt();
    }

    private static int readInt(){
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e){
            return -1;
        }
    }
}