package src.assi_4;
// CourseManagement.java
// The CourseManagement class should have private static variables to store a list of courses and the overall course grades for each student.
// Use appropriate access modifiers to control access to the variables.
// Implement static methods to add new courses, enroll students, assign grades, and calculate overall course grades for each student.
// The addCourse method should accept parameters for course information and create a new Course object. It should add the course to the list of courses.
// The enrollStudent method should accept a Student object and a Course object. It should enroll the student in the course by calling the appropriate method in the Student class.
// The assignGrade method should accept a Student object, a Course object, and a grade. It should assign the grade to the student for that course by calling the appropriate method in the Student class.
// The calculateOverallGrade method should accept a Student object and calculate the overall course grade for that student based on the grades assigned to them.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static Map<Student, Integer> overallGrades = new HashMap<>();

        // get courses
    public static List<Course> getCourses() {
        return courses;
    }

    // get overall grades
    public static Map<Student, Integer> getOverallGrades() {
        return overallGrades;
    }


    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        // Implement logic to add a course
        Course course = new Course(courseCode, courseName, maxCapacity);
        courses.add(course);

    }

    public static void enrollStudent(Student student, Course course) {
        // Implement logic to enroll a student in a course
        student.enrollInCourse(course);
        // we will later implement many to many relationship here but now...
        // we will just add the student to the course
        course.addStudent(student);
    }

    public static void assignGrade(Student student, Course course, int grade) {
        // Implement logic to assign a grade to a student for a course
        student.assignGrade(course, grade);

        calculateOverallGrade(student);
    }

    public static void calculateOverallGrade(Student student) {
        // Implement logic to calculate overall grade for a student
        int totalGrade = 0;
        int numCourses = 0;
        for (Course course : student.getEnrolledCourses()) {
            if (student.isCourseEnrolled(course)) {
                // add grade for course to total grade
                totalGrade += student.getCourseGrade(course);
                // increment number of courses
                numCourses++;
            }
        }

        // overall grade is the average of the grades for all courses
        int overallGrade = totalGrade / numCourses;
        // add overall grade to map
        overallGrades.put(student, overallGrade);
    }

    public static void main(String[] args) {
        // Create a new course
        Course course = new Course("CSE110", "Programming with Java");
        // Create a new student
        Student student = new Student("John Doe", "123456789");
        // Enroll the student in the course
        student.enrollInCourse(course);
        // Assign a grade to the student for the course
        student.assignGrade(course, 95);
        // Calculate overall grade for the student
        calculateOverallGrade(student);
    }

    public static void printCourse(String courseCode) {
        // Implement logic to print a course
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                Logging.print("Course Code: " + course.getCourseCode() + "\nCourse Name: " + course.getCourseName()
                        + "\nMax Capacity: " + course.getMaxCapacity(), Logging.LogType.SUCCESS);
            }
        }
    }

    public static void printCourseWithStudents(String courseCode) {
        // Implement logic to print a course
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                Logging.print("Course Code: " + course.getCourseCode() + "\nCourse Name: " + course.getCourseName()
                        + "\nMax Capacity: " + course.getMaxCapacity(), Logging.LogType.SUCCESS);
                Logging.print("Students: ", Logging.LogType.SUCCESS);
                for (Student student : course.getEnrolledStudents()) {
                    Logging.print(student.getName() + " " + student.getStudentID(), Logging.LogType.SUCCESS);
                }
            }
        }
    }

    // with grades
    public static void printCourseWithStudentsAndGrades(String courseCode) {
        // Implement logic to print a course
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                Logging.print("Course Code: " + course.getCourseCode() + "\nCourse Name: " + course.getCourseName()
                        + "\nMax Capacity: " + course.getMaxCapacity(), Logging.LogType.SUCCESS);
                Logging.print("Students: ", Logging.LogType.SUCCESS);
                for (Student student : course.getEnrolledStudents()) {
                    Logging.print(student.getName() + " " + student.getStudentID() + " " + student.getCourseGrade(course), Logging.LogType.SUCCESS);
                }
            }
        }
    }

    public static void printAllCourses() {
        // Implement logic to print all courses
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // as list codes
    public static List<String> getCourseCodes() {
        // Implement logic to get a list of course codes
        List<String> courseCodes = new ArrayList<>();
        for (Course course : courses) {
            courseCodes.add(course.getCourseCode());
        }
        return courseCodes;
    }

    public static List<String> getCourseNames() {
        // Implement logic to get a list of course names
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getCourseName());
        }
        return courseNames;
    }
}
