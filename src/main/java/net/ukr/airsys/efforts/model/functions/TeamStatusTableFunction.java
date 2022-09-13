package net.ukr.airsys.efforts.model.functions;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

import net.ukr.airsys.efforts.model.TeamStatus;

public interface TeamStatusTableFunction extends 
		Function<String, Map<String, Map<String, Map<Double, Long>>>> {

	@Override
	default Map<String, Map<String, Map<Double, Long>>> apply(String file) {
    	
    	Map<String, Map<String, Map<Double, Long>>> result = null;
		try (Stream<String> stream = Files.lines(Paths.get(file));) { 
			result = stream
				.skip(1L)
				.filter(line -> !line.isEmpty())
				.map(new LineToTeamFunction(){})
				.collect(Collectors.groupingBy(TeamStatus::team, 
					Collectors.groupingBy(TeamStatus::status, 
						Collectors.groupingBy(
							new StatusToEffortFunction(){},
							Collectors.counting()))));
		} catch (IOException e) { 
			System.err.println("Error reading Teams"); 
			e.printStackTrace(); 
		} 
		return result;
    }

    
}
