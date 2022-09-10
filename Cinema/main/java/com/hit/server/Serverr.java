package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.hit.service.CinemaServiceInterface;

public class Serverr {
	ServerSocket server;
	CinemaServiceInterface Service;
	Socket someClient;
	boolean serverIsRunning;
	ExecutorService executorService;

	public Serverr(int port) {
		try {
			server = new ServerSocket(port);
			someClient = null;
			serverIsRunning = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(CinemaServiceInterface service) throws ClassNotFoundException, IOException {
		while (serverIsRunning) {
			this.Service = service;
			someClient = server.accept();
			executorService = Executors.newFixedThreadPool(100);
			HandleRequest HR = new HandleRequest(someClient.getOutputStream(), someClient.getInputStream(),
					this.Service);
			executorService.execute(HR);
		}

		executorService.shutdown();

		someClient.close();
		server.close();
	}
}