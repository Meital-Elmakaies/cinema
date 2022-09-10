package com.hit.algorithm;

public class Order {
	private int Ticket;
	private boolean Statusseat=false;
	private String Name;
	private int Row;
	private int Chair;
	private int Id;
	
	public Order(int ticket) {
		Ticket=ticket;
	}
	public Order() {
		
	}
	
	public int getTicket() {
		return Ticket;
	}
	public void setTicket(int ticket) {
		Ticket = ticket;
	}
	
	public boolean isStatusseat() {
		return Statusseat;
	}
	public void setStatusseat(boolean statusseat) {
		Statusseat = statusseat;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getRow() {
		return Row;
	}
	public void setRow(int row) {
		Row = row;
	}
	public int getChair() {
		return Chair;
	}
	public void setChair(int chair) {
		Chair = chair;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public String GetCsv() {
		return this.Name + "," + this.Id + "," + this.Ticket + "," + this.Row + "," + this.Chair +","+ this.Statusseat;			
	}
	
	public Order(String csvData) {
		this.ImportCSVData(csvData);
	}
	public void ImportCSVData(String data) {
		String[] OrderData = data.split(",");
		this.Name=OrderData[0];
		this.Id=Integer.parseInt(OrderData[1]);
		this.Ticket=Integer.parseInt(OrderData[2]);
		this.Row=Integer.parseInt(OrderData[3]);
		this.Chair=Integer.parseInt(OrderData[4]);
		this.Statusseat=Boolean.parseBoolean(OrderData[5]);
	}

}
