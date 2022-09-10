package com.hit.service;

import java.io.IOException;

import com.hit.algorithm.IAlgoSeatingArrangement;
import com.hit.algorithm.Order;
import com.hit.server.MyResponse;

public interface CinemaServiceInterface {
	MyResponse ContinueOrdering(Order order) throws IOException;

	void SetCinemaSeatingAlgo(IAlgoSeatingArrangement Algo);

	int GetNumberofseatsavailabl();

	MyResponse DeleteOrder(Order order);

	void CloseProgram();

}