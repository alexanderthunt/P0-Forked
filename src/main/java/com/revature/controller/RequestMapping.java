package com.revature.controller;

import com.revature.exceptions.NotLoggedInException;

import io.javalin.Javalin;

public class RequestMapping {
	
	private static AuthenticateController authController = new AuthenticateController();
	private static PlanetController planetController = new PlanetController();
	private static MoonController moonController = new MoonController();
	
	public static void setupEndpoints(Javalin app) {
		
		// Authenticate user and create a session for the user, sending username/password in the body as JSON
		app.post("/login", ctx -> authController.authenticate(ctx)); //sends 200

		// Register a new user, sending username/password in the body as JSON
		app.post("/register", ctx -> authController.register(ctx)); //sends 201
		
		// Invalidate session
		app.post("/logout", ctx -> authController.invalidateSession(ctx));//sends 200
		
		// Check for valid sessions
		// Throw a custom exception if a session is not valid
		// This exception will be handled separately
		app.before("/api/*", ctx -> {	 
			if(!authController.verifySession(ctx)) {
				throw new NotLoggedInException();
			}
		});
		
		// Handling the exception when a session doesn't exist
		app.exception(NotLoggedInException.class, (e,ctx) -> {
			ctx.json(e.getMessage()).status(401);
		});
		
		
		// Get all Planets
		app.get("api/planets", ctx -> planetController.getAllPlanets(ctx));//sends 200
		
		// Get a planet with matching name
		app.get("api/planet/{name}", ctx -> planetController.getPlanetByName(ctx));//sends 200
		
		// Get a planet with matching ID
		app.get("api/planet/id/{id}", ctx -> planetController.getPlanetByID(ctx));//sends 200
		
		// Get moons associated with a planet
		app.get("api/planet/{id}/moons", ctx -> moonController.getPlanetMoons(ctx));//sends 200
		
		// Get all moons
		app.get("api/moons", ctx -> moonController.getAllMoons(ctx));//sends 200
		
		// Get a moon with matching name
		app.get("api/moon/{name}", ctx -> moonController.getMoonByName(ctx));//sends 200
		
		// Get a moon with matching ID
		app.get("api/moon/id/{id}", ctx -> moonController.getMoonById(ctx));//sends 200
		

		// Create a new planet, sending the data in the body as JSON
		app.post("api/planet", ctx -> planetController.createPlanet(ctx));//sends 201
		
		// Create a new moon, sending the data in the body as JSON
		app.post("api/moon", ctx -> moonController.createMoon(ctx));//sends 201
		

		// Delete a planet and all of its moons
		app.delete("api/planet/{id}", ctx -> planetController.deletePlanet(ctx));//sends 202
		
		// Delete a moon
		app.delete("api/moon/{id}", ctx -> moonController.deleteMoon(ctx));//sends 202
	}
}
