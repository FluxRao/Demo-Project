package com.rampo.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.rampo.entity.User;
import com.rampo.repository.UserRepository;

@Configuration
public class Validator {

	@Autowired
	private UserRepository userRepo;

	public boolean validatePassword(String password) {

		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

		Pattern p = Pattern.compile(regex);
		if (password == null) {
			return false;
		}
		Matcher m = p.matcher(password);
		return m.matches();
	}

	public boolean validateUsername(String name) throws Exception {
		String regex = "[A-Za-z]\\w{5,29}";

		Pattern p = Pattern.compile(regex);
		if (name == null) {
			return false;
		}
		Matcher m = p.matcher(name);
		if (!m.matches()) {
			throw new Exception(Constants.user_username_not_valid);
		}
		Optional<User> user = userRepo.findById(name);
		if (user.isPresent()) {
			throw new Exception(Constants.username_already_taken);
		}
		return true;
	}

	public boolean validateContactNo(String contactNo) throws Exception {

		if (contactNo.length() != 10) {
			throw new Exception(Constants.user_mobile_invalid_length);
		}
		String regex = "^[0-9]{10}$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(contactNo);
		if (!m.matches()) {
			throw new Exception(Constants.user_mobile_invalid_input);
		}

		Optional<User> user = userRepo.findByContactNo(contactNo);
		if (user.isPresent()) {
			throw new Exception(Constants.user_mobile_already_present);
		}
		return true;
	}

	public boolean validateEmail(String email) throws Exception {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new Exception(Constants.user_email_invalid_input);
		}
		Optional<User> user = userRepo.findByEmail(email);
		if (user.isPresent()) {
			throw new Exception(Constants.user_email_already_present);
		}
		return true;
	}
}
