package ch.hszt.mdp.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "friendship")
public class Friendship implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "primary_user", updatable = false, insertable = false)
	private User primaryUser;

	@Id
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
}
