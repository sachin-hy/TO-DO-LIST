package com.packages.Model;

public class tasks {

	int id;
	int user_id;
	String title;
	String description;
	String created_at;
	String updated_at;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	
	@Override
	public String toString() {
		return "tasks [id=" + id + ", user_id=" + user_id + ", title=" + title + ", description=" + description
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + ", getId()=" + getId()
				+ ", getUser_id()=" + getUser_id() + ", getTitle()=" + getTitle() + ", getDescription()="
				+ getDescription() + ", getCreated_at()=" + getCreated_at() + ", getUpdated_at()=" + getUpdated_at()
				+ "]";
	}
	
}
