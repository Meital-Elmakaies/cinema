package com.hit.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.google.gson.Gson;
import com.hit.info.MyResponse;
import com.hit.info.Order;
import com.hit.info.Request;

public class Controller {

	Socket myServer;
	ObjectOutputStream output;
	ObjectInputStream input;
	Gson gson = new Gson();

	public Controller() throws UnknownHostException, IOException {
		myServer = new Socket("localhost", 20704);
		output = new ObjectOutputStream(myServer.getOutputStream());
		input = new ObjectInputStream(myServer.getInputStream());
	}

	public void ChoiceAlgo(int Algo) throws IOException {
		String json = gson.toJson(Algo);
		Request req = new Request("ChooseAlgo", json);
		json = gson.toJson(req);
		output.writeObject(json);
		output.flush();
	}

	public void MenuChoice(int Choice) throws IOException {
		String json = gson.toJson(Choice);
		Request req = new Request("MenuChoice", json);
		json = gson.toJson(req);
		output.writeObject(json);
		output.flush();
	}

	public MyResponse ContinueOrdering(String Name, int Id, int Ticket) throws IOException, ClassNotFoundException {
		Order myorder = new Order();
		myorder.setName(Name);
		myorder.setId(Id);
		myorder.setTicket(Ticket);
		String json = gson.toJson(myorder);
		Request req = new Request("newOrder", json);
		json = gson.toJson(req);
		output.writeObject(json);
		output.flush();

		MyResponse Myresponse;
		String ResponseFromServer = (String) input.readObject();
		Myresponse = gson.fromJson(ResponseFromServer, MyResponse.class);
		return Myresponse;

	}

	public MyResponse DeleteOrder(String Name, int Id) throws ClassNotFoundException, IOException {
		Order order = new Order();
		order.setName(Name);
		order.setId(Id);
		String json = gson.toJson(order);
		Request req = new Request("DeleteOrder", json);
		json = gson.toJson(req);
		try {
			output.writeObject(json);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyResponse Myresponse;
		String ResponseFromServer = (String) input.readObject();
		Myresponse = gson.fromJson(ResponseFromServer, MyResponse.class);
		return Myresponse;
	}

	public void CloseProgram(String exit) throws IOException {
		String json = gson.toJson(exit);
		Request req = new Request("CloseProgram", json);
		json = gson.toJson(req);
		output.writeObject(json);
		output.flush();
		output.close();
		input.close();
		myServer.close();
	}
}
