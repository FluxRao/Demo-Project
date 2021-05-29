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

@Data
@Entity
@Table(name = "offerusermap")
public class OfferUserMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offerusermapid")
	private long offerUserMapId;

	@ManyToOne
	@JoinColumn(name = "offerid", nullable = false)
	private Offer offer;

	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User user;

	@Column(name = "didlike", nullable = false)
	private boolean didLike;

	@Column(name = "didrate", nullable = false)
	private boolean didRate;

	@Column(name = "didview", nullable = false)
	private boolean didView;

	@Column(name = "rating", nullable = true)
	private long rating;
}
