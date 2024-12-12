package Student_Bus.Student_Busm.service;

import Student_Bus.Student_Busm.entity.Driver;
import Student_Bus.Student_Busm.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import Student_Bus.Student_Busm.entity.Bus;
import Student_Bus.Student_Busm.entity.BusStatus;
import Student_Bus.Student_Busm.entity.Direction;
import Student_Bus.Student_Busm.repository.AdminRepository;
import Student_Bus.Student_Busm.repository.BusRepository;
import Student_Bus.Student_Busm.entity.Driver;
import Student_Bus.Student_Busm.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class Driverservice {

    @Autowired
    private final DriverRepository driverRepository;
    @Autowired
    private BusRepository busRepository;
    public Driver postdriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void assignDriverToBus(String driverId, String busName) {
        // Retrieve the Bus entity by name
        Bus bus = busRepository.findById(busName)
                .orElseThrow(() -> new RuntimeException("Bus not found with name: " + busName));

        // Retrieve the Driver entity by ID
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));

        // Set the bus for the driver
        driver.setBus(bus);

        // Save the updated driver
        driverRepository.save(driver);
    }

    public Driver findByDriverIdAndPassword(String driverId, String password) {
        Optional<Driver> optionalDriver = driverRepository.findByDriverIdAndPassword(driverId, password);

        if (optionalDriver.isPresent()) {
            System.out.println("Driver found: " + optionalDriver.get().getDriverId());
        } else {
            System.out.println("No driver found with given credentials");
        }

        return optionalDriver.orElse(null);  // Return null if no driver is found
    }
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}

