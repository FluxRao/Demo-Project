package com.rampo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "shopitemofferconfig")
public class ShopItemOfferConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long configId;

	@ManyToOne
	@JoinColumn(name = "shopitemid", nullable = false)
	private ShopItemConfig shopItemConfig;

	@ManyToOne
	@JoinColumn(name = "offerid", nullable = false)
	private Offer offer;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@CreationTimestamp
	@Column(name = "createdon", nullable = false)
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;

}
