package com.hit.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.hit.controller.Controller;
import com.hit.info.MyResponse;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class OrderDetails1 {

	private JFrame frame;
	Controller controller;
	MyResponse Response;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetails1 window = new OrderDetails1(controller, Response);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderDetails1(Controller controller, MyResponse Response) {
		this.controller = controller;
		this.Response = Response;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titel = new JLabel("Your order was successfully placed");
		titel.setBounds(36, 89, 422, 33);
		frame.getContentPane().add(titel);

		// Prints the customer name
		String Name = Response.getOrder().getName();
		JLabel NameLabel = new JLabel(Name);
		NameLabel.setBounds(26, 28, 115, 33);
		frame.getContentPane().add(NameLabel);

		// Printing the seats according to the order
		int ticket = Response.getOrder().getTicket();
		int chair = Response.getOrder().getChair();
		int row = Response.getOrder().getRow();
		String Seating = null;
		if (ticket == 1) {// if i have 1 ticket i dont need to print "to" chair
			Seating = "Your seats are row number " + row + " in chair number " + chair;
		}
		if (ticket > 1) {
			Seating = "Your seats are row number " + row + " in chair number " + chair + " to " + (chair + ticket - 1);
		}
		JLabel SeatingLabel = new JLabel(Seating);
		SeatingLabel.setBounds(61, 160, 382, 63);
		frame.getContentPane().add(SeatingLabel);

		JButton CancelButton = new JButton("Cancel this order");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int Id = Response.getOrder().getId();
					MyResponse res = controller.DeleteOrder(Name, Id);
					JOptionPane.showMessageDialog(null, res.getReason());
					frame.dispose();
					Menu1 menu = new Menu1(controller, res);
					menu.main();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		CancelButton.setBounds(26, 251, 230, 41);
		frame.getContentPane().add(CancelButton);

		JButton BeckMenuButton = new JButton("Back to menu");
		BeckMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menu1 menu = new Menu1(controller, Response);
				menu.main();
			}
		});
		BeckMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		BeckMenuButton.setBounds(309, 251, 189, 41);
		frame.getContentPane().add(BeckMenuButton);

	}
}
