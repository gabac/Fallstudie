package ch.hszt.mdp.domain;

import java.util.ArrayList;
import java.util.List;

public class Stream {
	private List<Activity> todaysActivities = new ArrayList<Activity>();
	private List<Activity> yesterdaysActivities = new ArrayList<Activity>();
	private List<Activity> pastActivities = new ArrayList<Activity>();

	public List<Activity> getTodaysActivities() {
		return todaysActivities;
	}

	public void setTodaysActivities(List<Activity> todaysActivities) {
		this.todaysActivities = todaysActivities;
	}
	
	public void addTodaysActivity(Activity activity) {
		todaysActivities.add(activity);
	}

	public List<Activity> getYesterdaysActivities() {
		return yesterdaysActivities;
	}

	public void setYesterdaysActivities(List<Activity> yesterdaysActivities) {
		this.yesterdaysActivities = yesterdaysActivities;
	}
	
	public void addYesterdaysActivities(Activity activity) {
		yesterdaysActivities.add(activity);
	}

	public List<Activity> getPastActivities() {
		return pastActivities;
	}

	public void setPastActivities(List<Activity> pastActivities) {
		this.pastActivities = pastActivities;
	}
	
	public void addPastActivities(Activity activity) {
		pastActivities.add(activity);
	}

}
