package assi_5;

// Interface for common vehicle features
interface Vehicle {
    String getMake();

    String getModel();

    int getYear();

    // we will add additional methods for type-specific features
    String getVehicleType();

}
