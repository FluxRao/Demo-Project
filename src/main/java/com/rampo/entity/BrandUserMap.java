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
@Table(name = "brandusermap")
public class BrandUserMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brandusermapid")
	private long brandUserMapId;

	@ManyToOne
	@JoinColumn(name = "itemid", nullable = false)
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User user;

	@Column(name = "didlike", nullable = false)
	private boolean didLike;

	@Column(name = "didrate", nullable = false)
	private boolean didRate;

	@Column(name = "rating", nullable = true)
	private long rating;

	@Column(name = "didview", nullable = false)
	private boolean didView;
}
