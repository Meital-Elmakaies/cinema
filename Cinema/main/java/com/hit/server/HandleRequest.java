package com.hit.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import com.google.gson.Gson;
import com.hit.algorithm.Order;
import com.hit.service.CinemaServiceInterface;
import com.hit.service.Controller;

public class HandleRequest implements Runnable {

	Gson gson = new Gson();
	ObjectOutputStream output;
	ObjectInputStream input;
	CinemaServiceInterface service;
	MyResponse Response;
	Controller controller;

	public HandleRequest(OutputStream out, InputStream in, CinemaServiceInterface Service)
			throws ClassNotFoundException {
		try {
			output = new ObjectOutputStream(out);
			input = new ObjectInputStream(in);

			this.controller = new Controller(Service);

		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	public void Close() throws IOException {
		output.close();
		input.close();
	}

//Takes all requests and handles them by of any request to Json
	public void run() {
		try {
			String messageIn = (String) this.input.readObject();

			Request req = gson.fromJson(messageIn, Request.class);

			if (!(req.getAction().equals("ExitProgram"))) {
				if (req.getAction().equals("ChooseAlgo")) {

					int algoNumber = gson.fromJson(req.getBody(), int.class);

					if (algoNumber == 1) {
						this.controller.SetAlgoNaive();
					} else if (algoNumber == 2) {
						this.controller.SetAlgoOptimal();
					}
				}
				while (true) {
					messageIn = (String) this.input.readObject();
					req = gson.fromJson(messageIn, Request.class);
					if (!(req.getAction().equals("ExitProgram"))) {

						if (req.getAction().equals("newOrder")) {
							Order MyOrder = gson.fromJson(req.getBody(), Order.class);
							Response = this.controller.ContinueOrdering(MyOrder);
							this.output.writeObject(gson.toJson(Response));
							this.output.flush();
						}

						if (req.getAction().equals("CancelOrder")) {
							Order MyOrder = gson.fromJson(req.getBody(), Order.class);// true
							Response = this.controller.DeleteOrder(MyOrder);
							this.output.writeObject(gson.toJson(Response));
							this.output.flush();
						}
						if (req.getAction().equals("DeleteOrder")) {
							Order MyOrder = gson.fromJson(req.getBody(), Order.class);
							Response = this.controller.DeleteOrder(MyOrder);
							this.output.writeObject(gson.toJson(Response));
							this.output.flush();
						}
						if (req.getAction().equals("CloseProgram")) {
							this.controller.CloseProgram();
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}
