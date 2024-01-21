package com.driver.Service;

import com.driver.Repository.HotelManagementRepository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {

    private HotelManagementRepository repo = new HotelManagementRepository();

    public String addHotel(Hotel hotel){
        return repo.addHotelToDb(hotel);
    }

    public int addUser(User user){
        return repo.addUser(user);
    }

    public String getHotelWithMostFacility(){
        return repo.getHotelWithMostFacility();
    }

    public int bookRoom(Booking booking){
        return repo.bookRoom(booking);
    }

    public int getBookingByAPerson(Integer A){
        return repo.getBookingOfAPeron(A);
    }

    public Hotel updateFacilities(List<Facility> facilities , String hotel){
        return repo.updateFacilities(facilities ,hotel);
    }
}
