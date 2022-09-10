package com.hit.info;

public class MyResponse {
	private boolean Success;
	private String Reason;
	private boolean isLastStep;
	Order order;

	public MyResponse(boolean Success, String Reason, Order myorder) {
		this.Success = Success;
		this.Reason = Reason;
		this.isLastStep = false;
		this.order = myorder;
	}

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public boolean isLastStep() {
		return isLastStep;
	}

	public void setLastStep(boolean isLastStep) {
		this.isLastStep = isLastStep;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
