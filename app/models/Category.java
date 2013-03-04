package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;

@Entity
public class Category {

	@Id
	@Constraints.Min(10)
	public Long id;
	
	public String name;

	public String description;
	
}
