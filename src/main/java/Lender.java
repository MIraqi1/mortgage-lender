final class Lender {

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

    public ApplicationStatus checkLoan(LoanApplicant loanApplicant) {
        if(loanApplicant.getRequestedAmount() > this.balance) {
            return ApplicationStatus.INSUFFICIENT_FUNDS;

        }
        double debtToIncome = (double)loanApplicant.getMonthlyDebtLoad() / loanApplicant.getMonthlyGrossIncome() * 100;
        double amountSaved = (double)loanApplicant.getDownPayment() / loanApplicant.getRequestedAmount() * 100;
        if (loanApplicant.getCreditScore() > 620 && debtToIncome < 36 && amountSaved >= 25) {
            return ApplicationStatus.QUALIFIED;
        }
        return ApplicationStatus.DENIED;
    }

    public double calculateMonthlyMortgagePmt(int principal, double interestRate, int numberOfPayments) {
        return principal * ((interestRate / 12.0) * Math.pow(1 + (interestRate / 12.0), numberOfPayments) / (Math.pow(1 + (interestRate / 12.0), numberOfPayments) - 1));
    }
}
