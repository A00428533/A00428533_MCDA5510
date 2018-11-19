package com.smu.dto;

import java.util.Date;

public class TransactionDTO {
	public TransactionDTO() {
	}

	private int ID = 0;
	private String NameOnCard = null;
	private String CardNumber = null;
	private String ExpDate = null;
	private double UnitPrice = 0.0D;
	private int qty = 0;
	private double totalPrice = 0.0D;
	private Date createdOn = null;
	private String createdBy = null;
	private String creditCardType = null;

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNameOnCard() {
		return NameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		NameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	public String getExpDate() {
		return ExpDate;
	}

	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}

	public double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String toString() {
		String results = new String();

		results = "[NameOnCard: " + NameOnCard + ",CardNumber: " + CardNumber + ",Expiry Date:" + ExpDate
				+ ",Unit Price:" + UnitPrice + ",Quantity:" + qty + ",Total Price:" + totalPrice + ",Create On:"
				+ createdOn + ",Created By:" + createdBy + ",Credit Card Type:" + creditCardType + "]";
		return results;
	}
}
