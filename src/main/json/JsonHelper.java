package main.json;

import com.google.gson.Gson;

public final class JsonHelper {

	public static String mashalObjectIntoJson(Object object){
		Gson gson = new Gson();
		String json = gson.toJson(object);

		return json;
	}

}
