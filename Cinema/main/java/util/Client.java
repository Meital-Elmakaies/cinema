package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.hit.algorithm.Order;
import com.hit.server.MyResponse;
import com.hit.server.Request;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket myServer = new Socket("localhost", 20704);
		ObjectOutputStream output = new ObjectOutputStream(myServer.getOutputStream());
		ObjectInputStream input = new ObjectInputStream(myServer.getInputStream());
		boolean isCinemaFull = false;
		boolean Stop = true;

		System.out.println("If you want to implement a naive algorithm click 1,"
				+ "if you want to implement an optimal algorithm click 2");

		Scanner scan = new Scanner(System.in);
		int Choice = scan.nextInt();
		Gson gson = new Gson();
		String json = gson.toJson(Choice);
		Request req = new Request("ChooseAlgo", json);
		json = gson.toJson(req);
		output.writeObject(json);
		output.flush();

		while (Stop) {
			while ((!isCinemaFull) && (Stop)) {
				int Choicemenu = ChoiceMenu();
				if (Choicemenu == 3) {
					Stop = false;
					break;
				}
				// NewOrder
				Order myorder = fillDetails(Choicemenu);
				if (Choicemenu == 1) {
					json = gson.toJson(myorder);
					req = new Request("newOrder", json);
					json = gson.toJson(req);
					output.writeObject(json);
					output.flush();

					MyResponse Myresponse;
					String ResponseFromServer = (String) input.readObject();
					Myresponse = gson.fromJson(ResponseFromServer, MyResponse.class);
					System.out.println(Myresponse.getReason());
					isCinemaFull = Myresponse.isLastStep();

					if (Myresponse.isSuccess()) {
						System.out.println(myorder.getName() + " Your seat is:");
						int chair = Myresponse.getOrder().getChair();
						int ticket = Myresponse.getOrder().getTicket();
						while (ticket != 0) {
							System.out.println(
									" Row number-" + Myresponse.getOrder().getRow() + " In chair number- " + chair);
							chair++;
							ticket--;
						}

						System.out.println("Do you want to cancel the order? To cancel press 1, if not press 0");
						Choice = scan.nextInt();
						if (Choice == 1) {
							json = gson.toJson(myorder);
							req = new Request("CancelOrder", json);
							json = gson.toJson(req);
							output.writeObject(json);
							output.flush();
							isCinemaFull = false;
							ResponseFromServer = (String) input.readObject();
							Myresponse = gson.fromJson(ResponseFromServer, MyResponse.class);
							System.out.println(Myresponse.getReason());
						}
					}
				}

				else if (Choicemenu == 2) {
					json = gson.toJson(myorder);
					req = new Request("DeleteOrder", json);
					json = gson.toJson(req);
					output.writeObject(json);
					output.flush();
					MyResponse Myresponse;
					String ResponseFromServer = (String) input.readObject();
					Myresponse = gson.fromJson(ResponseFromServer, MyResponse.class);
					System.out.println(Myresponse.getReason());
				}
			} // וואיל פנימי
			if (Stop) {
				int Choicemenu = ChoiceMenu();
				if (Choicemenu == 3) {
					Stop = false;
				}
				if (Choicemenu == 1 && isCinemaFull) {
					System.out.println("The cinema is full, it is no longer possible to book seats ");
				}
				if (Choicemenu == 2 && isCinemaFull) {
					Order myorder = fillDetails(Choicemenu);
					json = gson.toJson(myorder);
					req = new Request("DeleteOrder", json);
					json = gson.toJson(req);
					output.writeObject(json);
					output.flush();
					MyResponse Myresponse;
					String ResponseFromServer = (String) input.readObject();
					Myresponse = gson.fromJson(ResponseFromServer, MyResponse.class);
					System.out.println(Myresponse.getReason());
					if (Myresponse.isSuccess()) {
						isCinemaFull = false;
					}
				}
			} // וואיל חציוני
			req = new Request("CloseProgram", null);
			json = gson.toJson(req);
			output.writeObject(json);
			output.flush();

			output.close();
			input.close();
			myServer.close();
		}
	}

	public static Order fillDetails(int Choice) throws IOException, ClassNotFoundException {
		Order myorder = new Order();
		System.out.println("Enter your full name:");
		Scanner scan1 = new Scanner(System.in);
		String Details = scan1.nextLine();
		myorder.setName(Details);
		System.out.println("Enter your Id:");
		Scanner scan = new Scanner(System.in);
		int ID = scan.nextInt();
		myorder.setId(ID);
		// If he chose 2 he does not have to fill in the amount of tickets but only the
		// name and ID number
		if (Choice == 1) {
			System.out.println("Enter how many tickets do you want: ");
			int Ticket = scan.nextInt();
			myorder.setTicket(Ticket);
		}
		return myorder;
	}

	public static int ChoiceMenu() throws IOException {
		System.out.println("For new order click 1, to cancel order click 2, to exit of process write 3 ");
		Scanner scan = new Scanner(System.in);
		int Choice = scan.nextInt();
		return Choice;
	}

}
