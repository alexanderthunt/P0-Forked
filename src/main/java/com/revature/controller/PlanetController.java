package com.revature.controller;

import java.sql.SQLException;
import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.service.PlanetService;

import io.javalin.http.Context;

public class PlanetController {

	private PlanetService pService = new PlanetService();

	public void getAllPlanets(Context ctx) throws SQLException {
		ctx.json(pService.getAllPlanets()).status(200);
	}

	public void getPlanetByName(Context ctx) {
		User u = ctx.sessionAttribute("user");
		String planetName = ctx.pathParam("name");
		try {
			Planet p = pService.getPlanetByName(u.getUsername(), planetName);
			ctx.json(p).status(200);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("There is no planet with that name.").status(400);
		}
	}

	public void getPlanetByID(Context ctx) {
		User u = ctx.sessionAttribute("user");
		int planetId = ctx.pathParamAsClass("id", Integer.class).get();
		try {
			Planet p = pService.getPlanetById(u.getUsername(), planetId);
			ctx.json(p).status(200);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("There are no planets with that Id.").status(400);
		}
	}

	public void createPlanet(Context ctx) {
		Planet planetToBeCreated = ctx.bodyAsClass(Planet.class);
		User u = ctx.sessionAttribute("user");
		try {
			Planet createdPlanet = pService.createPlanet(u.getUsername(), planetToBeCreated);
			ctx.json(createdPlanet).status(201);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Owner id does not exist.").status(400);
		}
	}

	public void deletePlanet(Context ctx) {
		int planetId = ctx.pathParamAsClass("id", Integer.class).get();
		
		try {
			pService.deletePlanetById(planetId);
			ctx.json("Planet successfully deleted").status(202);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Index not in correct format.").status(400);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Index out of bounds.").status(400);
		}
	}
}
