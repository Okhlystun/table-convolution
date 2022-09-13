package net.ukr.airsys.efforts.view;

public interface View {
    void runPrompt();
	String getInFileName();
    void runNextPrompt();
    void save();
}