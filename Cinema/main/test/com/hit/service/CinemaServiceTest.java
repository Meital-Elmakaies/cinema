package com.hit.service;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.hit.algorithm.IOptimalAlgoOfTheAlgoFamily;
import com.hit.algorithm.Order;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.server.MyResponse;

public class CinemaServiceTest {

	public IOptimalAlgoOfTheAlgoFamily AlgoOpt;
	public CinemaService service;
	public IDao DB;
	public MyResponse Response;

	@Before
	public void FillDB() {
		DaoFileImpl db = new DaoFileImpl("C:\\Users\\yuval\\eclipse-workspace\\Cinema\\Datasource.txt");
		service = new CinemaService(db);
		this.service.SetCinemaSeatingAlgo(AlgoOpt = new IOptimalAlgoOfTheAlgoFamily());

	}

	@Test
	public void TestAddingACustomer() {

		Order order1 = new Order();
		order1.setName("yuval");
		order1.setId(313930703);
		order1.setTicket(3);

		try {
			Response = service.ContinueOrdering(order1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean output = Response.isSuccess();

		assertEquals("the seating performed successfully! you can also see the order in the file", true, output);
	}

	// The function deletes the last customer who enters and in addition inserts a
	// new customer
	// with the same amount of tickets and shows that is actually deleted in a
	// cinema and not just in a file
	@Test
	public void TestDeleteAfterLastOrder() {

		Order order1 = new Order();
		order1.setName("osher");
		order1.setId(313931045);
		order1.setTicket(3);

		Order order2 = new Order();
		order2.setName("meital");
		order2.setId(207058322);
		order2.setTicket(2);

		Order order3 = new Order();
		order3.setName("meital After Delete Seat In The Same Sit");
		order3.setId(207058422);
		order3.setTicket(2);
		// seated at the cinema and writh to the file
		try {
			service.ContinueOrdering(order1);
			service.ContinueOrdering(order2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Response = service.DeleteOrder(order2); // the deleted
		System.out.println(Response.getReason());
		boolean output = Response.isSuccess(); // need to be true if it was delete
		try {
			service.ContinueOrdering(order3);// same order 2 ticket for prove it was delete in the cinema and not just
												// in the file

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("the deletion completed! you can also see the order in the file", true, output);
	}

	// The function deletes the customer who enters and in addition inserts a new
	// customer
	// with the same amount of tickets and shows that is actually deleted in a
	// cinema and not just in a file
	@Test
	public void TestDeleteOrder() {

		Order order1 = new Order();
		order1.setName("orel");
		order1.setId(313947853);
		order1.setTicket(3);

		Order order2 = new Order();
		order2.setName("shay");
		order2.setId(207078122);
		order2.setTicket(2);

		Order order3 = new Order();
		order3.setName("orel Agian after the deletion");// to prove it was deletion not just in the file and also in the
														// cinema
		order3.setId(313947853);
		order3.setTicket(3);

		try {
			service.ContinueOrdering(order1);
			service.ContinueOrdering(order2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response = service.DeleteOrder(order1);
		System.out.println(Response.getReason());
		boolean output = Response.isSuccess();

		try {
			service.ContinueOrdering(order3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("the deletion completed! you can also see the order in the file", true, output);
	}

	@Test
	public void TestDeleteANonExistentClient() {

		Order order1 = new Order();
		order1.setName("Lihen");
		order1.setId(337847853);
		order1.setTicket(3);

		Order order2 = new Order();
		order2.setName("Tomer");
		order2.setId(207074122);
		order2.setTicket(2);

		try {
			service.ContinueOrdering(order1);
			service.ContinueOrdering(order2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order order3 = new Order();
		order3.setName("Smadi");// smadi not in the system we didnt put her in continueOrdering
		order3.setId(205074122);
		order3.setTicket(2);
		Response = service.DeleteOrder(order3);// try to delete smadi when she is not in our system
		System.out.println(Response.getReason());
		boolean output = Response.isSuccess();// expecting to be false

		assertEquals("the deletion not completed! you can also see the order in the file", false, output);
	}

}
