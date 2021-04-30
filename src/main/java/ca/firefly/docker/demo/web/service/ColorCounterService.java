package ca.firefly.docker.demo.web.service;

import ca.firefly.docker.demo.web.model.ColorCounter;
import java.util.List;

public interface ColorCounterService {

    List<?> findAll();

    ColorCounter findById(String id);

    ColorCounter save(ColorCounter colorCounter);

    ColorCounter update(ColorCounter colorCounter);

    void delete(String id);
    
    void updateColorCounter (String color);
}
