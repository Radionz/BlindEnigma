package main.java.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Dorian Blanc
 */
public abstract class Parser {

	private static JSONObject jso = new JSONObject();
	private static JSONParser jsp = new JSONParser();

	@SuppressWarnings({ "resource" })
	public static JSONObject getFromFile(String fileUrl) throws IOException {
		FileReader file = new FileReader(fileUrl); // open the file
		Object obj = null;
		try {
			file = new FileReader(fileUrl);
			obj = jsp.parse(file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
		if (obj != null) {
			return (JSONObject) obj;
		}
		file.close();
		return null;
	}

	/**
	 * If needed, flush the Json Object.
	 */
	public static void flushJSON() {
		// flush JSON objects to avoid overflow on different files
		jso = null;
		jsp = null;
		jso = new JSONObject();
		jsp = new JSONParser();
	}

}
