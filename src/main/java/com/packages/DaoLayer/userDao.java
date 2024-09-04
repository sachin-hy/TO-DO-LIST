package com.packages.DaoLayer;

import com.packages.Model.users;

import java.util.List;

import com.packages.Model.tasks;


public interface userDao {
  
	public void saveUser(users userdetail);
	public Long CheckEmailExistOrNot(String email);
	public String checkCredentailForLogin(String email);
	public void saveUserTask(tasks usertask);
	public int getUserId_ThroughEmail(String email);
	public List<tasks> getUserAllToDOList(int userid);
	public boolean editToDoList(int id, String title, String description);
	public boolean deleteItemFromTODoList(int id);
	
}
