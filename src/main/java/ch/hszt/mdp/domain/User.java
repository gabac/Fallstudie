package ch.hszt.mdp.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import ch.hszt.mdp.validation.PasswordsEqual;
import ch.hszt.mdp.validation.UniqueEmail;

@Entity
@Table(name = "users")
@PasswordsEqual(message = "Passwords are not equal.")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Size(max = 255)
	@UniqueEmail(message = "There is already a user with this e-mail address.")
	@Pattern(message = "Please provide a valid E-Mail address", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
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
	private Date birthdate;

	@NotNull
	@Size(min = 2, max = 255)
	private String city;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] photo;

	public User() {

	}

	public User(String email, String password, String prename, String surname) {
		this.email = email;
		this.password = password;
		this.repeat = password;
		this.prename = prename;
		this.surname = surname;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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
}
