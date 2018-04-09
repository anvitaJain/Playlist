package com.playlistExtractor.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.playlistExtractor.pojos.InputFileObject;
import com.pllaylistExtractor.utils.InvalidInputException;
import com.pllaylistExtractor.utils.Processor;
/*
 * Implementation for getting relevant playlists. 
 */
public class RelevantPlaylistImpl implements RelevantPlaylist{ 
	/*Wrote this main method to run the program locally for debugging purpose. 
	* Should not be shipped with jar.
	*/
	
	public static void main( String[] args ) throws IOException, InvalidInputException
    {
	   
	 Processor processor = new Processor();
	 InputFileObject inputObjectFromFile = processor.getModelFromFile("modelData.json");
	 
	 
	 System.out.println(inputObjectFromFile.toString());
	 System.out.println(processor.processPlaylist("MI3", "UK", inputObjectFromFile));
	 System.out.println(processor.processPlaylist("MI3", "US", inputObjectFromFile));
	 System.out.println(processor.processPlaylist("MI3", "CA", inputObjectFromFile));
	 System.out.println(processor.processPlaylist("StarWars", "IND", inputObjectFromFile));
    }
   
   public Set<ArrayList<String>> getRelevantPlaylist(String content, String country, String jsonFileInput) throws JsonParseException, JsonMappingException, NullPointerException, IOException, InvalidInputException {
	   Processor processor = new Processor();
	   InputFileObject inputObjectFromFile = processor.getModelFromFile(jsonFileInput);
	   return processor.processPlaylist(content, country, inputObjectFromFile);
   }
}
