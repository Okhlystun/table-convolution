package net.ukr.airsys.efforts;

import net.ukr.airsys.efforts.model.TeamEfforts;
import net.ukr.airsys.efforts.controller.EffortsController;

public class TeamEffortsApplication {

	public static void main(String[] args) {

		new EffortsController(new TeamEfforts());

    }
}
