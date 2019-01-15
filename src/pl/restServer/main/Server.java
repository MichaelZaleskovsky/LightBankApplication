package pl.restServer.main;

import java.util.stream.Collectors;

import io.javalin.Javalin;
import pl.restServer.entities.Account;
import pl.restServer.entities.AccountJS;
import pl.restServer.entities.Owner;
import pl.restServer.entities.Transaction;

public class Server {

	public static void main(String[] args) {
		AccountManager am = new AccountManager();
		Javalin app = Javalin.create().enableCorsForOrigin("*").port(7070).start();
		app.error(404, ctx -> ctx.result("Error 404 Page not found"));
		app.get("/", ctx -> ctx.result("SERVER Start sccessfuly"));
		
		new WebControllers(app).register();
		
/*	    app.get("accounts", ctx -> {									// GET accounts
	    		ctx
	    		.status(200)
	    		.json(am.getAllAccounts().stream().map(acc -> new AccountJS(acc)).collect(Collectors.toList()));      
	    });
*/	    
/*	    app.get("account/:accnum", ctx -> {								// GET account
	    	long accNum;
			try {
				accNum = Long.parseLong(ctx.pathParam("accnum"));
		    	Account acc = am.getAccount(accNum);
		    	if (acc == null) ctx.status(500)
		    						.result("Account number "+accNum+" not exist");
		    	else ctx.status(200).json(new AccountJS(acc));
			} catch (NumberFormatException e) {
				ctx.status(400)
					.result("Account number "+ctx.pathParam("accnum")+" must content digits only");
			}
	    });
	    app.put("transfer", ctx -> {									// POST transfer
   			Transaction trans = ctx.bodyAsClass(Transaction.class);
   			try {
     			am.transfer(trans);
    			ctx.status(200).result("Transfer done successful");
   			} catch (NumberFormatException e) {
				ctx.status(400).result("Account number must content digits only");
			} catch (Exception e) {
				ctx.status(400).result(e.getMessage());
			}
   		});
	    app.post("account", ctx -> {
	    	long account = am.createAccount(ctx.bodyAsClass(Owner.class).getName());
	    	ctx.status(201).result("Account No "+account+" created successfuly");
	    });
	    app.put("deposit", ctx -> {
	    	try {
	    		Transaction trans = ctx.bodyAsClass(Transaction.class);
				long accNum = Long.parseLong(trans.getAccountTo());
				am.credit(accNum, trans.getSum());
		    	ctx.status(200).result(""+trans.getSum()+" deposit on account "+accNum+" successfuly");
			} catch (NumberFormatException e) {
				ctx.status(400).result("Account number must content digits only");
			} catch (Exception e) {
				ctx.status(400).result(e.getMessage());
			}
	    });
	    app.put("withdraw", ctx -> {
	    	try {
	    		Transaction trans = ctx.bodyAsClass(Transaction.class);
				long accNum = Long.parseLong(trans.getAccountTo());
				am.debit(accNum, trans.getSum());
		    	ctx.status(200).result(""+trans.getSum()+" withdraw from account "+accNum+" successfuly");
			} catch (NumberFormatException e) {
				ctx.status(400).result("Account number must content digits only");
			} catch (Exception e) {
				ctx.status(400).result(e.getMessage());
			}
	    });
*/	}

}
