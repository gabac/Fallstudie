package ch.hszt.mdp.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {
	
	public enum ActivityType {
		status,
		profile,
		friend,
		like,
		dislike
	}
	
	@Id
	@GeneratedValue
	private Integer activity_id;
	
	private Integer user_id;
	
	@Enumerated(EnumType.STRING)
	private ActivityType typ;
	
	private String content;
	
	public Activity() {
		
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public ActivityType getTyp() {
		return typ;
	}

	public void setTyp(ActivityType typ) {
		this.typ = typ;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
