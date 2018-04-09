package com.playlistExtractor.appTest;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.playlistExtractor.app.RelevantPlaylistImpl;
import com.pllaylistExtractor.utils.InvalidInputException;

public class RelevantPlaylistImplTest extends RelevantPlaylistImpl{
	
	@Test
	public void getRelevantPlaylistTest() throws InvalidInputException, JsonParseException, JsonMappingException, IOException {
		RelevantPlaylistImplTest relevantPlaylist = new RelevantPlaylistImplTest();
		Set<ArrayList<String>> playlist = relevantPlaylist.getRelevantPlaylist("MI3", "US", "modelData.json");
		assertNotNull(playlist);
	}
	
	@Test(expected = NullPointerException.class)
	public void getRelevantPlaylistTestException() throws InvalidInputException, JsonParseException, JsonMappingException, IOException {
		RelevantPlaylistImplTest relevantPlaylist = new RelevantPlaylistImplTest();
		Set<ArrayList<String>> playlist = relevantPlaylist.getRelevantPlaylist("MI3", "US", "modelData1.json");
		assertNotNull(playlist);
	}
	
	@Test(expected = InvalidInputException.class)
	public void getRelevantPlaylistTestException1() throws InvalidInputException, JsonParseException, JsonMappingException, IOException {
		RelevantPlaylistImplTest relevantPlaylist = new RelevantPlaylistImplTest();
		relevantPlaylist.getRelevantPlaylist("", "US", "incorrect.json");
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void getRelevantPlaylistTestException2() throws InvalidInputException, JsonParseException, JsonMappingException, IOException {
		RelevantPlaylistImplTest relevantPlaylist = new RelevantPlaylistImplTest();
		relevantPlaylist.getRelevantPlaylist("", "", "incorrect.json");
		
	}
	
	
}
