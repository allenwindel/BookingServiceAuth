package pw.io.booker.controller;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pw.io.booker.model.TravelPackage;
import pw.io.booker.repo.TravelPackageRepository;

@RestController
@Transactional
@RequestMapping("/travel-packages")
public class TravelPackageController {

  private TravelPackageRepository travelPackageRepository;

  public TravelPackageController(TravelPackageRepository travelPackageRepository) {
    super();
    this.travelPackageRepository = travelPackageRepository;
  }

  @GetMapping
  public List<TravelPackage> getAll(@RequestHeader("tokenID") String tokenID) {
    return (List<TravelPackage>) travelPackageRepository.findAll();
  }

  @PostMapping
  public List<TravelPackage> saveAll(@RequestBody List<TravelPackage> travelPackages,@RequestHeader("tokenID") String tokenID) {
    return (List<TravelPackage>) travelPackageRepository.saveAll(travelPackages);
  }

  @PutMapping
  public List<TravelPackage> updateAll(@RequestBody List<TravelPackage> travelPackages,@RequestHeader("tokenID") String tokenID) {
    for (TravelPackage travelPackage : travelPackages) {
      if (!travelPackageRepository.findById(travelPackage.getTravelPackageId()).isPresent()) {
        throw new RuntimeException("Travel Package should exist first");
      }
    }
    return (List<TravelPackage>) travelPackageRepository.saveAll(travelPackages);
  }

  @DeleteMapping
  public List<TravelPackage> deleteAll(
      @RequestParam("travelPackageIdList") List<Integer> travelPackageIdList,@RequestHeader("tokenID") String tokenID) {
    List<TravelPackage> travelPackageList =
        (List<TravelPackage>) travelPackageRepository.findAllById(travelPackageIdList);
    travelPackageRepository.deleteAll(travelPackageList);
    return travelPackageList;
  }

  @GetMapping("/{travelPackageId}")
  public TravelPackage getTravelPackage(@PathVariable("travelPackageId") int travelPackageId,@RequestHeader("tokenID") String tokenID) {
    return travelPackageRepository.findById(travelPackageId).get();
  }

  @PutMapping("/{travelPackageId}")
  public TravelPackage updateTravelPackage(@PathVariable("travelPackageId") int travelPackageId,
      @RequestBody TravelPackage travelPackage,@RequestHeader("tokenID") String tokenID) {
    if(travelPackageId != travelPackage.getTravelPackageId()) {
      throw new RuntimeException("Id is not the same with the object id");
    }
    if (!travelPackageRepository.findById(travelPackage.getTravelPackageId()).isPresent()) {
      throw new RuntimeException("Travel Package should exist first");
    }
    travelPackage.setTravelPackageId(travelPackageId);
    return travelPackageRepository.save(travelPackage);
  }

  @DeleteMapping("/{travelPackageId}")
  public TravelPackage deleteTravelPackage(@PathVariable("travelPackageId") int travelPackageId,@RequestHeader("tokenID") String tokenID) {
    TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();
    travelPackageRepository.delete(travelPackage);
    return travelPackage;
  }
}
