package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lms.dao.DashboardDao;
import com.lms.pojo.DashboardStats;
import com.lms.util.DbUtil;

public class DashboardDaoImpl implements DashboardDao {

	private final String TOTAL_BOOK_QUERY = "Select count(*) from books";
	private final String TOTAL_USERS_QUERY = "Select count(*) from users where role = 'READER'";
	private final String BOOK_STATUS_QUERY = "Select count(*) from book_issued where status = ?";
	
	private int fetchCount(String sql, String status) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count = 0;
		try {
			
			conn = DbUtil.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			
			if (status != null) {
				preparedStatement.setString(1, status);
			}
			
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				count =  rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	private int fetchCount(String sql) {
		return fetchCount(sql, null);
	}
	
	
	@Override
	public DashboardStats fetchDashboardStats() {
		DashboardStats dashboardStats = new DashboardStats();
		dashboardStats.setTotalBooks(fetchCount(TOTAL_BOOK_QUERY));
		dashboardStats.setTotalUsers(fetchCount(TOTAL_USERS_QUERY));
		dashboardStats.setBooksAssigned(fetchCount(BOOK_STATUS_QUERY, "ISSUED"));
		dashboardStats.setBooksReturned(fetchCount(BOOK_STATUS_QUERY, "RETURN"));

		return dashboardStats;
	}

}
