package donatr.domain.account.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class AccountCreatedEvent {
	final String id;
	protected final String name;
}
