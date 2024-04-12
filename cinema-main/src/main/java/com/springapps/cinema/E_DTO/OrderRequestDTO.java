package com.springapps.cinema.E_DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequestDTO {

    private Long projectionID;

    private Long userId;
    @JsonProperty("seats")
    private List<TicketRequestDTO> ticketRequestDTOs;

    public OrderRequestDTO(Long projectionID, List<TicketRequestDTO> ticketRequestDTOs, Long userId) {
        this.projectionID = projectionID;
        this.ticketRequestDTOs = ticketRequestDTOs;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectionID() {
        return projectionID;
    }

    public void setProjectionID(Long projectionID) {
        this.projectionID = projectionID;
    }

    public List<TicketRequestDTO> getTicketRequestDTOs() {
        return ticketRequestDTOs;
    }

    public void setTicketRequestDTOs(List<TicketRequestDTO> ticketRequestDTOs) {
        this.ticketRequestDTOs = ticketRequestDTOs;
    }
}
