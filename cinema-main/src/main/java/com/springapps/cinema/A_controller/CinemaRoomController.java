package com.springapps.cinema.A_controller;

import com.springapps.cinema.B_service.CinemaRoomService;
import com.springapps.cinema.D_model.CinemaRoom;
import com.springapps.cinema.E_DTO.CinemaRoomRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemaroom")
public class CinemaRoomController {
    private CinemaRoomService cinemaRoomService;
    @Autowired
    public CinemaRoomController(CinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }


    @PostMapping
    public ResponseEntity<CinemaRoom> addCinemaROom(@RequestBody  CinemaRoomRequestDTO cinemaRoomRequestDTO){
        return ResponseEntity.ok(cinemaRoomService.addCinemaRoom(cinemaRoomRequestDTO));
    }


}
