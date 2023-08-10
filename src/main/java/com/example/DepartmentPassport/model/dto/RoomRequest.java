package com.example.DepartmentPassport.model.dto;

import com.example.DepartmentPassport.model.enums.room.TypeRoom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomRequest {
    String name;
    TypeRoom typeRoom;
    Integer area;
}
