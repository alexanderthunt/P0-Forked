package com.revature.controller;

import java.sql.SQLException;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;

import io.javalin.http.Context;

public class AuthenticateController {

	private UserService userService = new UserService();

	public void authenticate(Context ctx) {

		UsernamePasswordAuthentication loginRequest = ctx.bodyAsClass(UsernamePasswordAuthentication.class);

		User u = userService.getUserByUsername(loginRequest.getUsername());
		try {
			if (u != null && u.getPassword().equals(loginRequest.getPassword())) {
				ctx.sessionAttribute("user", u);
				ctx.status(200);
			} 
			else {
				ctx.status(400);
			}
		} catch (NullPointerException e) {
			System.out.println("ERROR CAUGHT: " + e);
			ctx.json("There is no user with that username.").status(400);
		}
	}

	public void register(Context ctx) { // this method does not handle an error. Example: create account with taken username. Don't handle this problem.
		UsernamePasswordAuthentication registerRequest = ctx.bodyAsClass(UsernamePasswordAuthentication.class);
		try {
			User newUser = userService.register(registerRequest);
			ctx.json(newUser).status(201);
		} catch (SQLException e) {
			ctx.status(400);
		}
	}

	public void invalidateSession(Context ctx) {
		ctx.req().getSession().invalidate();
	}

	public boolean verifySession(Context ctx) {
		return ctx.sessionAttribute("user") != null;
	}
}
