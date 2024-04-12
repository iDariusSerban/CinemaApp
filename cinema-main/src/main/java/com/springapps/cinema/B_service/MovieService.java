package com.springapps.cinema.B_service;

import com.springapps.cinema.C_repository.CinemaRoomRepository;
import com.springapps.cinema.C_repository.MovieRepository;
import com.springapps.cinema.D_model.CinemaRoom;
import com.springapps.cinema.D_model.Movie;
import com.springapps.cinema.D_model.Projection;
import com.springapps.cinema.E_DTO.MovieRequestDTO;
import com.springapps.cinema.E_DTO.ProjectionRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CinemaRoomRepository cinemaRoomRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    @Transactional

    public Movie addMovie (MovieRequestDTO movieRequestDTO){

        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(movieRequestDTO.getCinemaRoomId()).orElseThrow(() -> new RuntimeException("cinema room not found"));
        Movie movie = new Movie();
        movie.setTitle(movieRequestDTO.getTitle());
        movie.setMoviePrice(movieRequestDTO.getPrice());
        movie.setProjections(generateMovieProjections(movieRequestDTO.getProjectionRequestDTOs(),movie, cinemaRoom));
        return movieRepository.save(movie);
    }

    @Transactional
    public List<Projection> generateMovieProjections (List<ProjectionRequestDTO> projectionRequestDTOS, Movie movie, CinemaRoom cinemaRoom) {
       return  projectionRequestDTOS.stream()
                .map(projectionRequestDTO -> mapFromDTOToProjection(projectionRequestDTO, movie, cinemaRoom))
                .collect(Collectors.toList());
    }

    @Transactional
    public Projection mapFromDTOToProjection (ProjectionRequestDTO projectionRequestDTO, Movie movie, CinemaRoom cinemaRoom){
        Projection projection = new Projection();
        projection.setEndDate(projectionRequestDTO.getEndTime());
        projection.setStartDate(projectionRequestDTO.getStartTime());
        projection.setMovie(movie);
        projection.setCinemaRoom(cinemaRoom);
        return projection;
    }
}
