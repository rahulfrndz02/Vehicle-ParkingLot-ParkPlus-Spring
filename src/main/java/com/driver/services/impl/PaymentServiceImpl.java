package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {

        PaymentMode paymentMode;
        Reservation reservation = reservationRepository2.findById(reservationId).get();
        reservation.getSpot().setOccupied(false);

        if(mode.equalsIgnoreCase("cash")){
            paymentMode=PaymentMode.CASH;
        }
        else if (mode.equalsIgnoreCase("card")) {
            paymentMode=PaymentMode.CARD;
        }
        else if (mode.equalsIgnoreCase("upi")) {
            paymentMode=PaymentMode.UPI;
        }
        else{
            throw new Exception("Payment mode not detected");
        }

        int bill = reservation.getSpot().getPricePerHour()*reservation.getNumberOfHours();
        if(amountSent<bill){
            throw new Exception("Insufficient Amount");
        }

        Payment payment = new Payment(true,paymentMode);
        payment.setReservation(reservation);
        reservation.setPayment(payment);


        reservationRepository2.save(reservation);

        return payment;
    }
}
