package com.driver.Repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class HotelManagementRepository {

    HashMap<String , Hotel> hotelDb  = new HashMap<>();
    HashMap<Integer , User> userDb  = new HashMap<>();
    HashMap<String , Booking> bookingDb  = new HashMap<>();
    public String addHotelToDb(Hotel hotel){
        if(hotel==null || hotel.getHotelName()==null) return "FAILURE";
        if(hotelDb.containsKey(hotel.getHotelName())) return "FAILURE";

        hotelDb.put(hotel.getHotelName() , hotel);

        return "SUCCESS";
    }

    public int addUser(User user){
        userDb.put(user.getaadharCardNo() , user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacility(){
        String pans  = "";
        int max  = 0;

        for(String s : hotelDb.keySet()){
            Hotel hotel  = hotelDb.get(s);
            List<Facility> temp  = hotel.getFacilities();
            if(temp.size()>max){
                max  = temp.size();
                pans  = s;
            }
            if(temp.size()==max && max>0){
                if(s.compareTo(hotel.getHotelName())<0){
                    pans  = s;
                }
            }
        }
        return pans;
    }

    public int bookRoom(Booking booking){
        bookingDb.put(booking.getBookingId(), booking);
        String hotelName  = booking.getHotelName();
        Hotel  hotel  = hotelDb.get(hotelName);
        int availableRooms  = hotel.getAvailableRooms();
        int numOfRoom  = booking.getNoOfRooms();

        if(numOfRoom>availableRooms) return -1;


        int pricePerNight  = hotel.getPricePerNight();

        return pricePerNight*numOfRoom;
    }

    public int getBookingOfAPeron(Integer adharCard){
        int count  = 0;
        for(String s : bookingDb.keySet()){
            Booking booking  = bookingDb.get(s);
            if(booking.getBookingAadharCard()==adharCard){
                count++;
            }
        }
        return count;
    }

    public Hotel updateFacilities(List<Facility> facilities , String hotelName){
         Hotel hotel  = hotelDb.get(hotelName);
         List<Facility> temp  = hotel.getFacilities();
         List<Facility> uniqueFacility  = new ArrayList<>();

         for(Facility facility : facilities){
             if(uniqueFacility.contains(facility)==false && temp.contains(facility) == false){
                  temp.add(facility);
                  uniqueFacility.add(facility);
             }
         }
         hotelDb.put(hotel.getHotelName() , hotel);
         return hotel;
    }

}
