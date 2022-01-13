package com.example.SpringBootLoginRegistrationLogoutApplication.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringBootLoginRegistrationLogoutApplication.dao.UserDao;
import com.example.SpringBootLoginRegistrationLogoutApplication.model.UserModel;

@Controller
public class UserManagementController {

	@Autowired
	public UserDao userDao;

	@RequestMapping("/")
	public String welcome() {
		return "user-list";
	}

	@RequestMapping("/new")
	public String gotToNewUserPage() {
		return "new-user";
	}

	@RequestMapping("/insert")
	public void insert(UserModel userModel, HttpServletResponse response) throws IOException {
		userDao.save(userModel);
		response.sendRedirect("list");
	}

	@RequestMapping("/list")
	public String getAllUsers(Model model) {
		List<UserModel> allUsers = userDao.findAll();
		model.addAttribute("listUser", allUsers);
		return "user-list";
	}

	
	@RequestMapping("/edit")
	public String editExistingUser(HttpServletRequest request, Model model) {
		String strId = request.getParameter("id");
		long id = Long.parseLong(strId);
		UserModel findByid = userDao.findByid(id);
		model.addAttribute("user", findByid);
		return "new-user";
	}

	@RequestMapping("/update")
	public String updateExistingUser(UserModel userModel, Model model, HttpServletResponse response) {
		userDao.updateUser(userModel.getName(), userModel.getEmail(), userModel.getCountry(), userModel.getAge(),
				userModel.getId());
		
		List<UserModel> allUsers = userDao.findAll();
		model.addAttribute("listUser", allUsers);
		return "user-list";
	}
	
	@RequestMapping("/delete")
	public String deleteUser(HttpServletRequest request, Model model, HttpServletResponse response) {
		String strId = request.getParameter("id");
		long id = Long.parseLong(strId);
		userDao.deleteById(id);
		List<UserModel> allUsers = userDao.findAll();
		model.addAttribute("listUser", allUsers);
		return "user-list";
	}
}
