package com.rampo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
@Entity
@Table(name = "shopitemconfig")
public class ShopItemConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long configId;

	@Value("false")
	@Column(name = "flgactive", nullable = false)
	private boolean isActive;

	@ManyToOne
	@JoinColumn(name = "shopid", nullable = false)
	private Shop shop;

	@ManyToOne
	@JoinColumn(name = "itemid", nullable = false)
	private Item item;

}