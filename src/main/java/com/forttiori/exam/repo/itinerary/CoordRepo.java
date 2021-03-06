package com.forttiori.exam.repo.itinerary;

import com.forttiori.exam.model.id.CoordId;
import com.forttiori.exam.model.itinerary.Coord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CoordRepo extends CrudRepository<Coord, CoordId> {

    ArrayList<Coord> findAllByLineId(Integer lineId);

    void deleteByLineId(Integer lineId);

    @Query("SELECT DISTINCT c.lineId FROM Coord c WHERE (6371 * acos(cos(radians(?1)) * cos(radians(latitude)) * cos(radians(?2) - radians(longitude)) + sin(radians(?1)) * sin(radians(latitude)))) <= ?3")
    ArrayList<Integer> findDistinctLineIdsByRadius(Double latitude, Double longitude, Double radius);
}
