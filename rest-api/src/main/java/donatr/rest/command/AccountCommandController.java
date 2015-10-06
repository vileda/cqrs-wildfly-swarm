package donatr.rest.command;

import donatr.common.CreateAccountCommand;
import donatr.rest.response.CreateAccountResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.UUID;

@Path("/accounts")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AccountCommandController {
	@Inject
	CreateAccountCommand createAccountCommand;
	@Inject
	Event<CreateAccountCommand> createAccountCommandBus;

	@POST
	public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
		System.out.println("+++ACCOUNTS_QUERY " + createAccountRequest);
		CreateAccountResponse response = new CreateAccountResponse(UUID.randomUUID().toString());
		createAccountCommandBus.fire(new CreateAccountCommand(response.getId()));
		return response;
	}
}