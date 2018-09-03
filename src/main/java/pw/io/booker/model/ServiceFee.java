package pw.io.booker.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ServiceFee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="service_fee_id")
  private int serviceFeeId;
  private float amount;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;
  private LocalDate startDate;

  public int getServiceFeeId() {
    return serviceFeeId;
  }

  public void setServiceFeeId(int serviceFeeId) {
    this.serviceFeeId = serviceFeeId;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }
}
