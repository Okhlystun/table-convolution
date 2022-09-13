package net.ukr.airsys.efforts.controller;

import java.nio.file.Paths;
import java.nio.file.Path;

import net.ukr.airsys.efforts.model.Model;
import net.ukr.airsys.efforts.view.View;
import net.ukr.airsys.efforts.view.EffortsView;

public class EffortsController implements Controller {
	
	private final Model model;
	private final View view;
	private String inFileName;
   
	public EffortsController(Model model) {
		this.model = model;
		this.view = new EffortsView(this, model);
	    this.control();
	}

	@Override
	public void control() {
		view.runPrompt();
	    model.initialize(view.getInFileName());
		view.runNextPrompt();
		view.save();
	}
}