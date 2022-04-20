package bussinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public abstract String getTitle();

    public abstract float getRating();

    public abstract float getCalories();

    public abstract float getProteins();

    public abstract float getFats();

    public abstract float getSodium();

    public abstract float getPrice();

    public abstract void setRating(float a);

    public abstract void setCalories(float a);

    public abstract void setProteins(float a);

    public abstract void setFats(float a);

    public abstract void setSodium(float a);

    public abstract void setPrice(float a);


}
