package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.Product;

public class Program07 {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		String strPath = "C:\\temp\\in.csv";
		
		File path = new File(strPath);
		
		boolean success = new File(path.getParent() + "\\out").mkdir();
		
		String trgPath = path.getParent() + "\\out\\summary.csv";
		
		List<Product> list = new ArrayList<Product>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			
			String line = br.readLine();
			
			while (line != null) {
				String original = line;
				String[] vect = original.split(",");
				String name = vect[0];
				double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				
				Product product = new Product(name, price, quantity);
				
				list.add(product);
				line = br.readLine();
			}
			
			if (success == true) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(trgPath, true))){
				
					for (Product lists : list) {
						bw.write(lists.getName() + ',' + String.format("%.2f", lists.amount()));
						bw.newLine();
					}
				}
				catch(IOException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}			
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
