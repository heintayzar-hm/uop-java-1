package assi_5;

// Interface for motorcycle-specific features
interface MotorVehicle extends Vehicle {
    void setNumWheels(int numWheels);

    int getNumWheels();

    void setMotorcycleType(String motorcycleType);

    String getMotorcycleType();
}
