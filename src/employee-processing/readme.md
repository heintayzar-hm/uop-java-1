### Programming Assignment Unit 8

### How to run the program

#### 1. Clone the repository
```bash
git clone https://github.com/heintayzar-hm/uop-java-1.git
cd uop-java-1/src/employee-processing
```

#### 2. Compile the program
- make sure to install maven
```bash
mvn clean compile
java -jar target/employee-processing-1.0-SNAPSHOT.jar
```
Note: I am using ubuntu, I am not sure if it works on windows.


### Description of the program

#### 1. App class
- This class is the main class of the program.
- It will read the data using `EmployeeReader` class.
- It has all the requirements of the assignment.
  ##### 1.1. Function interface
- It has a method that utilizes `Function interface` to read the data from the file, that will get name and department of the employee.
    ##### 1.2. concatenated strings
- We have implemented that in main method of the class.
    ##### 1.3. average salary of all employees
- We made a method that will calculate the average salary of all employees, `getAverageSalary`. We used stream to calculate the average salary.
    ##### 1.4. filter function that only includes employees whose age is above a certain threshold
- Same as above, we used stream to filter the employees whose age is above a certain threshold.

#### 2. Employee class
- Model class for employee.
- It has all the attributes of the employee.
- It has a constructor that takes all the attributes as parameters.

#### 3. EmployeeReader class
- This class is used to read the data from the file.
- It has a method that will read the data from the file and return a list of employees.

#### Other classes

- We used [dataset](https://www.kaggle.com/datasets/tawfikelmetwally/employee-dataset) to run this project.
- The dataset has some missing values, so we have to handle it. We used `AddColumns` class for that.
- `AddColumns` class generates random values for missing values and write it into a new file.
- And we read that new file. You can find the new file in `src/employee-processing/data/NewEmployeeData.csv`.


#### Additional Tests for optimization
- We tried to optimize the stream in two ways: parallelStream and concurrent.
- Concurrent is not useful in the given cases but i tried it out as expected, it is longer than stream(sequential)
- As for parallel, the difference is not visible in small cases, but after i added more , the difference become more noticeable. It becomes almost half. But some operations does not change much and make even longer.

- There are other methods that I can think of like HashMap, but it will add more complexity for scenario like this.
