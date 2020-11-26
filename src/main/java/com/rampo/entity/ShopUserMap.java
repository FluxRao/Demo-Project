package com.rampo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "shopusermap")
public class ShopUserMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopusermapid")
	private long shopUserMapId;

	@ManyToOne
	@JoinColumn(name = "shopid", nullable = false)
	private Shop shop;

	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User user;

	@Column(name = "didlike", nullable = false)
	private boolean didLike;

	@Column(name = "didrate", nullable = false)
	private boolean didRate;
}
