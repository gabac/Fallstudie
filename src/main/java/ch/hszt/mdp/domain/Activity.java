package ch.hszt.mdp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ch.hszt.mdp.util.ISODateSerializer;

@Entity
@Table(name = "activities")
public class Activity {

	public enum ActivityType {
		STATUS("status"), PROFILE("profile"), FRIEND("friend"), LIKE("like"), DISLIKE("dislike");

		private final String value;

		ActivityType(String value) {
			this.value = value;
		}

		public static ActivityType fromValue(String value) {
			if (value != null) {
				for (ActivityType typ : values()) {
					if (typ.value.equals(value)) {
						return typ;
					}
				}
			}

			return getDefault();
		}

		public String toValue() {
			return value;
		}

		public static ActivityType getDefault() {
			return STATUS;
		}
	}

	@Id
	@GeneratedValue
	private Integer activity_id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "typ")
	private String typValue;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime time;

	@NotNull
	private String privacy;

	@Transient
	public ActivityType getActivityType() {
		return ActivityType.fromValue(typValue);
	}

	public void setTyp(ActivityType typ) {
		this.typValue = typ.toValue();
	}

	private String content;

	public Activity() {

	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user_id) {
		this.user = user_id;
	}

	@JsonSerialize(using = ISODateSerializer.class)
	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
}
