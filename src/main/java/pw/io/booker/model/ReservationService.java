package pw.io.booker.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class ReservationService {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int reservationServiceId;

  @ManyToOne
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;

  public int getReservationServiceId() {
    return reservationServiceId;
  }

  public void setReservationServiceId(int reservationServiceId) {
    this.reservationServiceId = reservationServiceId;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }


}
