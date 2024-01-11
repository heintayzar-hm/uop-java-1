package com.example;
public class  Employee {
        private final String education;
        private final int joiningYear;
        private final String city;
        private final int paymentTier;
        private final int age;
        private final String gender;
        private final String everBenched;
        private final int experienceInCurrentDomain;
        private final String leaveOrNot;
        private final String department;
        private final double salary;
        private final String name;

        public Employee(String education, int joiningYear, String city, int paymentTier, int age, String gender,
                        String everBenched, int experienceInCurrentDomain, String leaveOrNot, String department,
                        String salary, String name) {
                this.education = education;
                this.joiningYear = joiningYear;
                this.city = city;
                this.paymentTier = paymentTier;
                this.age = age;
                this.gender = gender;
                this.everBenched = everBenched;
                this.experienceInCurrentDomain = experienceInCurrentDomain;
                this.leaveOrNot = leaveOrNot;
                this.department = department;
                this.salary = Double.parseDouble(salary);
                this.name = name;
        }

        public double getSalary() {
                return salary;
        }

        public String getName() {
                return name;
        }

        public String getDepartment() {
                return department;
        }

        public int getJoiningYear() {
                return joiningYear;
        }

        public String getEducation() {
                return education;
        }

        public int getAge() {
                return age;
        }

        @Override
        public String toString() {
                return "Employee{" +
                                "education='" + education + '\'' +
                                ", joiningYear=" + joiningYear +
                                ", city='" + city + '\'' +
                                ", paymentTier=" + paymentTier +
                                ", age=" + age +
                                ", gender='" + gender + '\'' +
                                ", everBenched='" + everBenched + '\'' +
                                ", experienceInCurrentDomain=" + experienceInCurrentDomain +
                                ", leaveOrNot='" + leaveOrNot + '\'' +
                                ", department='" + department + '\'' +
                                ", salary='" + salary + '\'' +
                                '}';
        }
}
