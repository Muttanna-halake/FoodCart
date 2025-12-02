package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oId;

	private String oName;
	private double oPrice;
	private int oQuantity;
	private Date orderDate;
	private double totalAmmout;

	// ⭐ NEW FIELD FOR TRACKING FEATURE
	private String status; // ORDERED, PACKED, SHIPPED, DELIVERED

	// ⭐ NEW FIELD TO STORE PRODUCT IMAGE NAME
	private String image;

	@ManyToOne
	@JoinColumn(name = "user_u_id")
	private User user;

	// ------------------ GETTERS & SETTERS --------------------

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public double getoPrice() {
		return oPrice;
	}

	public void setoPrice(double oPrice) {
		this.oPrice = oPrice;
	}

	public int getoQuantity() {
		return oQuantity;
	}

	public void setoQuantity(int oQuantity) {
		this.oQuantity = oQuantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmmout() {
		return totalAmmout;
	}

	public void setTotalAmmout(double totalAmmout) {
		this.totalAmmout = totalAmmout;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// ⭐ NEW GETTER & SETTER FOR IMAGE FIELD
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// ------------------ TOSTRING --------------------
	@Override
	public String toString() {
		return "Orders [oId=" + oId + ", oName=" + oName + ", oPrice=" + oPrice +
				", oQuantity=" + oQuantity + ", orderDate=" + orderDate +
				", totalAmmout=" + totalAmmout + ", status=" + status +
				", image=" + image + ", user=" + user + "]";
	}

	// ⭐ NEW FIELDS
	private Date cancelDate;
	private String cancelReason;

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
}
