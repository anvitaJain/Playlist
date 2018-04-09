package com.playlistExtractor.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pllaylistExtractor.utils.InvalidInputException;
/*
 * This is the interface to be exposed outside this library for integration with other applications. 
 */
public interface RelevantPlaylist {
	   public Set<ArrayList<String>> getRelevantPlaylist(String content, String country, String jsonFileInput) throws JsonParseException, JsonMappingException, NullPointerException, IOException, InvalidInputException;

}
