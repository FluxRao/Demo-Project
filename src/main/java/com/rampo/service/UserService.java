package com.rampo.service;

import java.util.Date;
import java.util.Optional;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rampo.entity.Roles;
import com.rampo.entity.User;
import com.rampo.entity.UserAuthority;
import com.rampo.model.UserDTO;
import com.rampo.model.input.ChangePasswordInput;
import com.rampo.model.input.EditUserInput;
import com.rampo.model.input.ForgotPasswordInput;
import com.rampo.model.input.LoginInput;
import com.rampo.model.input.UserInput;
import com.rampo.repository.RolesRepository;
import com.rampo.repository.UserAuthorityRepository;
import com.rampo.repository.UserRepository;
import com.rampo.util.Constants;
import com.rampo.util.ObjectMapper;
import com.rampo.util.Validator;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RolesRepository rolesRepo;

	@Autowired
	private UserAuthorityRepository authorityRepo;

	@Autowired
	private Validator validator;

	@Autowired
	private JavaMailSender mailSender;

	public String registerUser(UserInput userInput) throws Exception {

		boolean usernameFlag = validator.validateUsername(userInput.getUserName());
		boolean passwordFlag = validator.validatePassword(userInput.getPassword());
		boolean contactNoFlag = validator.validateContactNo(userInput.getContactNo());
		boolean emailFlag = validator.validateEmail(userInput.getEmail());

		if (usernameFlag == false) {
			throw new Exception(Constants.user_username_not_valid);
		}

		if (passwordFlag == false) {
			throw new Exception(Constants.user_password_not_valid);
		}

		if (contactNoFlag && emailFlag && passwordFlag && usernameFlag) {
			Optional<User> user = userRepo.findById(userInput.getUserName());

			if (!user.isPresent()) {
				Roles role = rolesRepo.findById(Constants.role_user).get();

				User user1 = ObjectMapper.map(userInput, User.class);
				user1.setCreatedOn(new Date());
				user1.setActive(true);
				User savedUser = userRepo.save(user1);

				UserAuthority authority = new UserAuthority();
				authority.setUser(savedUser);
				authority.setUserRole(role);
				authority.setCreatedOn(new Date());

				authorityRepo.save(authority);

			} else {
				throw new Exception(Constants.user_usename_already_present);
			}
		}
		return Constants.user_save_success;
	}

	public User registerShopper(UserInput userInput) throws Exception {

		boolean usernameFlag = validator.validateUsername(userInput.getUserName());
		boolean passwordFlag = validator.validatePassword(userInput.getPassword());
		boolean contactNoFlag = validator.validateContactNo(userInput.getContactNo());
		boolean emailFlag = validator.validateEmail(userInput.getEmail());

		if (usernameFlag == false) {
			throw new Exception(Constants.user_username_not_valid);
		}

		if (passwordFlag == false) {
			throw new Exception(Constants.user_password_not_valid);
		}

		if (contactNoFlag && emailFlag && passwordFlag && usernameFlag) {
			Optional<User> user = userRepo.findById(userInput.getUserName());

			if (!user.isPresent()) {
				Roles role = rolesRepo.findById(Constants.role_shopper).get();

				User user1 = ObjectMapper.map(userInput, User.class);
				user1.setCreatedOn(new Date());
				user1.setActive(true);
				User savedUser = userRepo.save(user1);

				UserAuthority authority = new UserAuthority();
				authority.setActive(true);
				authority.setUser(savedUser);
				authority.setUserRole(role);
				authority.setCreatedOn(new Date());

				authorityRepo.save(authority);

				return savedUser;

			} else {
				throw new Exception(Constants.user_usename_already_present);
			}
		}
		return null;
	}

	public String checkAvailabilityOfUserName(String userName) throws Exception {

		if (userName.length() >= 6) {
			Optional<User> user = userRepo.findById(userName);

			if (!user.isPresent()) {
				return Constants.username_available;

			} else {
				throw new Exception(Constants.username_already_taken);
			}
		}
		return null;
	}

	public String login(LoginInput input) throws Exception {

		Optional<User> savedUser = userRepo.findById(input.getUserName());
		if (savedUser.isPresent()) {
			if (input.getUserName().equals(savedUser.get().getUserName())
					&& input.getPassword().equals(savedUser.get().getPassword())) {
				return Constants.login_success;
			}
		}
		throw new Exception(Constants.login_bad_credentials);
	}

	public User getUserDetails(String userName) throws Exception {

		Optional<User> userOptional = userRepo.findById(userName);
		if (userOptional.isPresent()) {
			return ObjectMapper.map(userOptional.get(), UserDTO.class);
		}
		throw new Exception(Constants.some_error_occured);
	}

	public void editUserDetails(EditUserInput input) throws Exception {

		boolean contactNoFlag = validator.validateContactNo(input.getContactNo());
		boolean emailFlag = validator.validateEmail(input.getEmail());
		if (contactNoFlag && emailFlag) {
			User user = userRepo.findById(input.getUserName()).get();
			user.setEmail(input.getEmail());
			user.setContactNo(input.getContactNo());
			user.setModifiedOn(new Date());
			userRepo.save(user);
		} else {
			throw new Exception(Constants.user_could_not_update_data);
		}
	}

	public void changePassword(ChangePasswordInput input) throws Exception {
		boolean passwordFlag = validator.validatePassword(input.getNewPassword());
		if (!passwordFlag) {
			throw new Exception(Constants.user_new_password_not_valid);
		}
		User user = userRepo.findById(input.getUserName()).get();
		if (user.getPassword().equals(input.getOldPassword())) {

			if (passwordFlag) {
				user.setPassword(input.getNewPassword());
				userRepo.save(user);
			}
		} else {
			throw new Exception(Constants.user_provide_correct_password);
		}
	}

	public void forgotPassword(ForgotPasswordInput input) throws Exception {

		User user = userRepo.findById(input.getUserName()).get();
		if (!input.getEMail().equals(user.getEmail())) {
			throw new Exception(Constants.please_provide_correct_email);
		} else {
			String randomPassword = RandomStringUtils.random(16, true, true);
			user.setPassword(randomPassword);
			userRepo.save(user);

			String subject = "Foffers India Password Reset";
			String body = "Hi " + user.getUserName() + ", " + "Your new password for foffers is: " + randomPassword
					+ ". Please log into your account and change the password in profile menu."
					+ " Please check spam folder if not found in primary mailbox.";
			String to = input.getEMail();

			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body);

			mailSender.send(message);
		}
	}
}