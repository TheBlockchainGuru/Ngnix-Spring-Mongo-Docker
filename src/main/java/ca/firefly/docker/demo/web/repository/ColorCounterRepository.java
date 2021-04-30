package ca.firefly.docker.demo.web.repository;


import ca.firefly.docker.demo.web.model.ColorCounter;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ColorCounterRepository extends MongoRepository<ColorCounter, String> {

}
