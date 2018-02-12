package com.sapient.FileCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {

 
public static void listFilesForFolder(final File folder) throws FileNotFoundException {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
        	ProcessFile(fileEntry);
            System.out.println(fileEntry.getName());
        }
    }
}

 

	private static void ProcessFile(File fileEntry) throws FileNotFoundException {
	// TODO Auto-generated method stub
 
	  wordCount(fileEntry) ;
		
      countVowels(fileEntry);
	
		
}
	

 



	private static void countVowels(File fileEntry) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner  sc = new Scanner(new FileInputStream(fileEntry));
	    String fileContent = "";
	    while (sc.hasNext())
	    {
	        fileContent += sc.next() + " ";
	    }
	    sc.close();

	    char[] charArr = fileContent.toCharArray();
	    int counter = 0;
	    for (char c : charArr)
	    {
	        if(c == 'a' || c == 'e' ||c == 'i' ||c == 'o' ||c == 'u')
	        counter++;
	    }
	    System.out.println("The file " + fileEntry.getName() + " has AEIOU in order " + counter + " times");
	}



	private static void wordCount(File fileEntry) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner  sc = new Scanner(new FileInputStream(fileEntry));
		 int count=0;
		    while(sc.hasNext()){
		        sc.next();
		        count++;
		    }
		System.out.println("Number of words: " + count);
		
		
	}



	public static void main(String[] args) throws FileNotFoundException {
		
	
	File folder = new File("C:\\Users\\rasati\\Documents\\File");
	/*File[] listOfFiles = folder.listFiles();

	for (File file : listOfFiles) {
	    if (file.isFile()) {
	        System.out.println(file.getName());
	    }
	}*/
	listFilesForFolder(folder);
}
}
