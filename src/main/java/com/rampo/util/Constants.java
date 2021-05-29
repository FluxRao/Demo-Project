package com.rampo.util;

public class Constants {

	public static String some_error_occured = "Some error occured. Please try again later.";

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
	public static String user_save_success = "User added successfully";
	public static String user_username_not_valid = "Username should be 6-30 in length and should only contain alphabets, numbers and underscore(_) and should not start with digit.";
	public static String user_password_not_valid = "Password should be 8-20 charcters in length and should contain atlease one uppercase, one lowercase character,"
			+ " one digit and one special character from these(!@#$%&*()-+=^)";
	public static String user_new_password_not_valid = "New password should be 8-20 charcters in length and should contain atlease one uppercase, one lowercase character,"
			+ " one digit and one special character from these(!@#$%&*()-+=^)";
	public static String user_could_not_update_data = "Could not update user information";
	public static String user_data_updated_successfully = "User data updated successfully";
	public static String user_password_updated_successfully = "Password updated successfully";
	public static String user_provide_correct_password = "Please provide correct password";
	public static String please_provide_username = "Please provide userName";
	public static String please_provide_correct_email = "Please provide correct email";

//	user roles constants
	public static String role_admin = "admin";
	public static String role_user = "user";
	public static String role_shopper = "shopper";

//	username constants
	public static String username_already_taken = "username already taken.";
	public static String username_available = "username available";

//	login constants
	public static String login_bad_credentials = "username or password is incorrect";
	public static String login_success = "successfully logged in";
	public static String please_login_to_continue = "Please login to continue";

// 	search constants
	public static String search_no_shop = "no shop found";
	public static String search_no_brand = "no brand found";

// 	CategoryController constants
	public static String category_no_record_found = "No category data available";

// 	Data constants
	public static String data_fetch_success = "Data fetched successfully";

//	Like constants
	public static String liked = "Liked";

//	Rating constants
	public static String rated = "Rated";

//	Item controller constants
	public static String item_save_failed = "Could not save data. Please try again.";
	public static String item_save_success = "Item added successfully.";

//	Offer controller constants
	public static String offer_save_success = "Offer added successfully";
}
