package pl.restServer;

import io.javalin.Javalin;

public class Server {

	public static void main(String[] args) {
		AccountManager am = new AccountManager();
//		Javalin app = Javalin.create().port(7070).enableCorsForOrigin("http://localhost:7070").start();
		Javalin app = Javalin.create().port(7070).start();
		app.get("/", ctx -> ctx.result("SERVER Start sccessfuly"));
	    app.get("accounts", ctx -> {									// GET accounts
	    		ctx
	    		.status(200)
	    		.header("Access-Control-Allow-Origin","*")
	    		.json(am.getAllAccounts());      
	    });
	    app.get("account/:accnum", ctx -> {								// GET account
	    	long accNum = Long.parseLong(ctx.pathParam("accnum"));
	    	Account acc = am.getAccount(accNum);
	    	if (acc == null) ctx.status(500).result("Account number "+accNum+" not exist");
	    	else ctx.json(acc);
	    });
	    app.post("transfer", ctx -> {									// POST transfer
   			Transaction trans = ctx.bodyAsClass(Transaction.class);
   			try {
   				am.transfer(trans);
    			ctx.status(200).result("Transfer done successfull");
   			} catch (Exception e) {
				ctx.status(500).result(e.getMessage());
			}
   		});
	    app.put("account", ctx -> {
	    	long account = am.createAccount(ctx.bodyAsClass(Owner.class).getName());
	    	ctx.status(201).result(""+account);
	    });
	}

}
