package org.amisescalade.controller;

import java.util.List;

import org.amisescalade.entity.User;
import org.amisescalade.entity.UserCategory;
import org.amisescalade.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserControllerImpl implements IUserController {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IInputValidator inputValidator;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public User signUpByDefault(String firstname, String lastname, String username, String password) {

		// TODO check saisie !

		User userSave = new User();

		try {
			userSave = iUserService.registerByDefault(firstname, lastname, username, password);
		} catch (Exception e) {

			this.errorMessage = e.getMessage();
		}
		return userSave;

	}

	public User displayUser(Long id) {

		User userFind = new User();

		try {
			userFind = iUserService.getUser(id);
		} catch (Exception e) {

			this.errorMessage = e.getMessage();
		}
		return userFind;
	}

	public User editUser(User user) {

		// TODO check saisie !

		User userEdit = new User();

		try {
			userEdit = iUserService.edit(user);
		} catch (Exception e) {

			this.errorMessage = e.getMessage();
		}

		return userEdit;
	}

	public Boolean signInUser(String username, String password) {
		
		Boolean loginStatus = false;
		
		try {
			loginStatus = iUserService.sampleLogin(username,password);
		} catch (Exception e) {
			
			this.errorMessage = e.getMessage();
		}
		return loginStatus;

	}

	@Override
	public List<User> displayAllUsers() {
		
		return iUserService.getAllUsers();
	}

	@Override
	public List<User> displayAllUsersByUserCategory(UserCategory userCategory) {
		
		return iUserService.getUsersByCategory(userCategory);
	}

}