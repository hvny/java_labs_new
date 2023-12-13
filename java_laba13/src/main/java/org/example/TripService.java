package org.example;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Component
public class TripService {
    private List<Trip> trips;

    @PostConstruct
    public void init() {
        trips = new ArrayList<>();
        trips.add(new Trip(1, "2021-08-01", "Moscow", "St. Petersburg"));
        trips.add(new Trip(2, "2021-09-15", "St. Petersburg", "Helsinki"));
        trips.add(new Trip(3, "2021-10-20", "Moscow", "Paris"));
        trips.add(new Trip(4, "2021-11-05", "Paris", "Rome"));
        trips.add(new Trip(5, "2021-12-01", "Moscow", "New York"));
        trips.add(new Trip(6, "2022-01-15", "New York", "Los Angeles"));
        trips.add(new Trip(7, "2022-02-20", "Los Angeles", "Sydney"));
        trips.add(new Trip(8, "2022-03-05", "Sydney", "Tokyo"));
        trips.add(new Trip(9, "2022-04-01", "Moscow", "Barcelona"));
        trips.add(new Trip(10, "2022-05-15", "Barcelona", "Rome"));
    }

    public void printAll() {
        for (Trip trip : trips) {
            System.out.println(trip.getId() + ". " + trip.getDate() + ", " + trip.getDeparture() + " - " + trip.getArrival());
        }
    }

    public Trip findByDate(String date) {
        for (Trip trip : trips) {
            if (trip.getDate().equals(date)) {
                return trip;
            }
        }
        return null;
    }

    public List<Trip> findByPlaceArrival(String arrival) {
        List<Trip> result = new ArrayList<>();
        for (Trip trip : trips) {
            if (trip.getArrival().equals(arrival)) {
                result.add(trip);
            }
        }
        return result;
    }
}
