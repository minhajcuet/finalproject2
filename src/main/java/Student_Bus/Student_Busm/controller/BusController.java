package Student_Bus.Student_Busm.controller;

import Student_Bus.Student_Busm.entity.BusStatus;
import Student_Bus.Student_Busm.entity.Driver;
import Student_Bus.Student_Busm.entity.Bus;
import Student_Bus.Student_Busm.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
//@CrossOrigin(origins = "http://localhost:3000")  // Allow CORS for this controller
@CrossOrigin("*")
public class BusController {

    @Autowired
    private BusService busService;

    // Endpoint to add a new bus
    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    // Endpoint to get all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getActiveBuses();
    }

    // Endpoint to get only active buses
    @GetMapping("/active")
    public List<Bus> getActiveBuses() {
        return busService.getActiveBuses();
    }

    @GetMapping("/inactive")
    public List<Bus> getINActiveBuses() {
        return busService.getINActiveBuses();
    }


    // Endpoint to get a bus by name (primary key)
    @GetMapping("/{name}")
    public Bus getBusByName(@PathVariable String name) {
        return busService.getBusByName(name)
                .orElseThrow(() -> new RuntimeException("Bus not found with name: " + name));
    }

    // Endpoint to update bus status
    @PutMapping("/{name}/status")
    public Bus updateBusStatus(@PathVariable String name, @RequestParam BusStatus status) {
        return busService.updateBusStatus(name, status);
    }

    // Endpoint to update bus direction
    @PutMapping("/{name}/direction")
    public Bus updateBusDirection(@PathVariable String name, @RequestParam String direction) {
        return busService.updateBusDirection(name, direction);
    }

    // Endpoint to assign a driver to a bus using the bus name
    @PutMapping("/{busName}/assign-driver/{driverId}")
    public Driver assignDriverToBus(@PathVariable String busName, @PathVariable String driverId) {
        return busService.assignDriverToBus(busName, driverId);
    }

    // Endpoint to fetch active buses with their drivers
    @GetMapping("/active-with-drivers")
    public List<Bus> getActiveBusesWithDrivers() {
        return busService.getActiveBusesWithDrivers();
    }
   /* @PostMapping("/make-inactive")
    public ResponseEntity<Void> makeAllBusesInactive() {
        busService.setAllBusesInactive();  // Call the service method to update the buses
        return ResponseEntity.ok().build(); // Return a successful response
    }*/
}
