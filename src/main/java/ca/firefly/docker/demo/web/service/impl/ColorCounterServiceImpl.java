package ca.firefly.docker.demo.web.service.impl;

import ca.firefly.docker.demo.web.model.ColorCounter;
import ca.firefly.docker.demo.web.repository.ColorCounterRepository;
import ca.firefly.docker.demo.web.service.ColorCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@Service
public class ColorCounterServiceImpl implements ColorCounterService {

    @Autowired
    private ColorCounterRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ColorCounter> findAll() {
        return repository.findAll();
    }

    @Override
    public ColorCounter findById(String id) {
        return repository.findById(id).orElse(new ColorCounter());
    }

    @Override
    public ColorCounter save(ColorCounter colorCounter) {
        return repository.save(colorCounter);
    }

    @Override
    public ColorCounter update(ColorCounter colorCounter) {
        return repository.save(colorCounter);
    }

    @Override
    public void delete(String id) {
       repository.findById(id).ifPresent(colorCounter -> repository.delete(colorCounter));
    }

    @Override
    public void updateColorCounter(String color) {

        Query query = new Query();
        query.addCriteria(Criteria.where("color").is(color));
        ColorCounter colorCounter = mongoTemplate.findOne(query, ColorCounter.class);

        Update update = new Update();
        update.set("count", colorCounter.getCount() + 1);
        mongoTemplate.update(ColorCounter.class).matching(query).apply(update).first();  
    }
}
