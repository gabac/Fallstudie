package ch.hszt.mdp.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendship")
public class Friendship implements Serializable {

	@Id
	private Integer primary_user;

	@Id
	private Integer secondary_user;

	@ManyToOne
	@JoinColumn(name = "primary_user", updatable = false, insertable = false)
	private User primaryUser;
	
	@ManyToOne
	@JoinColumn(name = "secondary_user", updatable = false, insertable = false)
	private User secondaryUser;

	private Integer accepted;

	public User getPrimaryUser() {
		return primaryUser;
	}

	public void setPrimaryUser(User primaryUser) {
		this.primaryUser = primaryUser;
	}

	public User getSecondaryUser() {
		return secondaryUser;
	}

	public void setSecondaryUser(User secondaryUser) {
		this.secondaryUser = secondaryUser;
	}

	public Integer getAccepted() {
		return accepted;
	}

	public void setAccepted(Integer accepted) {
		this.accepted = accepted;
	}

	public Integer getPrimary_user() {
		return primary_user;
	}

	public void setPrimary_user(Integer primary_user) {
		this.primary_user = primary_user;
	}

	public Integer getSecondary_user() {
		return secondary_user;
	}

	public void setSecondary_user(Integer secondary_user) {
		this.secondary_user = secondary_user;
	}
}
