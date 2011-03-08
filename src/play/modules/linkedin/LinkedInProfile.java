package play.modules.linkedin;

import java.io.Serializable;

/**
 * The Class LinkedInProfile.
 * 
 * @author Felipe Oliveira (http://geeks.aretotally.in)
 * @copyright Felipe Oliveira (http://geeks.aretotally.in)
 */
public class LinkedInProfile implements Serializable {

	/** The id. */
	private String id;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The industry. */
	private String industry;

	/** The picture url. */
	private String pictureUrl;

	/** The headline. */
	private String headline;
	
	/** The access token. */
	private String accessToken;

	/**
	 * Instantiates a new linked in profile.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param industry the industry
	 * @param pictureUrl the picture url
	 * @param headline the headline
	 * @param accessToken the access token
	 */
	public LinkedInProfile(String id, String firstName, String lastName,
			String industry, String pictureUrl, String headline, String accessToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.industry = industry;
		this.pictureUrl = pictureUrl;
		this.headline = headline;
		this.accessToken = accessToken;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the industry.
	 * 
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * Sets the industry.
	 * 
	 * @param industry
	 *            the new industry
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * Gets the picture url.
	 * 
	 * @return the picture url
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * Sets the picture url.
	 * 
	 * @param pictureUrl
	 *            the new picture url
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * Gets the headline.
	 * 
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * Sets the headline.
	 * 
	 * @param headline
	 *            the new headline
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the id.
	 *
	 * @param accessToken the new access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	/**
	 * To String.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LinkedInController [id=" + getId() + ", firstName="
				+ getFirstName() + ", lastName=" + getLastName() + ", industry=" + getIndustry() + ", pictureUrl=" + getPictureUrl()
				 + ", headline=" + getHeadline() + ", accessToken=" + getAccessToken() + "]";
	}

}
