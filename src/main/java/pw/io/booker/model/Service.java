package pw.io.booker.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Service {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "service_id")
  private int serviceId;
  private String serviceName;
  private String description;

  @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
  private List<Image> images;

  @JsonIgnore
  @OneToMany(mappedBy = "service", cascade=CascadeType.REMOVE)
  private List<ServiceFee> serviceFee;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "travel_package_id")
  private TravelPackage travelPackage;

  @JsonIgnore
  @ManyToMany(mappedBy="availedServiceList", cascade=CascadeType.REMOVE)
  private List<Reservation> reservations;

  public int getServiceId() {
    return serviceId;
  }

  public void setServiceId(int serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public List<ServiceFee> getServiceFee() {
    return serviceFee;
  }

  public void setServiceFee(List<ServiceFee> serviceFee) {
    this.serviceFee = serviceFee;
  }

  public TravelPackage getTravelPackage() {
    return travelPackage;
  }

  public void setTravelPackage(TravelPackage travelPackage) {
    this.travelPackage = travelPackage;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }


  @PrePersist
  private void createRelationships() {
    for(Image image : this.getImages()) {
      image.setService(this);
    }
  }

}
