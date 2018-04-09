package com.playlistExtractor.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class InputFileObject {

	private List<Content> content = new ArrayList<Content>();
	private List<Preroll> preroll = new ArrayList<Preroll>();
	public List<Content> getContent() {
		return content;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}
	public List<Preroll> getPreroll() {
		return preroll;
	}
	public void setPreroll(List<Preroll> preroll) {
		this.preroll = preroll;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((preroll == null) ? 0 : preroll.hashCode());
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
		InputFileObject other = (InputFileObject) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (preroll == null) {
			if (other.preroll != null)
				return false;
		} else if (!preroll.equals(other.preroll))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Input Details *****\n");
		sb.append("Content="+Arrays.toString(getContent().toArray())+"\n");
		sb.append("prerools="+Arrays.toString(getPreroll().toArray())+"\n");
		sb.append("*****************************");
		
		return sb.toString();
		
	}
	
}
