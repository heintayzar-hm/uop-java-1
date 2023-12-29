package assi_5;

// Interface for truck-specific features
interface TruckVehicle extends Vehicle {
    void setCargoCapacity(double cargoCapacity);

    double getCargoCapacity();

    void setTransmissionType(String transmissionType);

    String getTransmissionType();
}
