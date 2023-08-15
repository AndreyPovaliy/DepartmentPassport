package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.inventory.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Inventory")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @Column(name = "inventory_number")
    String inventoryNumber;
    @Column(name = "start_exploitation")
    Date startExploitation;
    @Column(name = "life_time")
    Integer lifeTime;

    @ManyToOne
    @JsonBackReference(value="room_inventories")
    RoomProfile roomProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    InventoryStatus inventoryStatus;
}
