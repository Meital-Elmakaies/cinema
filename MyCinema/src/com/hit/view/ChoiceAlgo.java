package com.hit.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.hit.controller.Controller;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class ChoiceAlgo {

	private JFrame frame;
	private JTextField txtWelcomeToThe;
	private JTextField txtCinemaMy;
	private JTextField txtSelectTheAlgorithm;
	Controller controller;
	private JButton EXITButton;

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceAlgo window = new ChoiceAlgo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public ChoiceAlgo() throws UnknownHostException, IOException {
		initialize();
		this.controller = new Controller();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 556, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtWelcomeToThe = new JTextField();
		txtWelcomeToThe.setBackground(SystemColor.menu);
		txtWelcomeToThe.setText("Welcome to the");
		txtWelcomeToThe.setBounds(154, 28, 200, 39);
		frame.getContentPane().add(txtWelcomeToThe);
		txtWelcomeToThe.setColumns(10);

		txtCinemaMy = new JTextField();
		txtCinemaMy.setBackground(SystemColor.menu);
		txtCinemaMy.setText("cinema M&Y");
		txtCinemaMy.setBounds(180, 79, 159, 39);
		frame.getContentPane().add(txtCinemaMy);
		txtCinemaMy.setColumns(10);

		txtSelectTheAlgorithm = new JTextField();
		txtSelectTheAlgorithm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtSelectTheAlgorithm.setBackground(SystemColor.menu);
		txtSelectTheAlgorithm.setText("Select the algorithm you want to use");
		txtSelectTheAlgorithm.setBounds(88, 176, 360, 39);
		frame.getContentPane().add(txtSelectTheAlgorithm);
		txtSelectTheAlgorithm.setColumns(10);

		// If you selected optimal sends to controller and opens the menu
		JButton OptimalButton = new JButton("Optimal");
		OptimalButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int Optimal = 2;
					controller.ChoiceAlgo(Optimal);
					frame.dispose();
					Menu1 menu = new Menu1(controller, null);
					menu.main();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		OptimalButton.setBounds(47, 285, 171, 41);
		frame.getContentPane().add(OptimalButton);

		// If you selected Naive sends to controller and opens the menu
		JButton NaiveButton = new JButton("Naive");
		NaiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int Naive = 1;
					controller.ChoiceAlgo(Naive);
					frame.dispose();
					Menu1 menu = new Menu1(controller, null);
					menu.main();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NaiveButton.setBounds(289, 285, 171, 41);
		frame.getContentPane().add(NaiveButton);

		// exit for ths system close the program
		EXITButton = new JButton("Exit");
		EXITButton.addActionListener(new ActionListener() {
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
		EXITButton.setBounds(168, 367, 171, 41);
		frame.getContentPane().add(EXITButton);
	}

}
