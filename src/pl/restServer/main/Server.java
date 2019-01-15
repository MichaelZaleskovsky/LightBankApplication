package pl.restServer.main;

import io.javalin.Javalin;

public class Server {

	public static void main(String[] args) {

		Javalin app = Javalin.create().enableCorsForOrigin("*").port(7070).start();
		app.error(404, ctx -> ctx.result("Error 404 Page not found"));
		app.get("/", ctx -> ctx.result("SERVER Start sccessfuly"));
		
		new WebControllers(app).register();
	}

}
