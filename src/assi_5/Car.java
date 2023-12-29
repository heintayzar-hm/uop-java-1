package assi_5;

// Car class implementing CarVehicle interface
class Car implements CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;
    private String vehicleType = "Car";

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public String getVehicleType() {
        return vehicleType;
    }
}
