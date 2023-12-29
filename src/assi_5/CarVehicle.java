package assi_5;

// Interface for car-specific features
interface CarVehicle extends Vehicle {
    void setNumDoors(int numDoors);

    int getNumDoors();

    void setFuelType(String fuelType); // Petrol, Diesel, Electric

    String getFuelType();
}
