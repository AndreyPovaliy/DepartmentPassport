package com.example.DepartmentPassport.model.entity;


import com.example.DepartmentPassport.model.enums.room.RoomStatus;
import com.example.DepartmentPassport.model.enums.room.TypeRoom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Room")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "type_room")
    TypeRoom typeRoom;
    Integer area;

    @ManyToOne
    @JsonBackReference(value="department_room")
    DepartmentProfile departmentProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    RoomStatus roomStatus;

    @OneToMany
    @JsonManagedReference(value="room_inventories")
    List<InventoryProfile> inventoryProfiles;
}
