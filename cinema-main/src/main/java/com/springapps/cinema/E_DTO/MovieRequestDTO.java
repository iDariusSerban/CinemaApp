package com.springapps.cinema.E_DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class MovieRequestDTO {

    private String title;
    private Long cinemaRoomId;

    private Double price;

    @JsonProperty("dates")
    List<ProjectionRequestDTO> projectionRequestDTOs;

    public MovieRequestDTO(String title, Long cinemaRoomId, List<ProjectionRequestDTO> projectionRequestDTOs, Double price) {
        this.title = title;
        this.cinemaRoomId = cinemaRoomId;
        this.projectionRequestDTOs = projectionRequestDTOs;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCinemaRoomId() {
        return cinemaRoomId;
    }

    public void setCinemaRoomId(Long cinemaRoomId) {
        this.cinemaRoomId = cinemaRoomId;
    }

    public List<ProjectionRequestDTO> getProjectionRequestDTOs() {
        return projectionRequestDTOs;
    }

    public void setProjectionRequestDTOs(List<ProjectionRequestDTO> projectionRequestDTOs) {
        this.projectionRequestDTOs = projectionRequestDTOs;
    }
}
