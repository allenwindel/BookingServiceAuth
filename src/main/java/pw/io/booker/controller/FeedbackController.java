package pw.io.booker.controller;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pw.io.booker.model.Feedback;
import pw.io.booker.model.Reservation;
import pw.io.booker.repo.FeedbackRepository;
import pw.io.booker.repo.ReservationRepository;

@RestController
@Transactional
@RequestMapping("/reservations/{reservationId}/feedbacks")
public class FeedbackController {

  private ReservationRepository reservationRepository;
  private FeedbackRepository feedbackRepository;

  public FeedbackController(ReservationRepository reservationRepository,
      FeedbackRepository feedbackRepository) {
    super();
    this.reservationRepository = reservationRepository;
    this.feedbackRepository = feedbackRepository;
  }

  @GetMapping
  public List<Feedback> getFeedback(@PathVariable("reservationId") int reservationId) {
    return reservationRepository.findById(reservationId).get().getFeedbacks();
  }

  @PostMapping
  public List<Feedback> saveFeedback(@PathVariable("reservationId") int reservationId,
      @RequestBody List<Feedback> feedbacks) {
    Reservation reservation = reservationRepository.findById(reservationId).get();
    reservation.getFeedbacks().addAll(feedbacks);
    return reservationRepository.save(reservation).getFeedbacks();
  }

  @PutMapping
  public List<Feedback> updateFeedback(@PathVariable("reservationId") int reservationId,
      @RequestBody List<Feedback> feedbacks) {
    for (Feedback feedback : feedbacks) {
      if(!feedbackRepository.findById(feedback.getFeedbackId()).isPresent()) {
        throw new RuntimeException("Feedback should exist first");
      }
    }
    return (List<Feedback>) feedbackRepository.saveAll(feedbacks);
  }

  @DeleteMapping
  public List<Feedback> deleteFeedback(@PathVariable("reservationId") int reservationId,
      @RequestParam("feedbackIdList") List<Integer> feedBackIdList) {
    List<Feedback> feedbacks = (List<Feedback>) feedbackRepository.findAllById(feedBackIdList);
    feedbackRepository.deleteAll(feedbacks);
    return feedbacks;
  }
}
