### Programming Assignment Unit 5

- run the main dir Administrator.java file in your respective ide.

- We have 6 main package classes: AdministratorInterface, Course, CourseManagement, Logging, Student,and Validator.

As the name suggest,
- AdministratorInterface is the main file that you have to run and its responsibilities  is getting user input and do the recommended actions, for example, adding course, enrolling course, etc...

- Course is the Course model for the project, its main responsibilities is getting its related data, name, total students, ...

- CourseManagement is the handler between Course and Student Class. It will do all responsibilities , including enrolling students, adding course,... The main difference between this class and AdministratorInterface is that AdministratorInterface responsibilities is simply Calling method from CourseManagement and the main logic still be in CourseManagement.

- Student is the Student model for the project, it has just getters and setters and some methods that should be used in Student model

- Logging is the logging helper, Instead of using System.out.println, we will be using Logging helper, In that way we can do many things before sending user every output. In this way we can simply update our user output format without having to change every one.

- Validator, the only class that is still writing, is that I want to continue logic from other languages packages ideas, I think it is from php, where we can simply use validation and showing errors with simply making a method chain, liek this:
.isEmail().isString().isBetween(10,100).

Although it is usable, it cannot cover the whole customization to make a package yet, one of the limits being, it has to use .forValue(value) at the start method chain, and it cannot used as object yet meaning we have to use only static calls.

- Overall, We will start at AdministratorInterface and asking input from user and use Validator and logging library to validate and output. After that it will follow respective class from CourseManagement. CourseManagement will then connect COurse and Student and store all students in a course data.



