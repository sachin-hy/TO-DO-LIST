package com.packages.ServiceLayer;

import java.util.List;

import com.packages.Model.tasks;
import com.packages.Model.users;

public interface userService {
 
	 public void saveUser(users userdetail);
	 public Long CheckEmialExistOrNot(String email);
	 public Boolean CheckCredentialForLogin(String email,String password);
	 public void saveUserTask(tasks usertask);
	 
	 public int getUserId_ThroughEmail(String email);
	public List<tasks> getUserAllToDOList(int userid);
	public boolean editToDOList(int id, String title, String description);
	public boolean deleteItemFromoDoList(int id);
}
