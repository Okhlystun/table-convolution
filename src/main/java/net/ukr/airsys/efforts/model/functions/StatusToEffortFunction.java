package net.ukr.airsys.efforts.model.functions;

import java.util.function.Function;

import net.ukr.airsys.efforts.model.TeamStatus;

public interface StatusToEffortFunction extends Function<Object, Double> {

	@Override
	default public Double apply(Object object) {

		if (!(object instanceof TeamStatus)) 
			throw new IllegalArgumentException(
				"Illegal type of argument.");

		TeamStatus teamStatus = (TeamStatus) object;
		String status = teamStatus.status();

		var result = switch(status) {
			case "Open" -> 10.;
			case "Closed - Complete" -> 25.;
			case "In Progress" -> 15.;
			case "Closed - Rejected" -> 5.;
			default -> Double.NaN;
		};

		return result;
	}
}