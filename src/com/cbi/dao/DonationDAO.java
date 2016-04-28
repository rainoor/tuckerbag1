package com.cbi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cbi.entity.Donation;
import com.cbi.util.DataBaseTool;

/**
 * the data access object of donation
 * @author Hu
 *
 */
public class DonationDAO {
	
	/**
	 * store the donation information into database
	 * @param d
	 * @return
	 */
	public boolean insertDonationRecord(Donation d){
		boolean result = false;
		Connection conn = DataBaseTool.getConnection();;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO donationrecord(fullName, commGardenName, email, street, " +
							"suburb, postCode, product, amount, unit, data, timeRange, status)" +
						"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, d.getFullName());
			stmt.setString(2, d.getCommGardenName());
			stmt.setString(3, d.getEmail());
			stmt.setString(4, d.getStreet());
			stmt.setString(5, d.getSuburb());
			stmt.setString(6, d.getPostCode());
			stmt.setString(7, d.getProduct());
			stmt.setDouble(8, d.getAmount());
			stmt.setString(9, d.getUnit());
			stmt.setString(10, d.getDate());
			stmt.setString(11, d.getTimeRange());
			stmt.setString(12, d.getStatus());
			
			result = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DataBaseTool.closeConnection(stmt, rs);
		}
		
		return result;
	}
	
	public List<Donation> getAllDonation(){
		ArrayList<Donation> dlist = new ArrayList<Donation>();
		
		Connection conn = DataBaseTool.getConnection();;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM donationrecord";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				dlist.add(new Donation(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getString(8), rs.getDouble(9), 
						rs.getString(10), rs.getString(11), rs.getString(12), 
						rs.getString(13)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseTool.closeConnection(stmt, rs);
		}
		
		return dlist;
	}

}
