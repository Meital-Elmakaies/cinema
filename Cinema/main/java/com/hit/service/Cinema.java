package com.hit.service;

import com.hit.algorithm.IAlgoSeatingArrangement;
import com.hit.algorithm.Order;

public class Cinema {
	private int row;
	private int col;
	private int Numberofseatsavailabl;
	private Order[][] Mycinema = new Order[3][4];

	private IAlgoSeatingArrangement ialgoseatingarrangement;

	// Strategy pattern with interface
	public Cinema(IAlgoSeatingArrangement ialgoseatingarrangement) {
		this.ialgoseatingarrangement = ialgoseatingarrangement;
	}

	public boolean executeStrategy(Order order, Order Mycinema[][], int row, int col) {
		return ialgoseatingarrangement.SeatedMyCinema(order, Mycinema, row, col);
	}

	// initialization the cinema
	public void InitCinema() {
		row = 3;
		col = 4;
		Numberofseatsavailabl = row * col;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Mycinema[i][j] = null;
			}
		}
	}

	// Sends to the seating function according to the selected algorithm
	public boolean SeatingCinema(Order order) {

		return this.ialgoseatingarrangement.SeatedMyCinema(order, Mycinema, row, col);
	}

	// A function that deletes customers from the movie theater
	public void DeleteOrder(Order order) {
		int DeleteRow = order.getRow();
		int DeleteCol = order.getChair();
		int DeleteTicket = order.getTicket();
		for (int j = 0; j < DeleteTicket; j++) {
			Mycinema[DeleteRow][DeleteCol] = null;
			DeleteCol++;
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getNumberofseatsavailabl() {
		return Numberofseatsavailabl;
	}

	public void setNumberofseatsavailabl(int numberofseatsavailabl) {
		Numberofseatsavailabl = numberofseatsavailabl;
	}

}
