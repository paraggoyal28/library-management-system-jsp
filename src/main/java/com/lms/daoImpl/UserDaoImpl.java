package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.dao.UserDao;
import com.lms.pojo.User;
import com.lms.util.DbUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User checkLogin(String username, String password) {
		
		try {
						
			String sql = "Select * from users where email = ? and password = ?";

			try(Connection conn =  DbUtil.getConnection()) {
			
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
				
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					
					try (ResultSet rs = preparedStatement.executeQuery()) {
					
						if (rs.next()) {
							User user = new User();
							
							user.setFirstName(rs.getString("first_name"));
							user.setLastName(rs.getString("last_name"));
							user.setEmail(rs.getString("email"));
							user.setPassword(rs.getString("password"));
							user.setRole(rs.getString("role"));
							user.setPhone_no(rs.getString("phone_no"));
							user.setAddress(rs.getString("address"));
			
							return user;
						}
					}
				}
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		return null;
	}
	
}
