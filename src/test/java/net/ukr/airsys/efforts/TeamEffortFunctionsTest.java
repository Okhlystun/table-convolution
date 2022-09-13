package net.ukr.airsys.efforts;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import net.ukr.airsys.efforts.model.TeamStatus;
import net.ukr.airsys.efforts.model.functions.*;

public class TeamEffortFunctionsTest {

	 private final String RESOURCES_DIR = "src/test/resources/";

	 @Test
	 @DisplayName("\nEffortsFunction Test")
	 public void testEffortsFunction() {
        EffortsFunction func = new EffortsFunction(){};	
		Map<Double, Long> map0 = new HashMap<>();
		map0.put(10.0, 5L);
		Map<String, Map<Double, Long>> map = new HashMap<>();
		map.put("Open", map0);
		Map<Double, Long> map1 = new HashMap<>();
		map1.put(20.0, 1L);
		map.put("Closed - Complete", map1);
		double[] expected = {70d, 50d};
		double[] actual = Stream
			.of(func.apply(map))
			.mapToDouble(Double::doubleValue)
			.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("\nLineToTeamFunction Test")
    public void testLineToTeamFunction() {
    	LineToTeamFunction func = new LineToTeamFunction(){};
    	TeamStatus expected = new TeamStatus("London", "Open");
    	String line = null;
		try (Stream<String> stream = 
				Files.lines(Paths.get(RESOURCES_DIR + "unit-test-data.csv"));) { 
			line = stream
				.skip(1L)
				.limit(1)
				.toArray(i -> new String[i])[0];
		} catch (IOException e) { 
			System.err.println("Error reading Teams"); 
			e.printStackTrace(); 
		}
		TeamStatus actual = func.apply(line);
    	assertEquals(expected, actual); 
	}

    @Test
    @DisplayName("\nStatusToEffortFunction Test")
    public void testStatusToEffortFunction() {
    	StatusToEffortFunction func = new StatusToEffortFunction(){};
       	Double actual = func.apply(new TeamStatus("London", "In Progress"));
    	assertEquals(15d, actual);
    }

    @Test
    @DisplayName("\nTeamStatusTableFunction Test")
    public void testTeamStatusTableFunction() {
    	TeamStatusTableFunction func = new TeamStatusTableFunction(){};
    	Map<Double, Long> map0 = new HashMap<>();
    	map0.put(10.0, 1L);
    	Map<String, Map<Double, Long>> map1 = new HashMap<>();
    	map1.put("Open", map0);
    	Map<String, Map<String, Map<Double, Long>>> expected = 
    		new HashMap<>();
    	expected.put("London", map1);
    	Map<String, Map<String, Map<Double, Long>>> actual =
    		func.apply(RESOURCES_DIR + "unit-test-data-short.csv");
    	assertEquals(expected, actual);
    }
}
