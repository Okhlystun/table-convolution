package net.ukr.airsys.efforts.model.functions;

import java.util.function.Function;

import net.ukr.airsys.efforts.model.TeamStatus;

public interface LineToTeamFunction extends Function<String, TeamStatus> {

	@Override
	default TeamStatus apply(String line) {
		String[] items = line.split(","); 
		return new TeamStatus(items[items.length - 1], items[4]);
	}
}
