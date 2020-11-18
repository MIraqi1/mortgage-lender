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

        return null;
    }
}
