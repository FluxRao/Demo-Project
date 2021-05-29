package com.rampo.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopInputForNewShopper {

	private String shopName;
	private String shopDetail;
	private String address;
	private String shopCity;
	private String shopState;
	private String shopCountry;
	private String pincode;
	private String shopOwner;
	private String contactNo;
	private String eMail;
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;
	private String userName;
	private String password;
}
