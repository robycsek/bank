package bank;

import bank.backend.BankDao;
import bank.backend.BankService;
import bank.backend.Client;
import bank.backend.InMemoryBankDao;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BankServiceIntegrationTest {

    @Test
    public void afterAddShouldList() {
        BankDao bankDao = new InMemoryBankDao();
        BankService bankService = new BankService(null, bankDao);
        bankService.addClient("Jhon Doe");
        bankService.addClient("Jane Doe");
        bankService.addClient("Jack Doe");

        List<Client> clients = bankService.listClients();
        assertEquals(Arrays.asList("Jhon Doe", "Jane Doe", "Jack Doe"), clients.stream().map(c -> c.getName()).collect(Collectors.toList()));
    }
}
