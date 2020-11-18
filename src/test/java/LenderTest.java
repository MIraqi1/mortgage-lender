import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


final class LenderTest {

    private Lender lender;

    @BeforeEach
    void setUp() {
        lender = new Lender(500000);
    }

    @Test
    void check_balance() {
        Assertions.assertEquals(500000,lender.getBalance());
    }

    @Test
    void add_funds() {
        lender.addFunds(100000);
        Assertions.assertEquals(600000,lender.getBalance());
    }

    @Test
    void deny_application_when_amount_is_insufficient() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 700000, 3000, 1000, 700);
        Assertions.assertEquals(ApplicationStatus.INSUFFICIENT_FUNDS, lender.checkLoan(loanApplicant));
    }
}
