package main.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public final class JsonHelper {

	public static String mashalObjectIntoJson(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);

		try {
			File dir = new File("tmp/json");
			dir.mkdirs();
			FileWriter file = new FileWriter("tmp/json/berrys.txt");
			file.write(json);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;
	}

}
