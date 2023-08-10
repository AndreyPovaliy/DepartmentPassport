package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Tag(name="room")
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;


    @PostMapping
//    @Operation (summary = "create Room")
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest) {

        return roomService.createRoom(roomRequest);
    }

    @GetMapping("/{id}")
//    @Operation (summary = "get Room")
    public RoomResponse readRoom(@PathVariable Long id) {

        return roomService.getRoom(id);
    }

    @PutMapping("/{id}")
//    @Operation (summary = "update Room")
    public RoomResponse updateRoom(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        return roomService.updateRoom(id, roomRequest);
    }

    @DeleteMapping
//    @Operation (summary = "delete Room")
    public void deleteRoom(@PathVariable Long id) {

        roomService.deleteRoom(id);
    }
}
