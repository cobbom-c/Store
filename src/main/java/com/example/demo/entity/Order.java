package com.example.demo.entity;

import java.util.Date;

public class Order extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1468303385169400222L;
	Integer oid;
	Integer uid;
	String name;
	String phone;
	String address;
	Long totalPrice;
	Integer state;
	Date orderTime;
	Date payTime;
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", totalPrice=" + totalPrice + ", state=" + state + ", orderTime=" + orderTime + ", payTime="
				+ payTime + "]";
	}

}
