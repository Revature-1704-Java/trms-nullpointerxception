package com.revature.util;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionUtilTest {
	
	

	@Test
	public void connectionTest() throws SQLException, IOException, ClassNotFoundException {
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		connectionUtil.getConnection();
	}
	
}
