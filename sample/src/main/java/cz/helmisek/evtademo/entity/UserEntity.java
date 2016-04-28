package cz.helmisek.evtademo.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserEntity
{

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("email")
	@Expose
	private String email;


	/**
	 * No args constructor for use in serialization
	 */
	public UserEntity()
	{
	}


	/**
	 * @param id
	 * @param username
	 * @param email
	 * @param name
	 */
	public UserEntity(Integer id, String name, String username, String email)
	{
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
	}


	/**
	 * @return The id
	 */
	public Integer getId()
	{
		return id;
	}


	/**
	 * @param id The id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}


	/**
	 * @return The name
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * @param name The name
	 */
	public void setName(String name)
	{
		this.name = name;
	}


	/**
	 * @return The username
	 */
	public String getUsername()
	{
		return username;
	}


	/**
	 * @param username The username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}


	/**
	 * @return The email
	 */
	public String getEmail()
	{
		return email;
	}


	/**
	 * @param email The email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

}