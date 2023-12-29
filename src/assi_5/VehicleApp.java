package assi_5;

// Main program
public class VehicleApp {
    public static void main(String[] args) {
        // start
        VehicleApp vehicleApp = new VehicleApp();
        vehicleApp.start();
    }

    public void start() {
        Logging.println("Welcome to the Vehicle Administration System!", Logging.LogType.QUESTION);

        while (true) {
            Logging.println("Choose an option:");
            Logging.println("1. Administer Vehicles");
            Logging.println("2. Exit");

            int choice = UserInputManager.getIntInput("Enter your choice:");

            switch (choice) {
                case 1:
                    // make vehicle app admin
                    VehicleAdministrator vehicleAdministrator = new VehicleAdministrator();
                    // start vehicle app
                    vehicleAdministrator.administerVehicles();
                    break;
                case 2:
                    Logging.println("Exiting the Vehicle Administration System. Goodbye!", Logging.LogType.SUCCESS);
                    return;
                default:
                    Logging.println("Invalid choice. Please enter a valid option.", Logging.LogType.ERROR);
            }
        }
    }
}
