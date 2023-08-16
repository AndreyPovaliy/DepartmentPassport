package com.example.DepartmentPassport.controllers;

import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(name="room")
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;


    @PostMapping
    @Operation(summary = "create Room")
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest) {

        return roomService.createRoom(roomRequest);
    }

    @GetMapping("/{id}")
    @Operation (summary = "get Room")
    public RoomResponse readRoom(@PathVariable Long id) {

        return roomService.getRoom(id);
    }

    @PutMapping("/{id}")
    @Operation (summary = "update Room")
    public RoomResponse updateRoom(@PathVariable Long id, @RequestBody RoomRequest roomRequest) {
        return roomService.updateRoom(id, roomRequest);
    }

    @DeleteMapping("/{id}")
    @Operation (summary = "delete Room")
    public void deleteRoom(@PathVariable Long id) {

        roomService.deleteRoom(id);
    }

    @GetMapping("/allRooms")
    @Operation (summary = "get all Room")
    public Page<RoomResponse> getAllRooms (@RequestParam (defaultValue = "1") Integer page,
                                                             @RequestParam (defaultValue = "10") Integer perPage,
                                                             @RequestParam (defaultValue = "name") String sort,
                                                             @RequestParam (defaultValue = "ASC") Sort.Direction order,
                                                             @RequestParam (required = false) String filter
    ) {

        return roomService.getAllRooms (page,perPage,sort,order,filter);
    }

    @PostMapping("/addRoomToDepartment/{roomId}/{departmentId}")
    @Operation (summary = "add room to department")
    public RoomResponse addRoomToDepartment (@PathVariable Long roomId, @PathVariable Long departmentId) {
        return roomService.addRoomToDepartment(roomId, departmentId);
    }
}
