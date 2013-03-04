package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class ActivityPart extends Model {
	private static final long serialVersionUID = 5064213707081067119L;

	@Id
	@Constraints.Min(10)
	public Long id;
	
	public String title;
	
	public String extra;
	
    @OneToMany(cascade=CascadeType.ALL) 
	public List<ActivityResource> activityItems;

}
