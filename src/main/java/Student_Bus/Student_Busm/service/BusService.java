package Student_Bus.Student_Busm.service;

import Student_Bus.Student_Busm.entity.Bus;
import Student_Bus.Student_Busm.entity.BusStatus;
import Student_Bus.Student_Busm.entity.Direction;
import Student_Bus.Student_Busm.repository.BusRepository;
import Student_Bus.Student_Busm.entity.Driver;
import Student_Bus.Student_Busm.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private DriverRepository driverRepository;

    // Method to add a new bus
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Method to retrieve all active buses
    public List<Bus> getActiveBuses() {
        return busRepository.findByBusStatus(BusStatus.ACTIVE);
    }
    public List<Bus> getINActiveBuses() {
        return busRepository.findByBusStatus(BusStatus.INACTIVE);
    }

    // Method to find a bus by its name (primary key)
    public Optional<Bus> getBusByName(String name) {
        return busRepository.findById(name);  // Find by 'name' as the primary key
    }

    // Method to update the bus status
    public Bus updateBusStatus(String name, BusStatus status) {
        Bus bus = busRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Bus not found with name: " + name));
        bus.setBusStatus(status);
        return busRepository.save(bus);
    }

    // Method to update the direction of a bus
    public Bus updateBusDirection(String name, String direction) {
        Bus bus = busRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Bus not found with name: " + name));
        bus.setDirection(Direction.valueOf(direction.toUpperCase()));
        return busRepository.save(bus);
    }

    // Method to assign a driver to a bus using the bus name
    public Driver assignDriverToBus(String busName, String driverId) {
        Bus bus = busRepository.findById(busName)
                .orElseThrow(() -> new RuntimeException("Bus not found with name: " + busName));
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));

        // Assign the driver to the bus
        driver.setBus(bus);

        // Set the bus status to ACTIVE
        bus.setBusStatus(BusStatus.ACTIVE);  // Set the bus status to ACTIVE when a driver is assigned

        // Save the updated driver and bus
        driverRepository.save(driver);
        return driverRepository.save(driver);  // Save the driver (this will automatically save the bus if the driver is assigned to the bus)
    }

    public List<Bus> getActiveBusesWithDrivers() {
        return busRepository.findByBusStatus(BusStatus.ACTIVE);
    }

    // Method to get buses for a specific admin
    public List<Bus> getBusesByAdmin(String adminId) {
        return busRepository.findByAdminAdminId(adminId);
    }
    /*public void setAllBusesInactive() {
        List<Bus> buses = busRepository.findAll(); // Get all buses from the database
        for (Bus bus : buses) {
            bus.setBusStatus("INACTIVE"); // Set the status of each bus to "Inactive"
        }
        busRepository.saveAll(buses); // Save the updated buses back to the database
    }*/
}
