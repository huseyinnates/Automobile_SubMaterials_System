// Java program to demonstrate
// adding elements to Hashtable

import java.io.*;
import java.util.*;

class test {
	public static void main(String args[])
	{
		// No need to mention the
		// Generic type twice
		Hashtable<Integer, String> ht1 = new Hashtable<>(4);

		// Initialization of a Hashtable
		// using Generics
		Hashtable<Integer, String> ht2
			= new Hashtable<Integer, String>(2);

		// Inserting the Elements
		// using put() method
		ht1.put(1, "y");
		ht1.put(2, "yy");
		ht1.put(3, "y");
                ht1.put(3, "y");
                ht1.put(3, "y");
		ht2.put(4, "y");
		ht2.put(5, "y");
		ht2.put(6, "six");

		// Print mappings to the console
		System.out.println("Mappings of ht1 : " + ht1);
		System.out.println("Mappings of ht2 : " + ht2);
	}
}
