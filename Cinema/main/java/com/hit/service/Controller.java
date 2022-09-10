package com.hit.service;

import java.io.IOException;

import com.hit.algorithm.INaiveAlgoOfTheAlgoFamily;
import com.hit.algorithm.IOptimalAlgoOfTheAlgoFamily;
import com.hit.algorithm.Order;
import com.hit.server.MyResponse;

public class Controller {

	CinemaServiceInterface service;
	MyResponse Response;
	Order Order ;
	
	public Controller(CinemaServiceInterface Service) {
		this.service=Service;
	}
	
	public void SetAlgoNaive() {
		this.service.SetCinemaSeatingAlgo(new INaiveAlgoOfTheAlgoFamily());
	}
	
	public void SetAlgoOptimal() {
		this.service.SetCinemaSeatingAlgo(new IOptimalAlgoOfTheAlgoFamily());
	}
	
	public  MyResponse ContinueOrdering(Order MyOrder) throws IOException {
		return service.ContinueOrdering(MyOrder);
		
	}
	
	public  MyResponse CancelOrder(Order MyOrder) {
		return service.DeleteOrder(MyOrder);
	}
	
	public  MyResponse DeleteOrder(Order MyOrder) {
		return service.DeleteOrder(MyOrder);
	}
	
	public int GetNumberofseatsavailabl() {
		return this.service.GetNumberofseatsavailabl();
	}
	public void CloseProgram() {
		this.service.CloseProgram();
	}
	
}
