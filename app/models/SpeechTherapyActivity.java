package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class SpeechTherapyActivity extends Model {
	private static final long serialVersionUID = 9201739339411054180L;

	public enum Type {
		TEXT_TO_IMAGES
	}
	
	@Id
	@Constraints.Min(10)
	public Long id;

	@Constraints.Required
	public String name;

	@Constraints.Required
	public String description;

	@Constraints.Required
	public String pictureUrl;

	@Constraints.Required
	public Type type;

	@Constraints.Required
	public Category category;

	@Constraints.Required
	public Date creationDate = new Date();

	@Constraints.Required
	public Date lastModificationDate = new Date();

    @OneToMany(cascade=CascadeType.ALL) 
	public List<ActivityPart> parts;

    @Override
    public void delete() {
    	for (ActivityPart part : parts) {
			part.delete();
		}
    	super.delete();
    }
	public static Finder<Long, SpeechTherapyActivity> find = new Finder<Long, SpeechTherapyActivity>(
			Long.class, SpeechTherapyActivity.class);

}