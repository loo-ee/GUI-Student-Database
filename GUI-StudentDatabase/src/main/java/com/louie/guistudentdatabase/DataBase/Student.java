package com.louie.guistudentdatabase.DataBase;

import java.util.Scanner;

public class Student extends HumanData {
    private String course;
    private int id;
    private int yearLevel;
    private int[] grades;

    Scanner scanner = new Scanner(System.in);

    public Student(String name, String email, int id, int age) {
        super(name, age, email);
        this.id = id;
    }

    public Student() {
        super();
    }

    public void setStudentInfo() {
        System.out.print("\nEnter your course: ");
        this.course = scanner.nextLine();
        System.out.print("Enter your year level: ");
        this.yearLevel = scanner.nextInt();
        scanner.nextLine();
    }

    public void inputGrades(int howManyGrades) {
        grades = new int[howManyGrades];

        for (int i = 0; i<howManyGrades; i++) {
            System.out.print("Enter grade #" + (i+1) + ": ");
            grades[i] = scanner.nextInt();
        }
        scanner.nextLine();
    }

    public int[] getGrades() {
        return this.grades;
    }

    public int getId() {
        return this.id;
    }

    public void showGrades() {
        System.out.println("\nShowing grades...");

        for (int i = 0; i < this.grades.length; i++) {
            System.out.print("Grade #" + (i+1) + ": " + this.grades[i] + "\n");
        }
    }

    public float getGradeAverage() {
        float average = 0;

        for (int grade : this.grades) {
            average += (float) grade;
        }
        average /= (float)this.grades.length;
        return average;
    }

    public void showStudentData() {
        System.out.println("Name: \t" + this.name);
        System.out.println("Age: \t" + this.age);
        System.out.println("ID: \t" + this.id);
        System.out.println("Email: \t" + this.email);
        System.out.println("Course: " + this.course);
        System.out.println("Year Level: " + this.yearLevel);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nID: " + this.id + "\n";
    }
}
