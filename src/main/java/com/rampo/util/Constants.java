package com.rampo.util;

public class Constants {

//	ShopController constants
	public static String shop_save_failed = "Could not process request. Please try again.";
	public static String shop_save_success = "Data saved successfully";
	public static String shop_no_record_found = "";

//	UserController constants
	public static String user_usename_already_present = "Username already exist !";
	public static String user_mobile_already_present = "Contact number already exist !";
	public static String user_mobile_invalid_length = "Contact number should have 10 digits and should only contain digits";
	public static String user_mobile_invalid_input = "Contact number should only contain digits";
	public static String user_email_invalid_input = "Invalid Email Id";
	public static String user_email_already_present = "Email Id already exist";
	public static String user_save_success = "User added successfully.";
	public static String user_username_not_valid = "Username should be 8-20 in length and should only contain alphabets, numbers, underscore(_) and dot(.) and should not contain spaces.\nUnderscore and dot can't be at the end or start of a username (e.g _username / username_ / .username / username.)\nUnderscore or dot can't be used multiple times in a row (e.g user__name / user..name)\nUnderscore and dot can't be next to each other (e.g user_.name).";
	public static String user_password_not_valid = "Password should be 8-20 charcters in length and should contain atlease one uppercase, one lowercase character,"
			+ " one digit and one special character from these(!@#$%&*()-+=^)";

//	user roles constants
	public static String role_admin = "admin";
	public static String role_user = "user";

//	username constants
	public static String username_already_taken = "username already taken.";
	public static String username_available = "username available";

//	login constants
	public static String login_bad_credentials = "username or password is incorrect";
	public static String login_success = "successfully logged in.";

// 	search constants
	public static String search_no_shop = "no shop found.";
	public static String search_no_brand = "no brand found.";

// 	CategoryController constants
	public static String category_no_record_found = "No category data available";

}
