package com.revature.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {

	public List<Moon> getAllMoons() throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<Moon> moons = new ArrayList<>();
			while (rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moon.setMyPlanetId(rs.getInt(3));
				moons.add(moon);
			}
			return moons;
		}
	}

	public Moon getMoonByName(String username, String moonName) throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(3));
			;
			return moon;
		}
	}

	public Moon getMoonById(String username, int moonId) throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(3));
			return moon;
		}
	}

	public Moon createMoon(String username, Moon moon) throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "insert into moons values (default,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, moon.getName());
			ps.setInt(2, moon.getMyPlanetId());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			Moon newMoon = new Moon();
			rs.next();
			int newId = rs.getInt("id");
			newMoon.setId(newId);
			newMoon.setName(moon.getName());
			newMoon.setMyPlanetId(moon.getMyPlanetId());
			return newMoon;
		}
	}

	public void deleteMoonById(int moonId) throws SQLException, IndexOutOfBoundsException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
			if (rowsAffected == 0) {
				throw new IndexOutOfBoundsException();
			}
		} 
	}

	public List<Moon> getMoonsFromPlanet(int planetId) throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where myplanetid = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			List<Moon> moons = new ArrayList<>();
			while (rs.next()) {
				Moon moon = new Moon();
				moon.setId(rs.getInt(1));
				moon.setName(rs.getString(2));
				moon.setMyPlanetId(rs.getInt(3));
				moons.add(moon);
			}
			return moons;
		}
	}
}
