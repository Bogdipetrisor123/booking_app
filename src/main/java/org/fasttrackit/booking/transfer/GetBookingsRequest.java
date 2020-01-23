package org.fasttrackit.booking.transfer;

import java.time.LocalDate;

public class GetBookingsRequest {
    private String partialDestination;
    private LocalDate checkIn;
    private LocalDate checkOut;


    public String getPartialDestination() {
        return partialDestination;
    }

    public void setPartialDestination(String partialDestination) {
        this.partialDestination = partialDestination;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "GetBookingsRequest{" +
                "partialDestination='" + partialDestination + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
