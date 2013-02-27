package models.activities;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class TextToImagesItem extends Model {
	private static final long serialVersionUID = 6370929822817527782L;

	@Id
	@Constraints.Min(10)
	public Long id;
	
	public String name;
	
	public String pictureUrl;
	
}
