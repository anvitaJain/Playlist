package com.pllaylistExtractor.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlistExtractor.pojos.Content;
import com.playlistExtractor.pojos.InputFileObject;
import com.playlistExtractor.pojos.Playlist;
import com.playlistExtractor.pojos.Preroll;
import com.playlistExtractor.pojos.Video;
/*
 * This class does the actual processing needed to get the relevant playlist
 * Steps: 
 * 1. Get the Model from the json file located in resource folder. 
 * 2. Start Processing if the input file is correctly parsed to java object
 * 2.a validate user input
 * 2.b process the input file to get relevant playlists in ordered way. 
 * 2.c return the list of ordered videos to form the playlist. 
 */
public class Processor {

	public InputFileObject getModelFromFile(String fileName) throws JsonParseException, JsonMappingException, IOException, NullPointerException {
		File inputFile = new File(this.getClass().getClassLoader().getResource(fileName).getFile());
		return new ObjectMapper().readValue(inputFile, InputFileObject.class);
	}

	public Set<ArrayList<String>> processPlaylist(String contentNameFromUser, String countryFromUser, InputFileObject inputData) throws InvalidInputException {
		
		validateInput(contentNameFromUser, countryFromUser, inputData);
		Playlist playlist = new Playlist();
		Set<ArrayList<String>> set = new HashSet<ArrayList<String>>(processContents(contentNameFromUser, countryFromUser, inputData).values());
		playlist.setPlaylist(set);
		return playlist.getPlaylist();
	}

	private Map<String, ArrayList<String>> processContents(String contentNameFromUser, String countryFromUser, InputFileObject inputData) {
		Map<String, ArrayList<String>> videoMap = new LinkedHashMap<>();

		List<Content> contents = inputData.getContent().stream().filter(c -> c.getName().equals(contentNameFromUser)).collect(Collectors.toList());
		if (!contents.isEmpty()) {
			List<String> prerollNamesFromContent = contents.get(0).getPreroll().stream().map(Preroll::getName)
					.collect(Collectors.toList());
			List<Preroll> prerollsToProcessFromFile = inputData.getPreroll().stream()
					.filter(p -> prerollNamesFromContent.contains(p.getName())).collect(Collectors.toList());
			for (Preroll preroll : prerollsToProcessFromFile) {
				createMapFromVideos(countryFromUser, preroll.getVideos(), true, videoMap);
			}
			createMapFromVideos(countryFromUser, contents.get(0).getVideos(), false, videoMap);
		}
		
		return videoMap;
	}

	private Map<String, ArrayList<String>> createMapFromVideos(String country, List<Video> videos, boolean isPreroll,
			Map<String, ArrayList<String>> videoMap) {
		for (Video video : videos) {
			populateVideoMap(country, video, isPreroll, videoMap);
		}
		return videoMap;
	}

	private void populateVideoMap(String country, Video video, boolean isPreroll, Map<String, ArrayList<String>> videoMap) {
		if (video.getAttributes().getCountries().contains(country)) {
			String key = generateKey(country, video);
			if (videoMap.containsKey(key.toString())) {
				videoMap.get(key.toString()).add(video.getName());
			} else if (!videoMap.containsKey(key.toString()) && isPreroll == true) {
				ArrayList<String> videoName = new ArrayList<>();
				videoName.add(video.getName());
				videoMap.put(key.toString(), videoName);
			}
		}
	}

	private String generateKey(String country, Video video) {
		StringBuilder key = new StringBuilder();
		key.append(country).append("_").append(video.getAttributes().getLanguage());
		key.toString();
		return key.toString(); 
	}

	private void validateInput(String contentNameFromUser, String countryFromUser, InputFileObject inputData)
			throws InvalidInputException {
		if (contentNameFromUser == null || contentNameFromUser.isEmpty() || countryFromUser == null
				|| countryFromUser.isEmpty() || inputData == null)
			throw new InvalidInputException("Invalid Input, check the input");

	}
}
