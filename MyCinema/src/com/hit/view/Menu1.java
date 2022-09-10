package com.hit.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hit.controller.Controller;
import com.hit.info.MyResponse;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Menu1 {

	private JFrame frame;
	private JTextField textField;
	Controller controller;
	MyResponse Response;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu1 window = new Menu1(controller, Response);
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
	public Menu1(Controller controller, MyResponse Response) {
		this.controller = controller;
		this.Response = Response;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 567, 496);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setText("Cinema M&Y");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		textField.setColumns(10);
		textField.setBackground(SystemColor.menu);
		textField.setBounds(160, 55, 194, 58);
		frame.getContentPane().add(textField);

		JButton button = new JButton("for a new order");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Response == null)) {
					if (Response.isLastStep()) {
						JOptionPane.showMessageDialog(null,
								"The cinema is full, it is no longer possible to book seats ");
					} else {
						frame.dispose();
						NewOrder1 neworder = new NewOrder1(controller);
						neworder.main();
					}
				} else {
					frame.dispose();
					NewOrder1 neworder = new NewOrder1(controller);
					neworder.main();
				}

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 21));
		button.setBounds(160, 159, 199, 41);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("To cancel an existing order");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CencelOrder1 cencel = new CencelOrder1(controller);
				cencel.main();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		button_1.setBounds(79, 234, 393, 41);
		frame.getContentPane().add(button_1);

		JButton Exitbutton = new JButton("Exit");
		Exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.CloseProgram("exit");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		Exitbutton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Exitbutton.setBounds(168, 339, 171, 41);
		frame.getContentPane().add(Exitbutton);
	}

}
