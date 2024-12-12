package Student_Bus.Student_Busm.controller;

import Student_Bus.Student_Busm.entity.Driver;
import Student_Bus.Student_Busm.service.Driverservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;




@RestController
@RequestMapping("/api/drivers")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
public class DriverController {

    private final Driverservice driverService;

    public DriverController(Driverservice driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/register")
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.postdriver(driver);
    }

    @GetMapping("/")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }
    @PostMapping("/{driverId}/assign-to-bus/{busName}")
    public ResponseEntity<String> assignDriverToBus(@PathVariable String driverId, @PathVariable String busName) {
        driverService.assignDriverToBus(driverId, busName);
        return ResponseEntity.ok("Driver assigned to bus successfully!");
    }
//    @GetMapping("/api/assignments")
//    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
//        List<AssignmentDTO> assignments = driverService.getAllAssignments();
//        return ResponseEntity.ok(assignments);
//    }

    @PostMapping("/login")
    public Driver login(@RequestBody Driver loginData) {
        Driver driver = driverService.findByDriverIdAndPassword(loginData.getDriverId(), loginData.getPassword());
        if (driver != null) {
            return driver;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }
//@GetMapping("/login")
//public Driver login(@RequestParam String driverId, @RequestParam String password) {
//    Driver driver = driverService.findByDriverIdAndPassword(driverId, password);
//    if (driver != null) {
//        return driver;
//    } else {
//        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//    }
}

