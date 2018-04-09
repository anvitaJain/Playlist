package com.playlistExtractor.utilsTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlistExtractor.pojos.Attributes;
import com.playlistExtractor.pojos.Content;
import com.playlistExtractor.pojos.InputFileObject;
import com.playlistExtractor.pojos.Preroll;
import com.playlistExtractor.pojos.Video;
import com.pllaylistExtractor.utils.InvalidInputException;
import com.pllaylistExtractor.utils.Processor;

public class ProcessorTest extends Processor{
	
	private static InputFileObject ip = new InputFileObject();
	
	@Before
	public void setupIP() {
	
		
		Set<String> c1 = new HashSet<>();
		c1.add("US");
		c1.add("UK");
		c1.add("CA");
		Attributes attr1 = new Attributes();
		attr1.setCountries(c1);
		attr1.setLanguage("English");
		
		
		Set<String> c2 = new HashSet<>();
		c2.add("CA");
		Attributes attr2 = new Attributes();
		attr2.setCountries(c2);
		attr2.setLanguage("French");
		
		
		Set<String> c3 = new HashSet<>();
		c3.add("US");
		Attributes attr3 = new Attributes();
		attr3.setCountries(c3);
		attr3.setLanguage("Spanish");
		
		Set<String> c4= new HashSet<>();
		c4.add("US");
		Attributes attr4 = new Attributes();
		attr4.setCountries(c4);
		attr4.setLanguage("English");
		
		Set<String> c5 = new HashSet<>();
		c5.add("CA");
		Attributes attr5 = new Attributes();
		attr5.setCountries(c5);
		attr5.setLanguage("French");
		
		Set<String> c6 = new HashSet<>();
		c6.add("US");
		Attributes attr6 = new Attributes();
		attr6.setCountries(c6);
		attr6.setLanguage("Spanish");
		
		Video v1 = new Video();
		v1.setName("V1");		
		v1.setAttributes(attr1);
		
		
		Video v2 = new Video();
		v2.setName("V2");		
		v2.setAttributes(attr2);
		
		
		Video v3 = new Video();
		v3.setName("V3");		
		v3.setAttributes(attr3);
		
		
		Video v4 = new Video();
		v4.setName("V4");		
		v4.setAttributes(attr4);
		
		Video v5 = new Video();
		v5.setName("V5");		
		v5.setAttributes(attr5);
		
		Video v6 = new Video();
		v6.setName("V6");		
		v6.setAttributes(attr6);
		
		
		
		Preroll pre1 = new Preroll();
		pre1.setName("WB1");
		
		Preroll pre2 = new Preroll();
		pre2.setName("WB1");
		pre2.setVideo(Arrays.asList(v4,v5,v6));
		
		
		Content con = new Content();
		con.setName("MI3");
		con.setPreroll(Arrays.asList(pre1));
		con.setVideos(Arrays.asList(v1,v2,v3));
		
		ip.setContent(Arrays.asList(con));
		ip.setPreroll(Arrays.asList(pre2));
	}
	
		
	public void printJson(InputFileObject ip) throws JsonProcessingException {
		ObjectMapper mapper= new ObjectMapper();
		System.out.println(mapper.writeValueAsString(ip));		
	}
	
	@Test
	public void getModelFromFileTest() throws JsonParseException, JsonMappingException, IOException {
		InputFileObject modelFromFile = new Processor().getModelFromFile("modelData.json");
		assertNotNull(modelFromFile);
		assertTrue(modelFromFile.equals(ip));
	}
	
	@Test
	public void getModelFromFileTestFail() throws JsonParseException, JsonMappingException, IOException {
		InputFileObject modelFromFile = new Processor().getModelFromFile("incorrect.json");
		assertFalse(modelFromFile.equals(ip));
	}
	@Test(expected = Exception.class)
	public void getModelFromFileTestException() throws JsonParseException, JsonMappingException, IOException {
		new Processor().getModelFromFile("blabla.json");
	}
	
	@Test()
	public void processPlaylistTest() throws InvalidInputException {
		Collection<ArrayList<String>> playlist = new Processor().processPlaylist("MI3", "UK", ip);
		assertTrue(playlist.isEmpty());
	}
	@Test()
	public void processPlaylistTest1() throws InvalidInputException {
		Collection<ArrayList<String>> playlist  = new Processor().processPlaylist("MI3", "US", ip);
		assertFalse(playlist.isEmpty());
		Assert.assertArrayEquals(getExpectedList("V4", "V1", "V6", "V3").toArray(), playlist.toArray());
		
	}
	
	@Test
	public void processPlaylistTestException() throws InvalidInputException, JsonParseException, JsonMappingException, IOException {
		InputFileObject modelFromFile = new Processor().getModelFromFile("ExtendedInput.json");
		Collection<ArrayList<String>> playlist  = new Processor().processPlaylist("StarWars", "IND", modelFromFile);
		assertFalse(playlist.isEmpty());
	}

	@SuppressWarnings("unchecked")
	private Collection<ArrayList<String>> getExpectedList(String v1, String v2, String v3, String v4) {
		Collection<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		List<String> expectedList1 = new ArrayList<>();
		expectedList1.addAll(Arrays.asList(v1, v2));
		List<String> expectedList2 = new ArrayList<>();
		expectedList2.addAll(Arrays.asList(v3, v4));
		expected.addAll((Collection<? extends ArrayList<String>>) Arrays.asList(expectedList1,expectedList2));
		return expected;
	}
}
