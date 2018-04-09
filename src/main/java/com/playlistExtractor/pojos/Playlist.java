package com.playlistExtractor.pojos;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {
	
	private Set<ArrayList<String>> playlist = new HashSet<ArrayList<String>>();
	
	public Set<ArrayList<String>> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Set<ArrayList<String>> playlist) {
		this.playlist = playlist;
	}
}