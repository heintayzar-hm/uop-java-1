package src.assi_4;
// AdministratorInterface.java
// Develop an interactive command-line interface for administrators to interact with the Course Enrollment and Grade Management System.
// Display a menu with options to add a new course, enroll students, assign grades, and calculate overall course grades.
// Prompt the administrator for necessary inputs and call the appropriate methods in the CourseManagement and Student classes to perform the requested operations.
// Implement error handling to handle cases where invalid inputs are provided or when enrolling students in courses that have reached their maximum capacity.

import java.util.List;
import java.util.Scanner;

public class AdministratorInterface {
    public static void main(String[] args) {
        // Implement a command-line interface for administrator
        Scanner scanner = new Scanner(System.in);
        int choice;

        // make dummy four students
        List<Student> students = List.of(
                new Student("HEIN TAY ZAR", "123456789"),
                new Student("TAY ZAR", "987654321"),
                new Student("MAY ZAR", "123123123"),
                new Student("MOO MOO", "321321321")
        );

        do {
            // Display menu and get user choice
            System.out
                    .println("1. Add Course\n2. Enroll Student\n3. Assign Grade\n4. Calculate Overall Grade\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Call addCourse method
                    String courseCode = handleAddCourseCode(scanner);
                    String courseName = handleAddCourseName(scanner);
                    int maxCapacity = handleAddCourseMaxCapacity(scanner);

                    CourseManagement.addCourse(courseCode, courseName, maxCapacity);

                    Logging.println("\nCourse added successfully!\n", Logging.LogType.SUCCESS);
                    CourseManagement.printCourse(courseCode);
                    break;
                case 2:
                    // Call enrollStudent method
                    Logging.println("\nHere are the available students(please enter the id): \n");
                    // loop and show them what to select
                    Logging.println("id " + "name");
                    for (int i = 0; i < students.size(); i++) {
                        Logging.println(i + ". " + students.get(i).getName());
                    }

                    // get student index
                    int studentIndex = getStudentIndex(scanner);
                    Logging.println("\nHere are the available courses(please enter the id): \n");
                    // loop and show them what to select
                    Logging.println("id " + "course code");
                    for (int i = 0; i < CourseManagement.getCourses().size(); i++) {
                        Logging.println(i + ". " + CourseManagement.getCourses().get(i).getCourseCode());
                    }

                    // get course index (we should check if course is full)
                    int courseIndex;
                    do {
                        courseIndex = getCourseIndex(scanner);
                        if (CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                                .size() == CourseManagement.getCourses().get(courseIndex).getMaxCapacity()) {
                            Logging.print(
                                    "Course is full. Please select another course: ",
                                    Logging.LogType.ERROR);
                        }


                    } while (
                    CourseManagement.getCourses().get(courseIndex).getEnrolledStudents().size() == CourseManagement
                            .getCourses().get(courseIndex).getMaxCapacity()
                            );

                    CourseManagement.enrollStudent(students.get(studentIndex), CourseManagement.getCourses().get(courseIndex));


                    Logging.println("\nStudent enrolled successfully!\n", Logging.LogType.SUCCESS);

                    CourseManagement.printCourseWithStudents(CourseManagement.getCourses().get(courseIndex).getCourseCode());
                    break;
                case 3:
                    // Call assignGrade method
                    // it should go from course to student

                    Logging.println("\nHere are the available courses(please enter the id): \n");
                    Logging.println("id " + "course code");
                    // loop and show them what to select
                    for (int i = 0; i < CourseManagement.getCourses().size(); i++) {
                        Logging.println(i + ". " + CourseManagement.getCourses().get(i).getCourseCode());
                    }

                    // get course index
                    do {
                        courseIndex = getCourseIndex(scanner);
                        if (CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                                .size() == 0) {
                            Logging.print(
                                    "Course has no students. Please select another course: ",
                                    Logging.LogType.ERROR);
                        }
                    } while (CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                            .size() == 0);

                    Logging.println("\nHere are the available students(please enter the id): \n");

                    Logging.println("id " + "name");
                    // loop and show them what to select
                    for (int i = 0; i < CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                            .size(); i++) {
                        Logging.println(i + ". " + CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                                .get(i).getName());
                    }

                    // get student index
                    do {
                        studentIndex = getStudentIndex(scanner);
                        if (!CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                                .contains(students.get(studentIndex))) {
                            Logging.print(
                                    "Student is not enrolled in this course. Please select another student: ",
                                    Logging.LogType.ERROR);
                        }
                    } while (!CourseManagement.getCourses().get(courseIndex).getEnrolledStudents()
                            .contains(students.get(studentIndex)));

                    // get grade
                    int grade = getGrade(scanner);

                    CourseManagement.assignGrade(students.get(studentIndex), CourseManagement.getCourses().get(courseIndex), grade);


                    Logging.println("\nGrade assigned successfully!\n", Logging.LogType.SUCCESS);
                    CourseManagement.printCourseWithStudentsAndGrades(CourseManagement.getCourses().get(courseIndex).getCourseCode());
                    break;
                case 4:
                    // Call calculateOverallGrade method

                    Logging.println("\nHere are the available students(please enter the id): \n");

                    Logging.println("id " + "name");
                    // loop and show them what to select
                    for (int i = 0; i < students.size(); i++) {
                        Logging.println(i + ". " + students.get(i).getName());
                    }

                    // get student index
                    do {
                        studentIndex = getStudentIndex(scanner);
                        if (students.get(studentIndex).getEnrolledCourses().size() == 0) {
                            Logging.print("Student is not enrolled in any course. Please select another student.",
                                    Logging.LogType.ERROR);
                        }
                    } while (students.get(studentIndex).getEnrolledCourses().size() == 0);


                    CourseManagement.calculateOverallGrade(students.get(studentIndex));

                    Logging.println("\nOverall grade calculated successfully!\n", Logging.LogType.SUCCESS);
                    Logging.println("Overall grade: " + students.get(studentIndex).getOverallGrade(), Logging.LogType.SUCCESS);


                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    private static String handleAddCourseCode(Scanner scanner) {
        Validator validator;
        String courseCode;

        scanner.nextLine();
        do {
            Logging.print("Enter Course Code: ", Logging.LogType.QUESTION);
            courseCode = scanner.nextLine();
            validator = Validator.forValue(courseCode)
                    .isNotEmpty("Course code cannot be empty")
                    .hasLengthBetween(5, 10, "Course code must be between 5 and 10 characters")
                    .unique("Course code must be unique", CourseManagement.getCourseCodes());
            if (!validator.isValid()) {
                Logging.print("Invalid input. " + validator.getErrorMessages().get(0),
                        Logging.LogType.ERROR);
            }
        } while (!validator.isValid());
        return validator.getValue();
    }


    private static String handleAddCourseName(Scanner scanner) {
        String courseName;
        Validator validator;
        scanner.nextLine();

        do {
            Logging.print("Enter Course Name: ", Logging.LogType.QUESTION);
            courseName = scanner.nextLine();
            validator = Validator.forValue(courseName)
                    .isNotEmpty("Course name cannot be empty")
                    .hasLengthBetween(5, 20, "Course name must be between 5 and 20 characters");
            if (!validator.isValid()) {
                Logging.print("Invalid input. " + validator.getErrorMessages().get(0),
                        Logging.LogType.ERROR);
            }
        } while (!validator.isValid());
        return courseName;
    }

    private static int handleAddCourseMaxCapacity(Scanner scanner) {
        String maxCapacity;
        Validator validator;
        scanner.nextLine();

        do {
            Logging.print("Enter Max capacity: ", Logging.LogType.QUESTION);
            maxCapacity = scanner.nextLine();
            validator = Validator.forValue(maxCapacity)
                    .isNumeric("Max capacity must be a number")
                    .isBetween(2, 100, "Max capacity must be between 2 and 100");
            if (!validator.isValid()) {
                Logging.print("Invalid input. " + validator.getErrorMessages().get(0),
                        Logging.LogType.ERROR);
            }
        } while (!validator.isValid());
        return Integer.parseInt(maxCapacity);
    }

    private static int getStudentIndex(Scanner scanner) {
        int studentIndex;
        Validator validator;

        do {
            Logging.print("Select student: ", Logging.LogType.QUESTION);
            studentIndex = scanner.nextInt();
            validator = Validator.forValue(studentIndex)
                    .isNumeric("Student index must be a number")
                    // to fix later
                    .isBetween(0, 3, "Student index must be between 0 and 3");
            if (!validator.isValid()) {
                Logging.print("Invalid input. " + validator.getErrorMessages().get(0),
                        Logging.LogType.ERROR);
            }
        } while (!validator.isValid());
        return studentIndex;
    }

    private static int getCourseIndex(Scanner scanner) {
        int courseIndex;
        Validator validator;

        do {
            Logging.print("Select course: ", Logging.LogType.QUESTION);
            courseIndex = scanner.nextInt();
            validator = Validator.forValue(courseIndex)
                    .isNumeric("Course index must be a number")
                    // to fix later
                    .isBetween(0, 3, "Course index must be between 0 and 3");
            if (!validator.isValid()) {
                Logging.print("Invalid input. " + validator.getErrorMessages().get(0),
                        Logging.LogType.ERROR);
            }
        } while (!validator.isValid());
        return courseIndex;
    }

    private static int getGrade(Scanner scanner) {
        int grade;
        Validator validator;

        do {
            Logging.print("Enter grade: ", Logging.LogType.QUESTION);
            grade = scanner.nextInt();
            validator = Validator.forValue(grade)
                    .isNumeric("Grade must be a number")
                    .isBetween(0, 100, "Grade must be between 0 and 100");
            if (!validator.isValid()) {
                Logging.print("Invalid input. " + validator.getErrorMessages().get(0),
                        Logging.LogType.ERROR);
            }
        } while (!validator.isValid());
        return grade;
    }
}
