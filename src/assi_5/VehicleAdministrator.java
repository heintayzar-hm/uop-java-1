package assi_5;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VehicleAdministrator {

    private List<Vehicle> vehicles;
    // relative path to the file
    // use absolute path if you want to save the file in a specific location
    private static final String JSON_FILE_PATH = "vehicles.json";

    public VehicleAdministrator(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        loadFromFile();
    }

    public VehicleAdministrator() {
        this.vehicles = new ArrayList<>();
        loadFromFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_FILE_PATH))) {
            writer.write("["); // Start of the array

            for (int i = 0; i < vehicles.size(); i++) {
                writer.write("\n"); // New line before each vehicle
                writer.write(
                        SimpleJsonConverter.toJson(vehicles.get(i)));
                if (i < vehicles.size() - 1) {
                    writer.write(",");
                }
            }

            writer.write("\n]"); // End of the array

            Logging.println("Data saved to " + JSON_FILE_PATH, Logging.LogType.SUCCESS);
        } catch (IOException e) {
            Logging.println("Error saving data to JSON file.", Logging.LogType.ERROR);
        }
    }

    private void loadFromFile() {
        // if the file does not exist, skip loading
        if (!Files.exists(Paths.get(JSON_FILE_PATH))) {
            Logging.println("File " + JSON_FILE_PATH + " does not exist. Skipping loading data from file.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line represents a JSON-like object
                // We have to find the type of the object and then convert it to the respective
                // object
                // NOTE: This is not a good way to do this. We should use a JSON library like
                // GSON or Jackson but
                // we will not use them for this assignment.
                Vehicle vehicle = null;
                if (line.contains("vehicleType")) {
                    Map<String, Object> keyValueMap = SimpleJsonConverter.fromJson(line);
                    String vehicleType = (String) keyValueMap.get("vehicleType");

                    switch (vehicleType) {
                        case "Car":
                            vehicle = new Car(
                                    (String) keyValueMap.get("make"),
                                    (String) keyValueMap.get("model"),
                                    Integer.parseInt((String) keyValueMap.get("year")));
                            ((Car) vehicle).setNumDoors(Integer.parseInt((String) keyValueMap.get("numDoors")));
                            ((Car) vehicle).setFuelType((String) keyValueMap.get("fuelType"));
                            break;

                        case "Motorcycle":
                            vehicle = new Motorcycle(
                                    (String) keyValueMap.get("make"),
                                    (String) keyValueMap.get("model"),
                                    Integer.parseInt((String) keyValueMap.get("year")));
                            ((Motorcycle) vehicle)
                                    .setNumWheels(Integer.parseInt((String) keyValueMap.get("numWheels")));
                            ((Motorcycle) vehicle).setMotorcycleType((String) keyValueMap.get("motorcycleType"));
                            break;

                        case "Truck":
                            vehicle = new Truck(
                                    (String) keyValueMap.get("make"),
                                    (String) keyValueMap.get("model"),
                                    Integer.parseInt((String) keyValueMap.get("year")));
                            ((Truck) vehicle)
                                    .setCargoCapacity(Double.parseDouble((String) keyValueMap.get("cargoCapacity")));
                            ((Truck) vehicle).setTransmissionType((String) keyValueMap.get("transmissionType"));
                            break;

                        default:
                            Logging.println("Unknown vehicle type: " + vehicleType, Logging.LogType.ERROR);
                            break;
                    }
                }

                if (vehicle != null) {
                    Logging.println("Loaded vehicle: " + vehicle.getMake() + " " + vehicle.getModel(),
                            Logging.LogType.SUCCESS);
                    vehicles.add(vehicle);
                }
            }
            Logging.println("Data loaded from " + JSON_FILE_PATH, Logging.LogType.SUCCESS);
        } catch (IOException e) {
            Logging.println("Error loading data from JSON file.", Logging.LogType.ERROR);
        }
    }

    public void administerVehicles() {
        Logging.println("Welcome to the Vehicle Administration System!", Logging.LogType.QUESTION);

        while (true) {
            Logging.println("\nChoose an option:");
            Logging.println("1. Add a new vehicle");
            Logging.println("2. Display all vehicles");
            Logging.println("3. Back\n");

            int choice = UserInputManager.getIntInput("Enter your choice:", 1, 3);

            switch (choice) {
                case 1:
                    addNewVehicle();
                    saveToFile();
                    break;
                case 2:
                    displayAllVehicles();
                    break;
                case 3:
                    Logging.println("Exiting the Vehicle Administration System. Goodbye!", Logging.LogType.SUCCESS);
                    return;
                default:
                    Logging.println("Invalid choice. Please enter a valid option.", Logging.LogType.ERROR);
            }
        }
    }

    private void addNewVehicle() {
        Logging.println("\nChoose the type of vehicle to add:");
        Logging.println("1. Car");
        Logging.println("2. Motorcycle");
        Logging.println("3. Truck\n");

        int choice = UserInputManager.getIntInput("Enter your choice:", 1, 3);

        switch (choice) {
            case 1:
                addCar();
                break;
            case 2:
                addMotorcycle();
                break;
            case 3:
                addTruck();
                break;
            default:
                Logging.println("Invalid choice. Please enter a valid option.", Logging.LogType.ERROR);
        }
    }

    private void addCar() {
        String make = UserInputManager.getStringInput("Enter the make of the car(max: 50):", 50);
        String model = UserInputManager.getStringInput("Enter the model of the car(max: 50):", 50);
        int year = UserInputManager.getIntInput("Enter the year of manufacture(1900-2025):", 1900, 2025);
        int doors = UserInputManager.getIntInput("Enter the number of doors(between 1 to 5):", 1, 5);
        String fuelType = UserInputManager.getStringInput("Enter the fuel type(Petrol, Diesel, Electric):", 10,
                new String[] { "Petrol", "Diesel", "Electric" });

        Car car = new Car(make, model, year);
        car.setNumDoors(doors);
        car.setFuelType(fuelType);

        vehicles.add(car);

        Logging.println("Car added successfully!", Logging.LogType.SUCCESS);
    }

    private void addMotorcycle() {
        String make = UserInputManager.getStringInput("Enter the make of the motorcycle(max: 50):", 50);
        String model = UserInputManager.getStringInput("Enter the model of the motorcycle(max: 50):", 50);
        int year = UserInputManager.getIntInput("Enter the year of manufacture(1900-2025):", 1900, 2025);
        int wheels = UserInputManager.getIntInput("Enter the number of wheels(between 1 to 3):", 1, 3);
        String type = UserInputManager.getStringInput("Enter the motorcycle type(Sports, Cruiser, Dirt):", 10,
                new String[] { "Sports", "Cruiser", "Dirt" });
        Motorcycle motorcycle = new Motorcycle(make, model, year);
        motorcycle.setNumWheels(wheels);
        motorcycle.setMotorcycleType(type);

        vehicles.add(motorcycle);

        Logging.println("Motorcycle added successfully!", Logging.LogType.SUCCESS);
    }

    private void addTruck() {
        String make = UserInputManager.getStringInput("Enter the make of the truck(max: 50):", 50);
        String model = UserInputManager.getStringInput("Enter the model of the truck(max: 50):", 50);
        int year = UserInputManager.getIntInput("Enter the year of manufacture(1900-2025):", 1900, 2025);
        double cargoCapacity = UserInputManager.getDoubleInput("Enter the cargo capacity(in tons):", 1);
        String transmissionType = UserInputManager.getStringInput("Enter the transmission type(Automatic, Manual):",
                10, new String[] { "Automatic", "Manual" });

        Truck truck = new Truck(make, model, year);
        truck.setCargoCapacity(cargoCapacity);
        truck.setTransmissionType(transmissionType);

        vehicles.add(truck);

        Logging.println("Truck added successfully!", Logging.LogType.SUCCESS);
    }

    private void displayAllVehicles() {
        Logging.println("\nAll Vehicles:");
        for (Vehicle vehicle : vehicles) {
            displayVehicleDetails(vehicle);
        }
    }

    private void displayVehicleDetails(Vehicle vehicle) {
        Logging.println("\nVehicle Details:");
        Logging.println("+-------------------------+");
        Logging.println("| Make: " + padRight(vehicle.getMake(), 20) + " |");
        Logging.println("| Model: " + padRight(vehicle.getModel(), 19) + " |");
        Logging.println("| Year: " + padRight(Integer.toString(vehicle.getYear()), 19) + " |");

        if (vehicle instanceof CarVehicle) {
            CarVehicle car = (CarVehicle) vehicle;
            Logging.println("| Number of Doors: " + padRight(Integer.toString(car.getNumDoors()), 14) + " |");
            Logging.println("| Fuel Type: " + padRight(car.getFuelType(), 18) + " |");
        } else if (vehicle instanceof MotorVehicle) {
            MotorVehicle motorcycle = (MotorVehicle) vehicle;
            Logging.println("| Number of Wheels: " + padRight(Integer.toString(motorcycle.getNumWheels()), 14) + " |");
            Logging.println("| Motorcycle Type: " + padRight(motorcycle.getMotorcycleType(), 15) + " |");
        } else if (vehicle instanceof TruckVehicle) {
            TruckVehicle truck = (TruckVehicle) vehicle;
            Logging.println(
                    "| Cargo Capacity: " + padRight(Double.toString(truck.getCargoCapacity()) + " tons", 12) + " |");
            Logging.println("| Transmission Type: " + padRight(truck.getTransmissionType(), 15) + " |");
        }

        Logging.println("| Vehicle Type: " + padRight(getVehicleType(vehicle), 17) + " |");
        Logging.println("+-------------------------+");
    }

    private String getVehicleType(Vehicle vehicle) {
        if (vehicle instanceof CarVehicle) {
            return "Car";
        } else if (vehicle instanceof MotorVehicle) {
            return "Motorcycle";
        } else if (vehicle instanceof TruckVehicle) {
            return "Truck";
        } else {
            return "Unknown";
        }
    }

    private String padRight(String s, int length) {
        return String.format("%-" + length + "s", s);
    }

}
