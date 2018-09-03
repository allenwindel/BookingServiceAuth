package pw.io.booker.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Authentication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authenticationID;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	private String token;
	private Date loginDate;

	public int getAuthenticationID() {
		return authenticationID;
	}

	public void setAuthenticationID(int authenticationID) {
		this.authenticationID = authenticationID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

}
