import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
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
        LoanApplicant loanApplicant = new LoanApplicant(3000, 700000, 3000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        Assertions.assertEquals(ApplicationStatus.INSUFFICIENT_FUNDS, loanApplicant.getStatus());
    }

    @Test
    void calculate_monthly_mortgage_pmt() {
        Assertions.assertEquals(1193.54,lender.calculateMonthlyMortgagePmt(250000, 0.04, 360),0.01);
    }

    @Test
    void determine_to_offer_loan() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 350000, 4000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        Assertions.assertEquals(ApplicationStatus.QUALIFIED, loanApplicant.getStatus());
    }

    @Test
    void offer_loan_to_qualified_candidate() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 350000, 4000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        lender.offerLoan(loanApplicant);
        Assertions.assertEquals(ApplicationStatus.OFFERED, loanApplicant.getStatus());
    }

    @Test
    void approve_offer_accepted_by_applicant() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 350000, 4000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        lender.offerLoan(loanApplicant);
        loanApplicant.acceptOffer();
        lender.approveLoan(loanApplicant);
        Assertions.assertEquals(ApplicationStatus.APPROVED, loanApplicant.getStatus());
    }

    @Test
    void send_requested_loan_amount() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 350000, 4000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        lender.offerLoan(loanApplicant);
        loanApplicant.acceptOffer();
        lender.approveLoan(loanApplicant);
        lender.sendLoan(loanApplicant);
        Assertions.assertEquals(150000, lender.getBalance());
    }

    @Test
    void set_offer_expiration_date() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 350000, 4000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        lender.offerLoan(loanApplicant);
        Assertions.assertEquals(3, loanApplicant.getOfferExpirationDays());
    }

    @Test
    void check_pending_funds() {
        LoanApplicant loanApplicant = new LoanApplicant(3000, 350000, 4000, 1000, 700, 87500);
        lender.checkLoan(loanApplicant);
        lender.offerLoan(loanApplicant);
        Assertions.assertEquals(150000, lender.getBalance());
        Assertions.assertEquals(350000, lender.getPendingBalance());
    }
}
