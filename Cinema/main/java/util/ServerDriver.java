package util;

import java.io.IOException;
import com.hit.dao.DaoFileImpl;
import com.hit.server.Serverr;
import com.hit.service.CinemaService;

public class ServerDriver {
	public static void main(String[] args) throws IOException {
		int port = 20704;
		Serverr a = new Serverr(port);
		DaoFileImpl db = new DaoFileImpl("C:\\Users\\yuval\\eclipse-workspace\\Cinema\\Datasource.txt");
		CinemaService service = new CinemaService(db);
		try {
			a.run(service);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
