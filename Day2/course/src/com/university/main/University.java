package com.university.main;

import java.util.List;
import java.util.Scanner;

import com.university.dao.CourseDao;
import com.university.dao.CourseDaoImpl;
import com.university.model.Course;

public class University {
    private static CourseDao courseDao = new CourseDaoImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to Gaming University Course Registration System");
            System.out.println("1. Gamer");
            System.out.println("2. Admin");
            System.out.println("3. Quit");
            System.out.print("Enter your Games: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    administratorMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Game. Please try again.");
            }
        }
    }

    private static void studentMenu() {
    	boolean studentExit = false;
        while (!studentExit) {
            System.out.println("Student Menu");
            System.out.println("1. View Available GameingCourse");
            System.out.println("2. Select a GameingCourse");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewAllCourses();
                    break;
                case 2:
                    selectCourse();
                    break;
                case 3:
                    studentExit = true;
                    break;
                default:
                    System.out.println("Invalid Game. Please try again.");
            }
        }
    }

    private static void administratorMenu() {
        boolean adminExit = false;
        while (!adminExit) {
            System.out.println("Administrator Menu");
            System.out.println("1. Add GamingCourse");
            System.out.println("2. View All GamingCourses");
            System.out.println("3. Update GamingCourse");
            System.out.println("4. Delete GamingCourse");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your Gamingchoice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    updateCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 5:
                    adminExit = true;
                    break;
                default:
                    System.out.println("Invalid Game. Please try again.");
            }
        }
    }
    
    private static void selectCourse() {
        System.out.println("Enter the GameId of the Gamingcourse you want to select:");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Course selectedCourse = courseDao.getCourseById(courseId);
        if (selectedCourse == null) {
            System.out.println("Invalid courseId. Course not found.");
        } else {
            // Implement the logic to enroll the student in the selected course (if applicable).
            System.out.println("You have successfully selected the following course:");
            System.out.println(selectedCourse);
        }
    }

    private static void addCourse() {
        // Implementation to add a new course
    	 System.out.println("Enter courseId:");
         int courseId = scanner.nextInt();
         scanner.nextLine(); // Consume the newline character

         System.out.println("Enter courseName:");
         String courseName = scanner.nextLine();

         System.out.println("Enter PlayerCapacity:");
         int capacity = scanner.nextInt();
         scanner.nextLine(); // Consume the newline character

         System.out.println("Enter prerequisites:");
         String prerequisites = scanner.nextLine();

         Course newCourse = new Course(courseId, courseName, capacity, prerequisites);
         courseDao.addCourse(newCourse);

         System.out.println("GamingCourse added successfully!");
    }

    private static void viewAllCourses() {
        // Implementation to view all courses
    	 List<Course> courses = courseDao.getAllCourses();
         if (courses.isEmpty()) {
             System.out.println("There is Such A Game Available.");
         } else {
             System.out.println("All GamingCourses:");
             for (Course course : courses) {
                 System.out.println(course);
             }
         }
     }


    private static void updateCourse() {
        System.out.println("Enter GamingId to update:");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Course existingCourse = findCourseById(courseId);
        if (existingCourse != null) {
            System.out.println("Enter new game name (or press Enter to skip):");
            String newCourseName = scanner.nextLine().trim();
            if (!newCourseName.isEmpty()) {
                existingCourse.setCourseName(newCourseName);
            }

            System.out.println("Enter new course playercapacity (or -1 to skip):");
            int newCapacity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (newCapacity != -1) {
                existingCourse.setCapacity(newCapacity);
            }

            System.out.println("Enter new prerequisites (or press Enter to skip):");
            String newPrerequisites = scanner.nextLine().trim();
            if (!newPrerequisites.isEmpty()) {
                existingCourse.setPrerequisites(newPrerequisites);
            }

            courseDao.updateCourse(existingCourse);
            System.out.println("GameCourse updated successfully!");
        } else {
            System.out.println("GameCourse not found with courseId: " + courseId);
        }
    }

    private static void deleteCourse() {
        System.out.println("Enter gamecourseId to delete:");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Course existingCourse = findCourseById(courseId);
        if (existingCourse != null) {
            courseDao.deleteCourse(courseId);
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Course not found with gamecourseId: " + courseId);
        }
    }

    private static Course findCourseById(int courseId) {
        List<Course> courses = courseDao.getAllCourses();
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }
}