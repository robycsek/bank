package bank.backend;

import java.util.List;

public interface BankDao {
    void addClient(Client client);

    List<Client> listClients();
}
