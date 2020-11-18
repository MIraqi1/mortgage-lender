import java.time.LocalDate;
import java.util.Date;

final class Lender {

    private int pendingBalance;


    public Lender(int balance) {
        this.balance = balance;
    }

    private int balance;

    public int getBalance() {
        return this.balance;
    }

    public void addFunds(int newFunds) {
        this.balance += newFunds;
    }

    public void checkLoan(LoanApplicant loanApplicant) {
        if(loanApplicant.getRequestedAmount() > this.balance) {
            loanApplicant.setStatus(ApplicationStatus.INSUFFICIENT_FUNDS);
            return;
        }
        double debtToIncome = (double)loanApplicant.getMonthlyDebtLoad() / loanApplicant.getMonthlyGrossIncome() * 100;
        double amountSaved = (double)loanApplicant.getAmountSaved() / loanApplicant.getRequestedAmount() * 100;
        if (loanApplicant.getCreditScore() > 620 && debtToIncome < 36 && amountSaved >= 25) {
            loanApplicant.setStatus(ApplicationStatus.QUALIFIED);
        } else {
            loanApplicant.setStatus(ApplicationStatus.DENIED);
        }
    }

    public double calculateMonthlyMortgagePmt(int principal, double interestRate, int numberOfPayments) {
        return principal * ((interestRate / 12.0) * Math.pow(1 + (interestRate / 12.0), numberOfPayments) / (Math.pow(1 + (interestRate / 12.0), numberOfPayments) - 1));
    }

    public void approveLoan(LoanApplicant loanApplicant) {
        if(loanApplicant.getStatus() == ApplicationStatus.OFFER_ACCEPTED) {
            loanApplicant.setStatus(ApplicationStatus.APPROVED);
        }
    }

    public void offerLoan(LoanApplicant loanApplicant) {
        if(loanApplicant.getStatus() == ApplicationStatus.QUALIFIED) {
            loanApplicant.setStatus(ApplicationStatus.OFFERED);
            loanApplicant.setOfferStartDate(LocalDate.now());
            loanApplicant.setOfferExpirationDate(LocalDate.now().plusDays(3));
            pendingBalance = (int) loanApplicant.getRequestedAmount();
            balance -= pendingBalance;
        }
    }

    public void sendLoan(LoanApplicant loanApplicant) {
        if(loanApplicant.getStatus() == ApplicationStatus.APPROVED) {
            pendingBalance -= loanApplicant.getRequestedAmount();
            loanApplicant.setStatus(ApplicationStatus.MONEY_ISSUED);
        }
    }

    public int getPendingBalance() {
        return this.pendingBalance;
    }
}
