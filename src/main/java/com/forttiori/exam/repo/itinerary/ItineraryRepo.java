package com.forttiori.exam.repo.itinerary;

import com.forttiori.exam.model.itinerary.Itinerary;
import org.springframework.data.repository.CrudRepository;

public interface ItineraryRepo extends CrudRepository<Itinerary, Integer> {

    void deleteByLineId(Integer lineId);
}
