package com.example.DepartmentPassport.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryRequest {
    String name;
    String inventoryNumber;
    Date startExploitation;
    Integer lifeTime;

//	- Помещение
}
