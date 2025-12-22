package com.pethotel.dto;

import java.sql.Timestamp;

public class BookingDTO {

    private int bookingId;
    private int customerId;
    private int petId;
    private int cageId;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private String status;          // Pending, Active, Completed, Cancelled
    private String paymentStatus;   // Paid, Unpaid
    private double totalPrice;
    private Timestamp createdDate;

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, int customerId, int petId, int cageId,
                      Timestamp checkInDate, Timestamp checkOutDate,
                      String status, String paymentStatus,
                      double totalPrice, Timestamp createdDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.petId = petId;
        this.cageId = cageId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.totalPrice = totalPrice;
        this.createdDate = createdDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getCageId() {
        return cageId;
    }

    public void setCageId(int cageId) {
        this.cageId = cageId;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
