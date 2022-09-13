package net.ukr.airsys.efforts.model;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import net.ukr.airsys.efforts.model.functions.TeamStatusTableFunction;
import net.ukr.airsys.efforts.model.functions.EffortsFunction;
import net.ukr.airsys.efforts.view.Observer;

public class TeamEfforts implements Model {

	private final TeamStatusTableFunction teamStatusTable;
	private final EffortsFunction efforts;

	private String inFileName;
   	private Map<String, Double[]> effortsTable;
   	private List<Observer> observers = new ArrayList<>();

	public TeamEfforts() {
		this.teamStatusTable = new TeamStatusTableFunction(){};
		this.efforts = new EffortsFunction(){};
	}

	public TeamEfforts(TeamStatusTableFunction teamStatusTable,
			EffortsFunction efforts) {
		this.teamStatusTable = teamStatusTable;
		this.efforts = efforts;
	}

	@Override
	public void initialize(String inFileName) {
		this.inFileName = inFileName;
      	this.effortsTable = teamStatusTable
			.apply(inFileName)
			.entrySet()
			.stream()
			.collect(Collectors.toMap(k -> k.getKey(),
				v -> efforts.apply(v.getValue())));
		notifyObservers();
	}

	@Override
	public Map<String, Double[]> getEffortsTable() {
		return this.effortsTable;
    }

    @Override
    public void registerObserver(Observer observer) {
      	this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
      	int i = this.observers.indexOf(observer);
      	if (i >= 0) {
         	this.observers.remove(i);
      	}
    }

    @Override
   	public void notifyObservers() {
      	for(int i = 0; i < this.observers.size(); i++) {
         	Observer observer = this.observers.get(i);
         	observer.update();
      	}
    }
}
