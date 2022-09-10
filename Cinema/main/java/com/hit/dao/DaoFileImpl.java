package com.hit.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.hit.algorithm.Order;

public class DaoFileImpl implements IDao {

	File file;
	boolean DeleteCompleted;
	Order tempOrder;
	String path;

	public DaoFileImpl(String path) {
		this.tempOrder = new Order();
		this.DeleteCompleted = false;
		this.path = path;
		try {
			this.openFile(path); // open file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openFile(String path) throws FileNotFoundException {
		this.file = new File(path);
	}

	public void WriteToDB(Order order) throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(this.file, true));

		String Info = order.GetCsv();
		br.write(Info);
		br.write("\n");

		br.close();
	}

	public boolean DeleteToDB(Order order) {
		DeleteCompleted = false;
		String name = order.getName();
		int id = order.getId();
		File tempFile = new File("myTempFile.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;
//Reads each line from the file and compares it with the customer's line
			while ((currentLine = reader.readLine()) != null) {
				// trim newline when comparing with lineToRemove
				String trimmedLine = currentLine.trim();
				tempOrder.ImportCSVData(trimmedLine);
				if ((tempOrder.getName().equals(name)) && (tempOrder.getId() == id)) {
					DeleteCompleted = true;
					order.ImportCSVData(trimmedLine);
				} else {
					writer.write(currentLine + System.getProperty("line.separator"));
				}
			}
			writer.close();
			reader.close();
			this.file.delete();
			Files.move(tempFile.toPath(), tempFile.toPath().resolveSibling("DataSource.txt"));
			return DeleteCompleted;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// Close the file
	public void CloseToDB() {
		try {
			Files.deleteIfExists(Paths.get(this.path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
