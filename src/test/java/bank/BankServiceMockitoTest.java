package bank;

import bank.backend.BankDao;
import bank.backend.BankService;
import bank.backend.Client;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class BankServiceMockitoTest {

    @Test
    public void testAddClient() {
        BankDao bankDao = Mockito.mock(BankDao.class);
        BankService bankService =  new BankService(null, bankDao);
        ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);

        //When
        bankService.addClient("Jhon Doe");

        //Then
        Mockito.verify(bankDao).addClient(captor.capture());
        assertEquals("Jhon Doe", captor.getValue().getName());
    }
}
