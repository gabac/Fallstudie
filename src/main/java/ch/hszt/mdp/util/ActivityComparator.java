package ch.hszt.mdp.util;

import java.util.Comparator;

import ch.hszt.mdp.domain.Activity;

public class ActivityComparator implements Comparator<Activity> {

	@Override
	public int compare(Activity o1, Activity o2) {
		return (o1.getTime().compareTo(o2.getTime())) * -1;
	}

}
