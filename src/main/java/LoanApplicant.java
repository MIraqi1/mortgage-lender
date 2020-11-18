final class LoanApplicant {
	private final int  creditScore;
	private final int  downPayment;
	private final int  monthlyDebtLoad;
	private final int  monthlyGrossIncome;
	private final long requestedAmount;
    private final int  amountSaved;
    private ApplicationStatus status;

	LoanApplicant(final int downPayment, final long requestedAmount, final int monthlyGrossIncome, final int monthlyDebtLoad, final int creditScore, int amountSaved) {
		this.downPayment = downPayment;
		this.requestedAmount = requestedAmount;
		this.monthlyGrossIncome = monthlyGrossIncome;
		this.monthlyDebtLoad = monthlyDebtLoad;
		this.creditScore = creditScore;
		this.amountSaved = amountSaved;
	}

	int getCreditScore() {
		return creditScore;
	}

	int getDownPayment() {
		return downPayment;
	}

	int getMonthlyDebtLoad() {
		return monthlyDebtLoad;
	}

	int getMonthlyGrossIncome() {
		return monthlyGrossIncome;
	}

	long getRequestedAmount() {
		return requestedAmount;
	}

	int getAmountSaved() { return amountSaved; }

	ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public void acceptOffer() {
		if(status == ApplicationStatus.OFFERED) {
			status = ApplicationStatus.OFFER_ACCEPTED;
		}

	}
}
