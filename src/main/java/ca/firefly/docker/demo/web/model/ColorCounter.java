package ca.firefly.docker.demo.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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