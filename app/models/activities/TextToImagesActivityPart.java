package models.activities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import models.ActivityPart;

@Entity
@DiscriminatorValue("TEXT_TO_IMAGES")
public class TextToImagesActivityPart extends ActivityPart {
	private static final long serialVersionUID = -984051952098826679L;

	public String text;

    @OneToMany(cascade=CascadeType.ALL) 
	public List<TextToImagesItem> activityItems;

}
