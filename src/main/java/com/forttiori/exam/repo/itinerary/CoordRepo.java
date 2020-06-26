package com.forttiori.exam.repo.itinerary;

import com.forttiori.exam.model.id.CoordId;
import com.forttiori.exam.model.itinerary.Coord;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CoordRepo extends CrudRepository<Coord, CoordId> {

    ArrayList<Coord> findAllByLineId(Integer lineId);

    void deleteByLineId(Integer lineId);
}
