package com.example.DepartmentPassport.service.impl;

import com.example.DepartmentPassport.model.dto.RoomRequest;
import com.example.DepartmentPassport.model.dto.RoomResponse;
import com.example.DepartmentPassport.model.entity.RoomProfile;
import com.example.DepartmentPassport.model.enums.room.RoomStatus;
import com.example.DepartmentPassport.model.repositories.RoomProfileRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.DepartmentPassport.model.enums.room.TypeRoom.MEDICAL;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {
    @InjectMocks
    private RoomServiceImpl roomService;
    @Mock
    private RoomProfileRepo roomProfileRepo;
    @Spy
    private ObjectMapper mapper;

    @Test
    public void createRoom() {
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setTypeRoom(MEDICAL);

        RoomProfile roomProfile = new RoomProfile();
        roomProfile.setId(1L);

        when(roomProfileRepo.save(any(RoomProfile.class))).thenReturn(roomProfile);

        RoomResponse result = roomService.createRoom(roomRequest);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
    }

    @Test
    public void getRoom() {
        RoomProfile roomProfile = new RoomProfile();
        roomProfile.setId(1L);

        when(roomProfileRepo.findById(1L)).thenReturn(Optional.of(roomProfile));

        RoomResponse result = roomService.getRoom(1L);
        assertEquals(Optional.of(1L), Optional.of(result.getId()));
        assertEquals(roomProfile.getId(), result.getId());
    }

    @Test
    public void updateRoom() {
        RoomProfile roomProfile = new RoomProfile();
        roomProfile.setId(1L);

        when (roomProfileRepo.findById(1L)).thenReturn(Optional.of(roomProfile));

        RoomRequest roomRequest = new RoomRequest();
        roomService.updateRoom(1L, roomRequest);
        verify(roomProfileRepo, times(1)).save(any(RoomProfile.class));
        assertEquals(RoomStatus.UPDATED, roomProfile.getRoomStatus());
    }

    @Test
    public void deleteRoom() {
        RoomProfile roomProfile = new RoomProfile();
        roomProfile.setId(1L);

        when (roomProfileRepo.findById(1L)).thenReturn(Optional.of(roomProfile));

        roomService.deleteRoom(1L);
        verify(roomProfileRepo, times(1)).save(any(RoomProfile.class));
        assertEquals(RoomStatus.DELETED, roomProfile.getRoomStatus());
    }
}