package com.hit.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.hit.controller.Controller;
import com.hit.info.MyResponse;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class NewOrder1 {

	private JFrame frame;
	private JTextField NameField;
	private JTextField IdField;
	private JTextField TicketField;
	Controller controller;
	MyResponse response;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewOrder1 window = new NewOrder1(controller);
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
	public NewOrder1(Controller controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 533, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titel = new JLabel("Please fill in your details");
		titel.setBounds(90, 60, 314, 33);
		frame.getContentPane().add(titel);

		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(26, 155, 107, 33);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Id:");
		label_2.setBounds(26, 215, 107, 33);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Ticket:");
		label_3.setBounds(26, 276, 107, 33);
		frame.getContentPane().add(label_3);

		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(119, 152, 236, 39);
		frame.getContentPane().add(NameField);

		IdField = new JTextField();
		IdField.setColumns(10);
		IdField.setBounds(119, 212, 236, 39);
		frame.getContentPane().add(IdField);

		TicketField = new JTextField();
		TicketField.setColumns(10);
		TicketField.setBounds(119, 276, 236, 39);
		frame.getContentPane().add(TicketField);

		JButton Nextbutton = new JButton("Next");
		Nextbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name;
				int Id, Ticket;
				Name = NameField.getText();
				Id = Integer.parseInt(IdField.getText());
				Ticket = Integer.parseInt(TicketField.getText());
				try {
					response = controller.ContinueOrdering(Name, Id, Ticket);
					if (response.isSuccess()) {
						frame.dispose();
						OrderDetails1 orderDetails = new OrderDetails1(controller, response);
						orderDetails.main();
					} else {
						JOptionPane.showMessageDialog(null, response.getReason());
						Menu1 menu = new Menu1(controller, response);
						menu.main();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Nextbutton.setBounds(286, 353, 171, 41);
		frame.getContentPane().add(Nextbutton);

		JButton Backbutton = new JButton("Back");
		Backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu1 menu = new Menu1(controller, null);
				menu.main();
			}
		});
		Backbutton.setBounds(36, 353, 171, 41);
		frame.getContentPane().add(Backbutton);
	}

}
