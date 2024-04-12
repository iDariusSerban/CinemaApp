package com.springapps.cinema.A_controller;

import com.springapps.cinema.B_service.MovieService;
import com.springapps.cinema.D_model.CinemaRoom;
import com.springapps.cinema.D_model.Movie;
import com.springapps.cinema.E_DTO.CinemaRoomRequestDTO;
import com.springapps.cinema.E_DTO.MovieRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequestDTO movieRequestDTO){
        return ResponseEntity.ok(movieService.addMovie(movieRequestDTO));
    }
}
