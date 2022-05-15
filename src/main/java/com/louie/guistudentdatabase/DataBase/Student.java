package com.louie.guistudentdatabase.DataBase;

import java.util.Scanner;

public class Student extends HumanData {
    private String course;
    private int id;
    private int yearLevel;
    private int[] grades = new int[4];

    Scanner scanner = new Scanner(System.in);

    public Student(String name, String email, int age) {
        super(name, age, email);
    }

    public Student() {
        super();
    }

    public void setStudentInfo(int id, int yearLevel, String course) {
        this.id = id;
        this.yearLevel = yearLevel;
        this.course = course;
    }

    public void setGrades(int english, int math, int history, int economics) {
        grades[0] = english;
        grades[1] = math;
        grades[2] = history;
        grades[3] = economics;
    }

    public int[] getGrades() {
        return this.grades;
    }

    public int getId() {
        return this.id;
    }

//    public void showGrades() {
//        System.out.println("\nShowing grades...");
//
//        for (int i = 0; i < this.grades.length; i++) {
//            System.out.print("Grade #" + (i+1) + ": " + this.grades[i] + "\n");
//        }
//    }

    public float getGradeAverage() {
        float average = 0;

        for (int grade : this.grades) {
            average += (float) grade;
        }
        average /= (float)this.grades.length;
        return average;
    }

//    public void showStudentData() {
//        System.out.println("Name: \t" + this.name);
//        System.out.println("Age: \t" + this.age);
//        System.out.println("ID: \t" + this.id);
//        System.out.println("Email: \t" + this.email);
//        System.out.println("Course: " + this.course);
//        System.out.println("Year Level: " + this.yearLevel);
//    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nID: " + this.id + "\n";
    }
}
