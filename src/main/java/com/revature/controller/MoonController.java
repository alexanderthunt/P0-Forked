package com.revature.controller;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Moon;
import com.revature.models.User;
import com.revature.service.MoonService;

import io.javalin.http.Context;

public class MoonController {

	private MoonService mService = new MoonService();

	public void getAllMoons(Context ctx) throws SQLException {

		ctx.json(mService.getAllMoons()).status(200);
	}

	public void getMoonByName(Context ctx) {
		User u = ctx.sessionAttribute("user");
		String moonName = ctx.pathParam("name");
		try {
			Moon m = mService.getMoonByName(u.getUsername(), moonName);
			ctx.json(m).status(200);
		} catch (Exception e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Moon with that name does not exist.").status(400);
		}
	}

	public void getMoonById(Context ctx) {
		User u = ctx.sessionAttribute("user");
		int moonId = ctx.pathParamAsClass("id", Integer.class).get();
		try {
			Moon m = mService.getMoonById(u.getUsername(), moonId);
			ctx.json(m).status(200);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Moon with that Id does not exist").status(400);
		}
	}

	public void createMoon(Context ctx) {
		Moon m = ctx.bodyAsClass(Moon.class);
		User u = ctx.sessionAttribute("user");
		try {
			Moon outGoingMoon = mService.createMoon(u.getUsername(), m);
			ctx.json(outGoingMoon).status(201);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Planet id does not exist.").status(400);
		}
	}

	public void deleteMoon(Context ctx) {
		int moonId = ctx.pathParamAsClass("id", Integer.class).get();
		try {
			mService.deleteMoonById(moonId);
			ctx.json("Moon successfully deleted").status(202);
		} catch (SQLException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Index not in correct format.").status(400);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("Index out of bounds.").status(400);
		}
	}

	public void getPlanetMoons(Context ctx) throws SQLException {
		int planetId = ctx.pathParamAsClass("id", Integer.class).get();
		List<Moon> moonList = mService.getMoonsFromPlanet(planetId);
		boolean noMoons = false;
		try {
			moonList.get(0);
		} catch (IndexOutOfBoundsException e) {
			noMoons = true;
		}
		if (noMoons) {
			ctx.json("There are no moons for this planet, or there is no planet at this index.").status(300);
		} else {
			ctx.json(moonList).status(200);
		}
	}
}
