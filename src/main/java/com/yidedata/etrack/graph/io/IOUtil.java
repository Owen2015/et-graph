package com.yidedata.etrack.graph.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IOUtil {

	public static List<ArrayList<String>> import2D(String path) throws IOException {
		
		BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
		String line;
		String token = " 	,";
		List<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

		line = bf.readLine();
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line, token);
			ArrayList<String> row = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				row.add(st.nextToken());
			}
			data.add(row);
			line = bf.readLine();
		}
		return data;
	}

	/*
	 * public static String[] import1D() {
	 * 
	 * }
	 */

	private static FileReader importfile(String path) throws FileNotFoundException {
		File file = new File(path);
		return new FileReader(file);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
