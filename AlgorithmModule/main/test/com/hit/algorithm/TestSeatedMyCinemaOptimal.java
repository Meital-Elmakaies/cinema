package com.hit.algorithm;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestSeatedMyCinemaOptimal {

	public IOptimalAlgoOfTheAlgoFamily junit= new IOptimalAlgoOfTheAlgoFamily();
	 public int row=3;
	 public int col=4;
	 public Order[][] Mock_Mycinema = new Order[row][col];
	 public int Numberofseatsavailabl=row*col;
	@Before
	public void setUp() {
	
		
			  for(int i=0; i<row; i++) {
				  for(int j=0; j<col; j++) {
					  Mock_Mycinema[i][j]=null;
				  }
			  }			 
			  Order order1=new Order(1);
				junit.SeatedMyCinema(order1 , Mock_Mycinema, row, col);
				  Order order2=new Order(2);
				junit.SeatedMyCinema(order2, Mock_Mycinema, row, col);
				  Order order3=new Order(4);
				junit.SeatedMyCinema(order3, Mock_Mycinema, row, col);
				  Order order4=new Order(3);
				junit.SeatedMyCinema(order4, Mock_Mycinema, row, col);
	}
	
	@Test
	public void TestMorePeopleThanChairs() {
		IOptimalAlgoOfTheAlgoFamily junit = new IOptimalAlgoOfTheAlgoFamily();
		Order order=new Order(4);
		boolean output = junit.SeatedMyCinema(order, Mock_Mycinema, row, col);
		assertEquals("Could not fill a larger number of people than the number of available seats",false,output);
	}
	
	@Test
	public void TestCheckingNaivety() {
		IOptimalAlgoOfTheAlgoFamily junit = new IOptimalAlgoOfTheAlgoFamily();
		Order order=new Order(2);
		boolean output = junit.SeatedMyCinema(order, Mock_Mycinema, row, col);
		assertEquals("Managed to sit in contrast to naive algo (treatment of the problem of edges)-algo optimal",true,output);
	}
	
	
	@Test
	public void TestCinemaFull() {
		IOptimalAlgoOfTheAlgoFamily junit = new IOptimalAlgoOfTheAlgoFamily();
		Order order=new Order(2);
		boolean output = junit.SeatedMyCinema(order, Mock_Mycinema, row, col);
		assertEquals("Succeeded to fill the cinema",true,output);
	}

}
