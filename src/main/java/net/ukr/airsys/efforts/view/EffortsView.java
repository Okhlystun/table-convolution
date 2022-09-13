package net.ukr.airsys.efforts.view;

import java.util.Scanner;
import java.util.Map;

import net.ukr.airsys.efforts.model.Model;
import net.ukr.airsys.efforts.controller.Controller;

public class EffortsView implements View, Observer {

    private final Controller controller;
    private final Model model;
    private final Scanner input;   
    private Printer printer;   
    private String inFileName;
    private String outFileName;
    private Map<String, Double[]> efforts;

    {
        input = new Scanner(System.in);
        printer = null;
    }

    public EffortsView(Controller controller, Model model) {	
		this.controller = controller;
		this.model = model;
        model.registerObserver((Observer) this);
    }
    
    public void runPrompt() {
        System.out.print("\n\tEnter file name: ");
        this.inFileName = input.next();
	}

    public String getInFileName() {
        return this.inFileName;
    }

    public void runNextPrompt() {
        System.out.print("\tSave to a file? [y/n] ");
        if (input.next().equals("y")) {
            System.out.print("\tEnter file name: ");
            this.outFileName = input.next();
            save();
        }
        System.exit(0);
    }
 
    public void update() {
        printer = new Printer(model.getEffortsTable());
        printer.render();

    }

    public void save() {
        printer.setWriter(outFileName);
        printer.save();
    }    
}