package models;

import static javax.persistence.DiscriminatorType.STRING;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PART_TYPE",  discriminatorType=STRING)
public abstract class ActivityPart extends Model {
	private static final long serialVersionUID = 5064213707081067119L;

	@Id
	@Constraints.Min(10)
	public Long id;

}
