package com.rampo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "offertypemaster")
public class OfferType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offertypeid")
	private long typeId;

	@Column(name = "offertype", nullable = false, length = 30, unique = true)
	private String offerType;

	@Column(name = "offerdetail", nullable = false, length = 1000)
	private String offerDetail;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;
	
	@Column(name = "createdon", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "modifiedon", nullable = true)
	private Date modifiedOn;
}
