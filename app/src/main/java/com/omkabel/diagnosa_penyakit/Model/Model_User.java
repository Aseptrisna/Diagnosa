package com.omkabel.diagnosa_penyakit.Model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class Model_User implements Serializable {


	@SerializedName("id")
	private String id;

	@SerializedName("nama")
	private String nama;

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("updated_at")
	private String updatedAt;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"Model_User{" + 
			"id = '" + id + '\'' + 
			",nama = '" + nama + '\'' + 
			",email = '" + email + '\'' + 
			",password = '" + password + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			"}";
		}
}