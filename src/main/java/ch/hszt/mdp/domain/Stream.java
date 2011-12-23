package ch.hszt.mdp.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

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

	public void addActivites(List<Activity> activities) {

		DateTime now = new DateTime();

		DateTime startOfToday = now.toDateMidnight().toInterval().getStart();
		DateTime endOfToday = now.toDateMidnight().toInterval().getEnd();
		DateTime startOfYesterDay = now.minusDays(1).toDateMidnight().toInterval().getStart();

		for (Activity activity : activities) {
			if (activity.getTime().isAfter(startOfToday) && activity.getTime().isBefore(endOfToday)) {
				addTodaysActivity(activity);
			} else if (activity.getTime().isBefore(startOfToday) && activity.getTime().isAfter(startOfYesterDay)) {
				addYesterdaysActivities(activity);
			} else {
				addPastActivities(activity);
			}
		}
	}
}
