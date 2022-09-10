package com.hit.algorithm;



public class INaiveAlgoOfTheAlgoFamily implements IAlgoSeatingArrangement{
	private int check; 
	//private String FullName; 
	private int Ticket; 
	
	public boolean SeatedMyCinema(Order order,Order Mycinema[][],int row,int col) {
		Ticket=order.getTicket();
		 check = Ticket;
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
					 }
				 }
				 if(check==0) {
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
		if(check!=0) {
			System.out.println("Sorry, there are no seats available for your tickets");
		
		}
		return false;	//No vacancies were found in the cinema
}
}

