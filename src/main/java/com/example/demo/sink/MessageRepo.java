package com.example.demo.sink;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Sink, Long> {

}