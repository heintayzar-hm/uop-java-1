### Programming Assignment Unit 5

- run the main dir Administrator.java file in your respective ide.

- We have 6 main package classes: AdministratorInterface, Course, CourseManagement, Logging, Student, and Validator.

As the name suggests,
- AdministratorInterface is the main file that you have to run and its responsibilities are getting user input and doing the recommended actions, for example, adding courses, enrolling courses, etc...

- Course is the Course model for the project, its main responsibility is getting its related data, names, and total students, ...

- CourseManagement is the handler between Course and Student Class. It will do all responsibilities, including enrolling students, and adding courses,... The main difference between this class and AdministratorInterface is that AdministratorInterface responsibilities are simply Calling methods from CourseManagement and the main logic still be in CourseManagement.

- Student is the Student model for the project, it has just getters and setters and some methods that should be used in the Student model
Note: student is being defaulted four students from AdministratorInterface. The current project cannot add students yet.
- Logging is the logging helper, Instead of using System.out.println, we will be using a Logging helper, In that way, we can do many things before sending the user every output. In this way, we can update our user output format without having to change every output.

- Validator, the only class that is still writing, is that I want to continue logic from other languages' packages ideas, I think it is from PHP, where we can simply use validation and show errors by simply making a method chain, like this:
.isEmail().isString().isBetween(10,100).

Although it is usable, it cannot cover the whole customization to make a package yet, one of the limits being, that it has to use .forValue(value) at the start method chain, and it cannot used as an object yet meaning we have to use only static calls.

- Overall, We will start at AdministratorInterface ask input from the user, and use Validator and logging library to validate and output. After that, it will follow the respective class from CourseManagement. CourseManagement will then connect COurse and Student and store all students in course data.
