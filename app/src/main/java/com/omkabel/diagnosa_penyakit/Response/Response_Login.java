package com.omkabel.diagnosa_penyakit.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.omkabel.diagnosa_penyakit.Model.Model_User;

import java.io.Serializable;

public class Response_Login implements Serializable {

	@SerializedName("status")
	private boolean status;
	@SerializedName("user")
	private List<Model_User> user;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setUser(List<Model_User> user){
		this.user = user;
	}

	public List<Model_User> getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Response_Login{" + 
			"status = '" + status + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}