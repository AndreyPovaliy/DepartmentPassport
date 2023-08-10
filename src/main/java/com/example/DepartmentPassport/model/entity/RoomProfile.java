package com.example.DepartmentPassport.model.entity;

import com.example.DepartmentPassport.model.enums.building.BuildingStatus;
import com.example.DepartmentPassport.model.enums.room.RoomStatus;
import com.example.DepartmentPassport.model.enums.room.TypeRoom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    //    @ManyToOne
//    @JsonBackReference(value="driver_cars")
//    UserProfile userProfile;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Enumerated
    RoomStatus roomStatus;
}
