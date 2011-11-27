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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import ch.hszt.mdp.validation.PasswordsEqual;

@Entity
@Table(name = "users")
@PasswordsEqual(message = "Passwords are not equal.")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Size(min = 5, max = 255)
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
