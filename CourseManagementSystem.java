import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int registered;

    Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registered = 0;
    }

    boolean isAvailable() {
        return registered < capacity;
    }

    void registerStudent() {
        if (isAvailable()) {
            registered++;
        } else {
            System.out.println("Course is full.");
        }
    }

    void removeStudent() {
        if (registered > 0) {
            registered--;
        } else {
            System.out.println("No students to remove from this course.");
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description + "\nAvailable Slots: " + (capacity - registered);
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses = new ArrayList<>();

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    void registerForCourse(Course course) {
        if (course.isAvailable()) {
            course.registerStudent();
            registeredCourses.add(course);
            System.out.println(name + " successfully registered for " + course.title);
        } else {
            System.out.println("Course is full, unable to register.");
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.removeStudent();
            registeredCourses.remove(course);
            System.out.println(name + " dropped " + course.title);
        } else {
            System.out.println(name + " is not registered for this course.");
        }
    }
}

public class CourseManagementSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Creating some sample courses
        courses.add(new Course("CS1221", "Intro to Programming", "Learn the basics of programming.", 30));
        courses.add(new Course("MATH1221", "Calculus I", "Introduction to Calculus.", 25));
        courses.add(new Course("WP1221", "INTRO TO WEB DEVELOPMENT", " Build websites.", 30));
        courses.add(new Course("DSA1221", "ADVANCED ALGORITHMS", "DATA STRUCTURES.", 25));
        courses.add(new Course("GRH1221", "GRAPHIC DESIGNING", "PLAY WITH 3Ds.", 25));


        // Register a student
        Student student = new Student("S123", "Alice");
        students.add(student);

        // Main loop
        while (true) {
            System.out.println("\nAvailable Actions:");
            System.out.println("1. View Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View available courses
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        System.out.println(course);
                        System.out.println("----------------------------");
                    }
                    break;

                case 2:
                    // Register for a course
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    Course selectedCourse = null;
                    for (Course course : courses) {
                        if (course.courseCode.equals(courseCode)) {
                            selectedCourse = course;
                            break;
                        }
                    }
                    if (selectedCourse != null) {
                        student.registerForCourse(selectedCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter course code to drop: ");
                    courseCode = scanner.nextLine();
                    selectedCourse = null;
                    for (Course course : courses) {
                        if (course.courseCode.equals(courseCode)) {
                            selectedCourse = course;
                            break;
                        }
                    }
                    if (selectedCourse != null) {
                        student.dropCourse(selectedCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}




