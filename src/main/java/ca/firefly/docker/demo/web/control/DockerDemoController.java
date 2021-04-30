package ca.firefly.docker.demo.web.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import ca.firefly.docker.demo.domain.Attributes;
import lombok.extern.slf4j.Slf4j;
import ca.firefly.docker.demo.web.service.ColorCounterService;
import ca.firefly.docker.demo.web.repository.ColorCounterRepository;
import ca.firefly.docker.demo.web.model.ColorCounter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ca.firefly.docker.demo.web.utils.HelperUtil;


@RestController
@RequestMapping("/get")
public class DockerDemoController 
{
	private Attributes attributes;

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
    private ColorCounterService colorCounterService;

	@Autowired
	private ColorCounterRepository colorCounterRepository;


	
	public DockerDemoController( @Value("${demo.color:red}") String color )
	{
		this.attributes = new Attributes( color );
	}

	@GetMapping
	public Attributes getMethodName()
	{

		/*
			init ColorCounter collection...
		 */

		List<ColorCounter> colorCounters = colorCounterRepository.findAll();
		if (colorCounters.size() == 0) {
			LOGGER.info("******* Inserting Colors to DB *******");
			colorCounterRepository.saveAll(HelperUtil.colorSupplier.get());
		} else {
			LOGGER.info("******* Colors stored in DB Size :: {}", colorCounters.size());
			LOGGER.info("******* Colors stored in DB :: {}", colorCounters);
		}

		/*
			increase color count +1
		 */
		
		colorCounterService.updateColorCounter(this.attributes.color);

		return this.attributes;
	}

	@GetMapping(value = "/all")
    public ResponseEntity<List<?>> findAll() {
        List<?> list = colorCounterService.findAll();
        return ResponseEntity.ok().body(list);
    }


}
