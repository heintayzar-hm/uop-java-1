package src.assi_4;
// Student.java
// The Student class should have private instance variables to store student information such as name, ID, and enrolled courses.
// Implement appropriate access modifiers and provide public getter and setter methods for accessing and updating student information.
// Design a method to enroll students in courses. It should accept a Course object as a parameter and add the course to the student's enrolled courses.
// Implement a method to assign grades to students. It should accept a Course object and a grade for the student and update the student's grade for that course.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private String studentID;
    private List<Course> enrolledCourses;
    private Map<Course, Integer> courseGrades;
    // Other necessary instance variables

    // Constructor
    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<Course>();
        this.courseGrades = new HashMap<Course, Integer>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    // end getters and setters

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        // Implement logic to enroll student in a course
        this.enrolledCourses.add(course);
    }

    public int getCourseGrade(Course course) {
        // Implement logic to get grade of student for a course
        return this.courseGrades.get(course);
    }

    public void assignGrade(Course course, int grade) {
        // Implement logic to assign grade to student for a course
        if (isCourseEnrolled(course)) {
            this.courseGrades.put(course, grade);
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }

    public int getOverallGrade() {
        // Implement logic to calculate overall grade for a student
        int totalGrade = 0;
        int numCourses = 0;
        for (Course course : this.enrolledCourses) {
            if (isCourseEnrolled(course)) {
                // add grade for course to total grade
                totalGrade += getCourseGrade(course);
                // increment number of courses
                numCourses++;
            }
        }

        // overall grade is the average of the grades for all courses
        int overallGrade = totalGrade / numCourses;
        return overallGrade;
    }

    public boolean isCourseEnrolled(Course course) {
        // Implement logic to check if student is enrolled in a course
        return this.enrolledCourses.contains(course);
    }
}


