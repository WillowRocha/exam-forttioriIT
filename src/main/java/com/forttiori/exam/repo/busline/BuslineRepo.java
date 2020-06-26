package com.forttiori.exam.repo.busline;

import com.forttiori.exam.model.busline.Busline;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuslineRepo extends CrudRepository<Busline, Integer> {

    void deleteById(Integer id);

    Iterable<Busline> findAllByCodeContainingOrNameContainingAllIgnoreCase(String code, String name);

    Iterable<Busline> findAllByIdIn(List<Integer> lineIds);
}
