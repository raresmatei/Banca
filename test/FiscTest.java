import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FiscTest {

    Bank bank;
    Fisc fisc;

    @BeforeEach
    void setUp() {
        fisc = Fisc.getInstance();
        bank = new Bank();
    }

    @Test
    void startMonitorizeTest() {
        Client c1 = new Client("1234", bank);
        Client c2 = new Client("2345", bank);

        fisc.startMonitorize("5678", bank);
        assertEquals(fisc.getMonitorizedClients().size(), 0);

        fisc.startMonitorize("1234", bank);
        assertEquals(fisc.getMonitorizedClients().size(), 1);
        assertEquals(c1.getObservers().size(), 2);

        fisc.startMonitorize("2345", bank);
        assertEquals(c2.getObservers().size(), 2);
        assertEquals(fisc.getMonitorizedClients().size(), 2);
    }

    @Test
    void stopMonitorizeTest() {
        Client c1 = new Client("1234", bank);
        Client c2 = new Client("2345", bank);

        fisc.stopMonitorize("5678", bank);

        fisc.startMonitorize("1234", bank);
        fisc.startMonitorize("2345", bank);
        fisc.stopMonitorize("1234", bank);
        assertEquals(fisc.getMonitorizedClients().size(), 1);
        assertEquals(c1.getObservers().size(), 0);

        fisc.stopMonitorize("2345", bank);
        assertEquals(fisc.getMonitorizedClients().size(), 0);
        assertEquals(c2.getObservers().size(), 0);
    }

    @Test
    void enableObserversTest(){
        Client c1 = new Client("1234", bank);
        assertEquals(c1.getObservers().size(), 0);

        fisc.enableObservers(c1);
        assertEquals(c1.getObservers().size(), 2);
    }
}