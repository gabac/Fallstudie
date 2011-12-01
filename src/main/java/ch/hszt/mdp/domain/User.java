package ch.hszt.mdp.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import ch.hszt.mdp.validation.PasswordsEqual;
import ch.hszt.mdp.validation.UniqueEmail;

/**
 * This Class is used as a resource. It uses hibernate to saves everything inside of the table "User".
 * 
 * @see <a
 *      href="http://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Additional_columns_in_join_table.">Many-To-Many
 *      with additional columns</a>
 * 
 * @author Cyril Gabathuler
 * 
 * @param id
 *            integer for a exact identifiation of every user.
 * 
 * @param email
 *            string with the e-Mail of the user (with format check)
 * 
 * @param password
 *            password string (MD5 encrypted)
 * 
 * @param repeat
 *            password string (MD5 encrypted) uses for password validation for registration
 * 
 * @param prename
 *            prename of the user
 * 
 * @param surname
 *            surname string of the user
 * 
 * @param birthday
 *            date field that defines the birthday of the user
 * 
 * @param city
 *            string where the user lives
 * 
 * @param photo
 *            photo binary of the user
 */
@Entity
@Table(name = "users")
@PasswordsEqual(message = "passwords are not equal")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Size(max = 255)
	@UniqueEmail(message = "there is already a user with this e-mail address")
	@Pattern(message = "please provide a valid E-Mail address", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;

	@NotNull
	@Size(min = 1, max = 255)
	private String password;

	@NotNull
	@Size(min = 1, max = 255)
	@Transient
	private String repeat;

	@NotNull
	@Size(min = 2, max = 255)
	private String prename;

	@NotNull
	@Size(min = 2, max = 255)
	private String surname;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime birthdate;

	@NotNull
	@Size(min = 2, max = 255)
	private String city;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] photo;

	@OneToMany(mappedBy = "primaryUser")
	private List<Friendship> friendships;

	@OneToMany(mappedBy = "user_id")
	private List<Activity> activities;

	public User() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public DateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(DateTime birthdate) {
		this.birthdate = birthdate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Friendship> getFriendships() {
		return friendships;
	}

	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public void addSecondaryUser(User primaryUser, boolean accepted) {
		Friendship friendship = new Friendship();
		friendship.setPrimaryUser(primaryUser);
		friendship.setSecondaryUser(this);
		friendship.setPrimary_user(primaryUser.getId());
		friendship.setSecondary_user(this.getId());
		friendship.setAccepted(accepted ? 1 : 0);

		this.friendships.add(friendship);
	}
}
