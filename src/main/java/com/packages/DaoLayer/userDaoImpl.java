package com.packages.DaoLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.packages.Model.tasks;
import com.packages.Model.users;


@Repository
public class userDaoImpl implements userDao{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	
	
	
	
	
	
	
	@Override
	public void saveUser(users userdetail) {
		// TODO Auto-generated method stub
		 
		String sql="insert into users (id,username,password,email,created_at) values(?,?,?,?,?) ";
		jdbcTemplate.update(sql,userdetail.getId(),userdetail.getUsername(),userdetail.getPassword(),userdetail.getEmail(),userdetail.getCreated_at());
	}

	
	
	
	
	
	
	
	
	
	@Override
	public Long CheckEmailExistOrNot(String email) {
		// TODO Auto-generated method stub
		String sql="select id from users where email = ?";
		
		try {
			Long result = jdbcTemplate.queryForObject(sql,new Object[]{email}, Long.class);
			
			return result;
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String checkCredentailForLogin(String email) {
		// TODO Auto-generated method stub
		
		String sql="select password from users where email = ?";
		
		try {
			String databasePassword=jdbcTemplate.queryForObject(sql,new Object[]{email},String.class);
			return databasePassword;
		}catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void saveUserTask(tasks usertask) {
		// TODO Auto-generated method stub
		String sql="insert into tasks (user_id,title,description,created_at,updated_at) values (?,?,?,?,?)";
		
		jdbcTemplate.update(sql,usertask.getUser_id(),usertask.getTitle(),usertask.getDescription(),usertask.getCreated_at(),usertask.getUpdated_at());
	}

	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public int getUserId_ThroughEmail(String email) {
		// TODO Auto-generated method stub
		
		String sql="select id from users where email = ?";
		
	    try {
	    	int userid_fromdatabase =jdbcTemplate.queryForObject(sql,new Object[] {email},Integer.class);
	    	return userid_fromdatabase;
	    }catch(EmptyResultDataAccessException e)
	    {
	    	return (Integer) null;
	    }
		 
	}

	
	
	
	
	
	
	
	
	
	@Override
	public List<tasks> getUserAllToDOList(int userid) {
	    String sql = "SELECT * FROM tasks WHERE user_id = ?";
	    
	    
		
		@SuppressWarnings("deprecation")
		List<tasks> UserToDoList =jdbcTemplate.query(sql, new Object[] {userid},  new RowMapper<tasks>() {
            
            public tasks mapRow(ResultSet rs, int rowNum) throws SQLException {
                tasks usertasks = new tasks();
                usertasks.setId(rs.getInt("id"));
                usertasks.setUser_id(rs.getInt("user_id"));;
                usertasks.setTitle(rs.getString("title"));
                usertasks.setDescription(rs.getString("description"));
                usertasks.setCreated_at(rs.getString("created_at"));
                usertasks.setUpdated_at(rs.getString("updated_at"));
                return usertasks;
            }
	    });
	    return UserToDoList; // Return the list of tasks
	}

	
	
	
	
	
	
	
	
	@Override
	public boolean editToDoList(int id, String title, String description) {
		// TODO Auto-generated method stub
		  String sql="update tasks set title = ? ,description = ?  where id = ?";
		  try {
			    jdbcTemplate.update(sql,title,description,id);
			    return true;
		  }catch(Exception e)
		  {
			  System.out.println("error in editing to do list");
			  return false;
		  }
	}










	@Override
	public boolean deleteItemFromTODoList(int id) {
		// TODO Auto-generated method stub
		
		String sql="delete from tasks where id = ?";
		
		try {
			
			jdbcTemplate.update(sql,id);
			return true;
			
		}catch(Exception e)
		{
			System.out.println("error in deleting the row content");
			return false;
		}
		
	}


}
