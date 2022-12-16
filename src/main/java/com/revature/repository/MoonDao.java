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

	public Moon getMoonByName(String username, String moonName) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, moonName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Moon moon = new Moon();
			moon.setId(rs.getInt(1));
			moon.setName(rs.getString(2));
			moon.setMyPlanetId(rs.getInt(3));;
			return moon;
		} catch (SQLException e) {
			System.out.println(e);
			return new Moon();
		}
	}

	public Moon getMoonById(String username, int moonId) {
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
		} catch (SQLException e) {
			System.out.println(e);
			return new Moon();
		}
	}

	public Moon createMoon(String username, Moon m) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "insert into moons values (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, m.getId());
			ps.setString(2, m.getName());
			ps.setInt(3, m.getMyPlanetId());
			ResultSet rs = ps.executeQuery();
			Moon newMoon = new Moon();
			rs.next();
			newMoon.setId(rs.getInt(1));
			newMoon.setName(rs.getString(2));
			newMoon.setMyPlanetId(rs.getInt(3));
			return newMoon;
		} catch (SQLException e) {
			System.out.println(e);
			return new Moon();
		}
	}

	public void deleteMoonById(int moonId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e);
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
