package com.springapps.cinema.E_DTO;

import java.util.List;

public class CinemaRoomRequestDTO {

    private Integer numberOfRows;
    private Integer numberOfCols;

    private String name;

    private List<ExtraPriceDTO> extraPrices;

    public CinemaRoomRequestDTO(Integer numberOfRows, Integer numberOfCols, List<ExtraPriceDTO> extraPrices, String name) {
        this.numberOfRows = numberOfRows;
        this.numberOfCols = numberOfCols;
        this.extraPrices = extraPrices;
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public Integer getNumberOfCols() {
        return numberOfCols;
    }

    public void setNumberOfCols(Integer numberOfCols) {
        this.numberOfCols = numberOfCols;
    }

    public List<ExtraPriceDTO> getExtraPrices() {
        return extraPrices;
    }

    public void setExtraPrices(List<ExtraPriceDTO> extraPrices) {
        this.extraPrices = extraPrices;
    }
}
