package com.hit.service;

import java.io.IOException;

import com.hit.algorithm.IAlgoSeatingArrangement;
import com.hit.algorithm.Order;
import com.hit.dao.IDao;
import com.hit.server.MyResponse;

public class CinemaService implements CinemaServiceInterface {
	private IAlgoSeatingArrangement algo;
	private Cinema cinema;
	private IDao DB;

	public CinemaService(IDao db) {
		this.DB = db;
	}

	public CinemaService(IAlgoSeatingArrangement Algo, IDao db) {

		this.algo = Algo;
		this.cinema = new Cinema(algo);
		this.DB = db;

	}

	public int GetNumberofseatsavailabl() {
		return this.cinema.getNumberofseatsavailabl();
	}

	// This function take an algorithm and setting it as the seating algo,
	// It also create new Cinema so it reset all of the sits.
	public void SetCinemaSeatingAlgo(IAlgoSeatingArrangement Algo) {
		this.algo = Algo;
		this.cinema = new Cinema(algo);
		cinema.InitCinema();
	}

	// After filling in details continue booking and finding seats
	public MyResponse ContinueOrdering(Order order) throws IOException {
		boolean StatusSeat = false;
		if (order.getTicket() <= cinema.getNumberofseatsavailabl()) {// Is the number of tickets smaller than the number
																		// of available seats in the cinema
			if ((order.getTicket()) <= (cinema.getCol()))// Checks that the amount of tickets will not be greater than
															// the amount in a row
			{
				StatusSeat = cinema.SeatingCinema(order);
				if (StatusSeat == true) {
					cinema.setNumberofseatsavailabl(cinema.getNumberofseatsavailabl() - order.getTicket());
					DB.WriteToDB(order);
					MyResponse Response = new MyResponse(true, "The order was successfully placed :)", order);
					if (cinema.getNumberofseatsavailabl() == 0) {
						Response.setLastStep(true);
					}
					return Response;
				}
			} else {
				MyResponse Response = new MyResponse(false,
						"The number of tickets you entered is greater than the number of seats in the row,\n"
								+ "you can not sit together - please split the order",
						order);
				return Response;
			}

		} else {
			MyResponse Response = new MyResponse(false, "There are no places available! There are"
					+ cinema.getNumberofseatsavailabl() + " places available Try re-entering", order);
			return Response;
		}

		return new MyResponse(false, "Sorry, there are no places to sit next to each other please try again", order);

	}

	public MyResponse DeleteOrder(Order order) {
		boolean DeleteCompleted = DB.DeleteToDB(order);
		if (DeleteCompleted) {
			cinema.DeleteOrder(order);
			cinema.setNumberofseatsavailabl(cinema.getNumberofseatsavailabl() + order.getTicket());
			order.setStatusseat(false);
			MyResponse Response = new MyResponse(true, "Deletion completed successfully!", order);
			return Response;
		}
		MyResponse Response = new MyResponse(false, "Your details were not found in the system , try again", order);
		return Response;
	}

	public void CloseProgram() {
		DB.CloseToDB();
	}
}
