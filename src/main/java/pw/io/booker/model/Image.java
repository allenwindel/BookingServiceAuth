package pw.io.booker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="image_id")
  private int imageId;
  private String description;
  private String imageUrl;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "service_id")
  private Service service;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "travel_package_id")
  private TravelPackage travelPackage;

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public TravelPackage getTravelPackage() {
    return travelPackage;
  }

  public void setTravelPackage(TravelPackage travelPackage) {
    this.travelPackage = travelPackage;
  }
}
