package com.hit.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.hit.controller.Controller;
import com.hit.info.MyResponse;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.event.ActionEvent;

public class CencelOrder1 {

	private JFrame frame;
	Controller controller;
	MyResponse response;
	private JTextField NameField;
	private JTextField IdField;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CencelOrder1 window = new CencelOrder1(controller);
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
	public CencelOrder1(Controller controller) {
		this.controller = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 512, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel titel = new JLabel("Please fill in your details");
		titel.setBounds(88, 56, 297, 33);
		frame.getContentPane().add(titel);

		JLabel Namelabel = new JLabel("Name:");
		Namelabel.setBounds(26, 154, 115, 33);
		frame.getContentPane().add(Namelabel);

		JLabel Idlabel = new JLabel("Id:");
		Idlabel.setBounds(26, 229, 115, 33);
		frame.getContentPane().add(Idlabel);

		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(112, 151, 236, 39);
		frame.getContentPane().add(NameField);

		IdField = new JTextField();
		IdField.setColumns(10);
		IdField.setBounds(112, 226, 236, 39);
		frame.getContentPane().add(IdField);

		JButton Nextbutton = new JButton("Next");
		Nextbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name;
				int Id;
				Name = NameField.getText();
				Id = Integer.parseInt(IdField.getText());
				try {
					response = controller.DeleteOrder(Name, Id);
					JOptionPane.showMessageDialog(null, response.getReason());
					// If managed to delete the user returns to the main menu
					if (response.isSuccess()) {
						frame.dispose();
						Menu1 menu = new Menu1(controller, response);
						menu.main();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Nextbutton.setBounds(283, 308, 171, 41);
		frame.getContentPane().add(Nextbutton);

		JButton Backbutton = new JButton("Back");
		Backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Menu1 menu = new Menu1(controller, null);
				menu.main();
			}
		});
		Backbutton.setBounds(36, 308, 171, 41);
		frame.getContentPane().add(Backbutton);
	}

}
