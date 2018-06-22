package bank.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class BankService {

    private ApplicationEventPublisher applicationEventPublisher;

    private BankDao bankDao;

    private JpaLogEntryDao logEntryDao;

    //ha csak konstruktor injection van akkor beírhatjuk a konstruktorba is, ilyenkor felesleges így
    @PostConstruct
    public void init() {
        System.out.println("BankService has been created");
    }

    public BankService(ApplicationEventPublisher applicationEventPublisher, BankDao bankDao) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.bankDao = bankDao;
    }

    @Transactional
    public void addClient(Client client) {
        bankDao.addClient(client);
    }

    @Transactional
    public void addClient(String name, Address... addresses) {
        Client client = new Client(name);
        Arrays.stream(addresses).forEach(a -> client.addAddress(a));
        bankDao.addClient(client);

    }

    @Transactional
    public void addClient(String name) {
        Client client = new Client(name);
        bankDao.addClient(client);
        if (applicationEventPublisher != null) {
            applicationEventPublisher.publishEvent(new ClientHasCreatedEvent(this, name));
        }

        if (logEntryDao != null) {
            logEntryDao.log("Client has created: " + name);
        }

        if (name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
    }

    public List<Client> listClients() {
        return bankDao.listClients();
    }

    @Autowired
    public void setLogEntryDao(JpaLogEntryDao logEntryDao) {
        this.logEntryDao = logEntryDao;
    }
}
