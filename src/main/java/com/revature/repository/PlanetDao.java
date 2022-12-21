package com.revature.repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {

	public static Logger logger = LoggerFactory.getLogger(PlanetDao.class);

	public List<Planet> getAllPlanets() throws SQLException {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from planets";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<Planet> planets = new ArrayList<>();
			while (rs.next()) {
				Planet planet = new Planet();
				planet.setId(rs.getInt(1));
				planet.setName(rs.getString(2));
				planet.setOwnerId(rs.getInt(3));
				planets.add(planet);
			}
			return planets;
		}
	}

	public Planet getPlanetByName(String owner, String planetName) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from planets where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, planetName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Planet planet = new Planet();
			planet.setId(rs.getInt(1));
			planet.setName(rs.getString(2));
			planet.setOwnerId(rs.getInt(3));
			return planet;
		} catch (SQLException e) {
			System.out.println(e);
			return new Planet();
		}
	}

	public Planet getPlanetById(String username, int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "select * from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Planet planet = new Planet();
			planet.setId(rs.getInt(1));
			planet.setName(rs.getString(2));
			planet.setOwnerId(rs.getInt(3));
			return planet;
		} catch (SQLException e) {
			System.out.println(e);
			return new Planet();
		}
	}

	public Planet createPlanet(String username, Planet planet) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "insert into planets values (default,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, planet.getName());
			ps.setInt(2, planet.getOwnerId());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			Planet newPlanet = new Planet();
			rs.next();
			int newId = rs.getInt("id");
			newPlanet.setId(newId);
			newPlanet.setName(planet.getName());
			newPlanet.setOwnerId(planet.getOwnerId());
			return newPlanet;
		} catch (SQLException e) {
			System.out.println(e);
			return new Planet();
		}
	}

	public void deletePlanetById(int planetId) {
		try (Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e); // good spot to add some logging

		}
	}

	public static void main(String[] args) {

		/*
		 * PlanetDao planetDao = new PlanetDao();
		 * try {
		 * System.out.println(planetDao.getAllPlanets());
		 * } catch (SQLException e) {
		 * System.out.println(e.getMessage());
		 * }
		 */

		/*
		 * PlanetDao planetDao = new PlanetDao();
		 * planetDao.deletePlanetById();
		 */

	}
}
