package com.springapps.cinema.B_service;

import com.springapps.cinema.C_repository.CinemaRoomRepository;
import com.springapps.cinema.D_model.CinemaRoom;
import com.springapps.cinema.D_model.Seat;
import com.springapps.cinema.E_DTO.CinemaRoomRequestDTO;
import com.springapps.cinema.E_DTO.ExtraPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaRoomService {

    private CinemaRoomRepository cinemaRoomRepository;
    @Autowired
    public CinemaRoomService(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    @Transactional
    public CinemaRoom addCinemaRoom (CinemaRoomRequestDTO cinemaRoomRequestDTO){
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setName(cinemaRoomRequestDTO.getName());
        cinemaRoom.setSeats(generateSeatsForCinemaRoom(cinemaRoomRequestDTO, cinemaRoom));
        return cinemaRoomRepository.save(cinemaRoom);
    }

    @Transactional
    public List<Seat> generateSeatsForCinemaRoom(CinemaRoomRequestDTO cinemaRoomRequestDTO, CinemaRoom cinemaRoom){
        List<Seat> result = new ArrayList<>();
        for (int i =1; i<=cinemaRoomRequestDTO.getNumberOfRows(); i++){
            for (int j=1; j<=cinemaRoomRequestDTO.getNumberOfCols(); j++){
                Seat seat = new Seat();
                seat.setSeatRow(i);
                seat.setSeatColumn(j);
                seat.setAvailable(true);
                seat.setCinemaRoom(cinemaRoom);
                seat.setExtraPrice(getExtraPrice(i,cinemaRoomRequestDTO.getExtraPrices()));
                result.add(seat);
            }
        }
        return result;
    }

    @Transactional
    public Double getExtraPrice (Integer row, List<ExtraPriceDTO> extraPriceDTOS){
        /*for (ExtraPriceDTO extraPriceDTO: extraPriceDTOS){
            if (row>= extraPriceDTO.getStartingRow() && row<=extraPriceDTO.getEndingRow()){
                return extraPriceDTO.getExtraPrice();
            }
        }*/

        Optional<Double> extraPriceOptional = extraPriceDTOS.stream()
                .filter(extraPriceDTO -> row>= extraPriceDTO.getStartingRow() && row<=extraPriceDTO.getEndingRow())
                .map(extraPriceDTO -> extraPriceDTO.getExtraPrice())
                .findFirst();

        return extraPriceOptional.orElse(0.0);
    }
}
