package com.akamai.edgegrid.auth;

import java.util.List;


/**
 *  POJO for preparing Post Data JSON for Akamai Purge Call
 */
public class AkamaiPostData{
	
	
	/** The hostname. */
	private String hostname;
	
	/** The objects. */
	private List<String> objects;
	
	
	/**
	 * Gets the hostname.
	 *
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	
	/**
	 * Sets the hostname.
	 *
	 * @param hostname the new hostname
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	/**
	 * Gets the objects.
	 *
	 * @return the objects
	 */
	public List<String> getObjects() {
		return objects;
	}
	
	/**
	 * Sets the objects.
	 *
	 * @param objects the new objects
	 */
	public void setObjects(List<String> objects) {
		this.objects = objects;
	}
	

}
