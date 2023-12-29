### Programming Assignment Unit 6

- run the main dir VehicleApp.java in respective ide.

- As we can see we have like this:

- Vehicle (an interface for a vehicle, this will be the main interface for all other classes CarVehicle, MotorVehicle, TruckVehicle)
- We also have respective Interfaces and Classes that extend from the Vehicle Interface: (Car(Interface), CarVehicle), (Motorcycle(Interface), MotorVehicle), (Truck(Interface), TruckVehicle)

The main Program, Vehicle App, is responsive for managing sube programs like administration the vehicle.
- We have the Main logic for this project inside VehicleAdministrator, A sub-app for administration. It has available options like creating and displaying vehicles.
- In this project, we additionally created some helpers, we have a Validator which is supposed to be used together with UserInputManager for validating inputs from users. So every input required from VehicleAdministrator is supposed to be called from UserInputManager, which is later validated with methods from Validator.

- We also have Logging, which I will use for every project for printing outputs to users.

- In the VehicleAdministrator, we additionally have storing and loading data for further persistence data. I usually use JSON format so I use a .json file, which we can edit in JSON_FILE_PATH inside VehicleAdministrator. Actually, we are supposed to use some additional external libraries for parsing the JSON data. but we want to do it ourselves, so we challenge ourselves with SimpleJsonConverter.
Note: SimpleJsonConverter is not perfect, we can only use it with certain rules: only possible for arrays and JSON data must be written line by line.

- This week, compared to last week, we updated our logic in Validation, and getting input is changed, we lose our customization in exchange for simple syntax. We also created new usage for Converting JSON objects and files, although it is kinda limited, it is usable.

Overall, We have classes and their respective interfaces, (Car(Interface), CarVehicle), (Motorcycle(Interface), MotorVehicle), (Truck(Interface), TruckVehicle), and Vehicle App which will manage the available systems such as Administration. The administration system will then show options for adding and creating, which consumed UserInputManager for getting inputs. UserInputManager is actually using a Validator file for validation. After creating new vehicles we will save inside a JSON file which will later load at the start.

