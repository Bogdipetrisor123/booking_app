package org.fasttrackit.booking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String destination;
    @NotNull
    private LocalDate checkIn;
    @NotNull
    private LocalDate checkOut;
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer numberOfPersons;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", numberOfPersons=" + numberOfPersons +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
