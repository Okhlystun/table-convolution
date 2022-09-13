package net.ukr.airsys.efforts.model.functions;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface EffortsFunction extends 
		Function<Map<String, Map<Double, Long>>, Double[]> {

	@Override
	default Double[] apply(Map<String, Map<Double, Long>> map) {

		String[] remainingStatus = new String[] { "Open", "In Progress" };
		Double[] efforts = null;
		double total = 0; 
		double remaining = 0;

		for (Map.Entry<String, Map<Double, Long>> entry : 
				map.entrySet()) {
			
			String status = entry.getKey();
			double current = 0;

			for (Map.Entry<Double, Long> result : 
					entry.getValue().entrySet()) {

				current = result.getKey() * result.getValue();
				total += current;
				if (status.equals(remainingStatus[0]) || 
						status.equals(remainingStatus[1])) 
					remaining += current;
				efforts = new Double[] { total, remaining };
			
			}
		}
		return efforts;
	} 
}