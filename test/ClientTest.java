import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientTest {

    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void createAccountsTest() {
        assertEquals(bank.getClients().size(), 0);

        Client c1 = new Client("1234", bank);
        //c1.createAccounts(bank);
        assertEquals(bank.getClients().size(), 1);

        Client c2 = new Client("2345", bank);
        //c2.createAccounts(bank);
        assertEquals(bank.getClients().size(), 2);

        //c1.createAccounts(bank);
        assertEquals(bank.getClients().size(), 2);

        //c2.createAccounts(bank);
        assertEquals(bank.getClients().size(), 2);
    }

    @Test
    void attachObserverTest(){
        Client c1 = new Client("1234", bank);
        //c1.createAccounts(bank);
        assertEquals(c1.getObservers().size(), 0);

        FiscObserver obs = new FiscRonObserver(c1);
        assertEquals(c1.getObservers().size(), 1);

        FiscObserver obs1 = new FiscEuroObserver(c1);
        assertEquals(c1.getObservers().size(), 2);
    }

    @Test
    void detachObserverTest(){
        Client c1 = new Client("1234", bank);
        //c1.createAccounts(bank);
        assertEquals(c1.getObservers().size(), 0);

        FiscObserver obs = new FiscRonObserver(c1);
        c1.detach();
        assertEquals(c1.getObservers().size(), 0);

        FiscObserver obs1 = new FiscEuroObserver(c1);
        c1.detach();
        assertEquals(c1.getObservers().size(), 0);

        FiscObserver obs2 = new FiscRonObserver(c1);
        FiscObserver obs3 = new FiscEuroObserver(c1);
        c1.detach();
        assertEquals(c1.getObservers().size(), 0);
    }

    @Test
    void clearAccountTest(){
        Client c1 = new Client("1234", bank);
        //c1.createAccounts(bank);
        c1.depositEuro(1000);
        c1.depositRon(1000);

        Client c2 = new Client("2345", bank);
        //c2.createAccounts(bank);
        c2.depositEuro(1000);
        c2.depositRon(1000);

        assertEquals(bank.getClients().size(), 2);

        c1.clearAccount();
        c2.clearAccount();
        assertEquals(bank.getClients().size(), 2);

        c1.withdrawEuro(1000 );
        c1.withdrawRon(1000);
        c1.clearAccount();
        assertEquals(bank.getClients().size(), 1);

        c2.withdrawEuro(1000);
        c2.withdrawRon(1000);
        c2.clearAccount();
        assertEquals(bank.getClients().size(), 0);
    }

    @Test
    void depositRonTest(){
        Client c1 = new Client("1234", bank);
        c1.depositRon(100);
        c1.depositRon(500);
        assertEquals(c1.getSoldContRon(), 0);

        c1.depositRon(1000);
        c1.depositRon(1000);
        assertEquals(c1.getSoldContRon(), 2000);

    }

    @Test
    void depositEuroTest(){
        Client c1 = new Client("1234", bank);
        c1.depositEuro(100);
        c1.depositEuro(500);
        assertEquals(c1.getSoldContEuro(), 0);

        c1.depositEuro(1000);
        c1.depositEuro(1000);
        assertEquals(c1.getSoldContEuro(), 2000);
    }

    @Test
    void withdrawRonTest(){
        Client c1 = new Client("1234", bank);
        c1.depositRon(1000);
        c1.withdrawRon(100);
        assertEquals(c1.getSoldContRon(), 1000);
        c1.withdrawRon(1000);
        assertEquals(c1.getSoldContRon(), 0);

        c1.depositRon(2000);
        c1.withdrawRon(1000);
        assertEquals(c1.getSoldContRon(), 1000);
    }

    @Test
    void withdrawEuroTest(){
        Client c1 = new Client("1234", bank);
        c1.depositEuro(1000);
        c1.withdrawEuro(100);
        assertEquals(c1.getSoldContEuro(), 1000);
        c1.withdrawEuro(1000);
        assertEquals(c1.getSoldContEuro(), 0);

        c1.depositEuro(2000);
        c1.withdrawEuro(1000);
        assertEquals(c1.getSoldContEuro(), 1000);
    }
}
