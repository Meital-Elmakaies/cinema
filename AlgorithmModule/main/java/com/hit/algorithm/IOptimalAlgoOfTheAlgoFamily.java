package com.hit.algorithm;



public class IOptimalAlgoOfTheAlgoFamily implements IAlgoSeatingArrangement {
		private int check; 
		private int Ticket;
		private int DefaultCol;
		private int DefaultRow;
		private boolean DefaultStatus=false;
		
		

	public boolean SeatedMyCinema(Order order,Order Mycinema[][],int row,int col) {
		Ticket=order.getTicket();
		DefaultStatus=false;
			boolean SingelStatus=false;//Private case of card 1 whether seated or not seated 
			 check = Ticket;
			 if(Ticket!=1) {
			for(int i=0; i<row; i++) {
				 if(check==0) {
					 break;
				 }
				  for(int j=0; j<col ; j++) {
					 if( Mycinema[i][j]==null) {
						 check--;
						 for (int k=j+1 ; check!=0 && k<col;k++)
						 {
							 if(Mycinema[i][k]==null)
							 {
								 check--;
							 }
							 else
							 {
								 check=Ticket;
								 break;
							 }
							 if((k+1)==(col-1)&& (Mycinema[i][k+1]==null)&& (check==0)&& (DefaultStatus==false) &&(Ticket<col-1))//בדיקה אם נשאר כסא בודד
							 {
								check=Ticket;
								DefaultCol=j;
								DefaultRow=i;
								DefaultStatus=true;
								if(i!=row-1) {
								i++;
								j=-1;
								break;}
								else {break;}
							 }
							 else if((check==0) && (Ticket==col-1))// If he finds places and there is not a single place left
							 {
								 System.out.println(order.getName()+" Your seat is:");
								 while(Ticket!=0)//Seated after the test 
									{
									 if(order.getTicket()==Ticket)
									 {
										 order.setRow(i);
										 order.setChair(j);
										 order.setStatusseat(true);
									 }
										Mycinema[i][j]=order;
										System.out.println(" Row number-" + i + " In chair number- "+ j);
										j++;
										Ticket--;
									}
									return true; 
							 }
						 }
					 
					 }
					 if((check==0) && (Ticket!=col-1)) {
						 System.out.println(order.getName()+" Your seat is:");
						while(Ticket!=0)//Seated after the test 
						{
							 if(order.getTicket()==Ticket)
							 {
								 order.setRow(i);
								 order.setChair(j);
								 order.setStatusseat(true);
							 }
							Mycinema[i][j]=order;
							System.out.println(" Row number-" + i + " In chair number- "+ j);
							j++;
							Ticket--;
						}
						return true; 
					 }
					 else
					 {check=Ticket;}
			  }	
		}
			 }
			 else {
				 SingelStatus=SingelTicket( Mycinema, row, col,order);
				 if (SingelStatus==true)
				 {
					 return true;
				 }
			 }
			if((DefaultStatus==true) && (SingelStatus==false)) {
				System.out.println(order.getName()+" Your seat is:");
				while(Ticket!=0)//Seated after the test 
				{
					 if(order.getTicket()==Ticket)
					 {
						 order.setRow(DefaultRow);
						 order.setChair(DefaultCol);
						 order.setStatusseat(true);
					 }
					Mycinema[DefaultRow][DefaultCol]=order;
					System.out.println(" Row number-" + DefaultRow + " In chair number- "+ DefaultCol);
					DefaultCol++;
					Ticket--;
				}
				return true; 
			
			 }
			 else if(SingelStatus==false) {
				System.out.println("Sorry, there are no seats available for your tickets");
			
			}
			return false;	//No vacancies were found in the cinema
	}
		
		//Private case for one card
	public boolean SingelTicket(Order Mycinema[][],int row,int col, Order order) {
		for(int i=0; i<row; i++) {
			for(int j=0; j<col ; j++) {
				if( Mycinema[i][j]==null) {
					if(Mycinema[i][0]==null)
					{
						 if(order.getTicket()==Ticket)
						 {
							 order.setRow(i);
							 order.setChair(j);
							 order.setStatusseat(true);
						 }
							Mycinema[i][j]=order;
							System.out.println(order.getName()+" Your seat is:");
							System.out.println(" Row number-" + i + " In chair number- "+ j);
							return true;
					}
					if((j+1)==(col-1)&& (Mycinema[i][j+1]==null) && (DefaultStatus==false))//Check if there is a single chair left
					 {
						check=Ticket;
						DefaultCol=j;
						DefaultRow=i;
						DefaultStatus=true;
						break;
					 }
					if((j==col-1)&&(Mycinema[i][j-1]!=null)) {
						 if(order.getTicket()==Ticket)
						 {
							 order.setRow(i);
							 order.setChair(j);
							 order.setStatusseat(true);
						 }
						Mycinema[i][j]=order;
						System.out.println(order.getName()+" Your seat is:");
						System.out.println(" Row number-" + i + " In chair number- "+ j);
						return true;
					}
			}
		}
	}
		
		return false;
	}



}
