import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BankTest {

    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    @DisplayName("Test if clients are added")
    void getClientByCNPTest() {
        bank = new Bank();
        Client c1 = new Client("1234", bank);
        Client c2 = new Client("2345", bank);
        c1.depositRon(2000);
        //c1.createAccounts(bank);
        //c2.createAccounts(bank);

        assertEquals(bank.getClientByCNP("1234"), c1);
        assertEquals(bank.getClientByCNP("2345"), c2);
        assertNull(bank.getClientByCNP("6789"));
    }

}