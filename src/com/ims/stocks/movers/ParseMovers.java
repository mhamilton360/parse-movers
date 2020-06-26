package com.ims.stocks.movers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ParseMovers {

	public static void main(String[] args) {
		Path filePath = Paths.get("./data", "movers-jun-24.txt");
		 
		try (Stream<String> lines = Files.lines(filePath)) 
		{
		 
		     List<String> filteredLines = lines
	                    	.filter(s -> s.contains("(") && s.contains(":") && s.contains(")"))
	                    	.map(line -> {
	                    		int beginIndex = line.indexOf("(");
	                    		int endIndex = line.indexOf(")");
	                    		
	                    		return line.substring(beginIndex + 1, endIndex);
	                    		
	                    	})
	                    	.filter(s -> s.contains(":") && !s.contains("http"))
	                    	.map(line -> {
	                    		int beginIndex = line.indexOf(":");
	                    		return line.substring(beginIndex + 1);
	                    		
	                    	})
	                    	.collect(Collectors.toList());
		      
		     filteredLines.forEach(System.out::println);
		 
		} 
		catch (IOException e) {
		 
		    e.printStackTrace();
		}
	}
	
	public static boolean matchPattern(String line) {
		
		String patternString = ":";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(line);
        boolean matches = matcher.matches();
		
		return matches;
	}

}
