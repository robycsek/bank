package bank.backend;

import org.springframework.context.ApplicationEvent;

public class ClientHasCreatedEvent extends ApplicationEvent {

    private String name;

    public ClientHasCreatedEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
