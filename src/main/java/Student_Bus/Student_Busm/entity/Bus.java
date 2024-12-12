package Student_Bus.Student_Busm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buses")
@Entity
public class Bus {

    @Id
    @Column(nullable = false, unique = true)  // Make 'name' the primary key
    private String name;  // Unique bus name (Primary Key)

    @Column(nullable = false)
    private int totalSeats = 50;  // Total seats in the bus

    @Column(nullable = false)
    private int occupiedSeats = 0;  // Occupied seats in the bus

    @Column(nullable = false)
    private String bustype;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusStatus busStatus = BusStatus.ACTIVE;  // Status of the bus

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction = Direction.FROM_CUET;  // Direction of the bus

    // Many-to-One relationship with Admin (Many buses can be managed by one admin)
    @Setter
    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "adminId")  // Foreign key to Admin
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driverId") // Foreign key to driver
    private Driver driver;
}
