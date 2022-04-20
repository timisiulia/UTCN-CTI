package bussinessLayer;

import java.util.Objects;

public class BaseProduct extends MenuItem {

    private String title;
    private float rating;
    private float calories;
    private float proteins;
    private float fats;
    private float sodium;
    private float price;

    public BaseProduct(String title, float rating, float calories, float proteins, float fats, float sodium, float price) {

        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;


    }

    public BaseProduct() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Float.compare(that.rating, rating) == 0 && Float.compare(that.calories, calories) == 0 && Float.compare(that.proteins, proteins) == 0 && Float.compare(that.fats, fats) == 0 && Float.compare(that.sodium, sodium) == 0 && Float.compare(that.price, price) == 0 && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, calories, proteins, fats, sodium, price);
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", sodium=" + sodium +
                ", price=" + price +
                System.lineSeparator();
    }
}
