package com.packages.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.DaoLayer.userDao;
import com.packages.Model.tasks;
import com.packages.Model.users;


@Service
public class userServiceImpl  implements userService{

	
	 @Autowired
	 private userDao userdao;
	 
	@Override
	public void saveUser(users userdetail) {
		
		// TODO Auto-generated method stub
		
		userdao.saveUser(userdetail);
		
	}
	
	
	
	
	

	 public Long CheckEmialExistOrNot(String email) {
		 return userdao.CheckEmailExistOrNot(email);
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public Boolean CheckCredentialForLogin(String email,String password)
	 {
		String databasePassword= userdao.checkCredentailForLogin(email);
		
		if(password.equals(databasePassword))
		{
			return true;
		}else {
			return false;
		}
		
	 }
	 
	 
	 
	 
	 

	@Override
	public void saveUserTask(tasks usertask) {
		// TODO Auto-generated method stub
		userdao.saveUserTask(usertask);
	}
	
	
	
	
	
	
	
	public int getUserId_ThroughEmail(String email)
	{
		return userdao.getUserId_ThroughEmail(email);
	}
	
	
	
	
	
	
	

	@Override
	public List<tasks> getUserAllToDOList(int userid) {
		
		
		return userdao.getUserAllToDOList(userid);
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	
	@Override
	public boolean editToDOList(int id, String title, String description) {
		// TODO Auto-generated method stub
		
		return userdao.editToDoList(id,title,description);
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean deleteItemFromoDoList(int id) {
		// TODO Auto-generated method stub
		return userdao.deleteItemFromTODoList(id);
	}
}
