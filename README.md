# Docker Demo for Nginix auth-basic, Spring , MongoDB

## Build the Application

* Build the application
```
mvn clean package
```

* Run using docker-compose.
```
docker-compose up --build
```

* Nginix auth-basic user and password

UserName: admin
Password: admin


### Code Snippets
1. #### Maven Dependencies
    Need to add below dependencies to enable Mongo related config in **pom.xml**. Lombok's dependency is to get rid of boiler-plate code.   
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
   
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```
   
   
2. #### Properties file
    Reading Mongo DB related properties from **application.properties** file and configuring Mongo connection factory for mongoDB.  

    **src/main/resources/application.properties**
     ```
	spring.data.mongodb.uri=mongodb://colorMongo:27017/colordb
	spring.data.mongodb.host=localhost
	spring.data.mongodb.port=27017
	spring.data.mongodb.database=colordb
	spring.jackson.default-property-inclusion=NON_NULL
     ```
   
   
3. #### Model class
    Below are the model classes which we will store in MongoDB and perform CRUD operations.  
    **ca.firefly.docker.demo.web.model.ColorCounter.java**  
    ```
    @Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Document(collection = "colorcounter")
	public class ColorCounter implements Serializable {

	    @Id
	    private String id;
	    private String color;
	    private int count;

	    // Constructor, Getter and Setter
	}
   ```
   
   
4. #### get operation for Color

    In **ca.firefly.docker.demo.web.control.DockerDemoController.java** class, 
    we have exposed 2 endpoints for basic get operations
    - GET Color
    - GET All Colors
    
    ```
	@GetMapping
	public Attributes getMethodName()
	{
		```
		colorCounterService.updateColorCounter(this.attributes.color);
		return this.attributes;
	}

	@GetMapping(value = "/all")
    public ResponseEntity<List<?>> findAll() {
        List<?> list = colorCounterService.findAll();
        return ResponseEntity.ok().body(list);
    }
    ```
   
    In **ca.firefly.docker.demo.web.repository.ColorCounterRepository.java**, we are extending `MongoRepository<Class, ID>` interface which enables CRUD related methods.
    ```
    public interface ColorCounterRepository extends MongoRepository<SuperHero, String> {
    }
    ```
   
   In **ca.firefly.docker.demo.web.service.impl.ColorCounterServiceImpl.java**, we are autowiring above interface using `@Autowired` annotation and doing CRUD operation.



    
### API Endpoints

- #### Color Operations
    > **GET Color** http://localhost/get  - Get Color and increase color count +1
    
    > **GET Colors** http://localhost/get/all  - Get all Colors
       
   

Thanks...
