package ca.firefly.docker.demo.web.utils;


import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import ca.firefly.docker.demo.web.model.ColorCounter;

public class HelperUtil {

    private HelperUtil() {
    }

    public static Supplier<List<ColorCounter>> colorSupplier = () ->
            Arrays.asList(
		        ColorCounter.builder().color("brown").count(0).build(),
                ColorCounter.builder().color("green").count(0).build(),
                ColorCounter.builder().color("pink").count(0).build(),
                ColorCounter.builder().color("orange").count(0).build() 
            );

}
