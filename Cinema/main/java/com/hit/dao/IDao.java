package com.hit.dao;

import java.io.IOException;

import com.hit.algorithm.Order;

public interface IDao {
	void WriteToDB(Order order) throws IOException;

	boolean DeleteToDB(Order order);

	void CloseToDB();
}
