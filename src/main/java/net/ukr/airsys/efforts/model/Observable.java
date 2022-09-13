package net.ukr.airsys.efforts.model;

import net.ukr.airsys.efforts.view.Observer;

public interface Observable {
   void registerObserver(Observer observer);
   void removeObserver(Observer observer);
   void notifyObservers();
}
