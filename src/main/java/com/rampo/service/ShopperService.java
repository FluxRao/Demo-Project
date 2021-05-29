package com.rampo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rampo.entity.Roles;
import com.rampo.entity.User;
import com.rampo.entity.UserAuthority;
import com.rampo.model.UserDTO;
import com.rampo.model.input.LoginInput;
import com.rampo.model.input.UserInput;
import com.rampo.repository.RolesRepository;
import com.rampo.repository.UserAuthorityRepository;
import com.rampo.repository.UserRepository;
import com.rampo.util.Constants;
import com.rampo.util.ObjectMapper;
import com.rampo.util.Validator;

@Service
public class ShopperService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RolesRepository rolesRepo;

	@Autowired
	private UserAuthorityRepository authorityRepo;

	@Autowired
	private Validator validator;

	public User getShopperDetails(String userName) throws Exception {

		Optional<User> userOptional = userRepo.findById(userName);
		if (userOptional.isPresent()) {

			User user = userOptional.get();
			UserAuthority authority = authorityRepo.findByUser_UserNameAndIsActiveTrue(user.getUserName());
			if (authority.getUserRole().getRole().equalsIgnoreCase(Constants.role_shopper)) {
				return ObjectMapper.map(userOptional.get(), UserDTO.class);
			}
		}
		return null;
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

	public String login(LoginInput input) throws Exception {

		Optional<User> savedUser = userRepo.findById(input.getUserName());
		if (savedUser.isPresent()) {
			if (input.getUserName().equals(savedUser.get().getUserName())
					&& input.getPassword().equals(savedUser.get().getPassword())) {

				if (authorityRepo.findByUser_UserNameAndIsActiveTrue(savedUser.get().getUserName()).getUserRole()
						.getRole().equalsIgnoreCase(Constants.role_shopper)) {
					return Constants.login_success;
				}
			}
		}
		throw new Exception(Constants.login_bad_credentials);
	}
}
