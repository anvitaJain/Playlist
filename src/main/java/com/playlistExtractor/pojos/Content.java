package com.playlistExtractor.pojos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Content {
	private String name;
	private List<Preroll> preroll = new ArrayList<Preroll>();
	private List<Video> videos = new ArrayList<Video>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Preroll> getPreroll() {
		return preroll;
	}

	public void setPreroll(List<Preroll> preroll) {
		this.preroll = preroll;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((preroll == null) ? 0 : preroll.hashCode());
		result = prime * result + ((videos == null) ? 0 : videos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (preroll == null) {
			if (other.preroll != null)
				return false;
		} else if (!preroll.equals(other.preroll))
			return false;
		if (videos == null) {
			if (other.videos != null)
				return false;
		} else if (!videos.equals(other.videos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		 return "Content [name=" + name + ", preroll=" + preroll + ", videos=" + videos + "]";

//		StringBuilder sb = new StringBuilder();
//		sb.append("***CONTENT*****" + "\n");
//		sb.append("name=" + getName() + "\n");
//		sb.append("prerolls=" + Arrays.toString(getPreroll().toArray()) + "\n");
//		sb.append("videos=" + Arrays.toString(getVideos().toArray()) + "\n");
//
//		return sb.toString();

	}

}
