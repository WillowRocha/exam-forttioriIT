package com.forttiori.exam.repo.itinerary;

import com.forttiori.exam.model.id.CoordId;
import com.forttiori.exam.model.itinerary.Coord;
import org.springframework.data.repository.CrudRepository;

public interface CoordRepo extends CrudRepository<Coord, CoordId> {
}
