package bussinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeProduct extends BaseProduct {


    String title;


    private List<MenuItem> food;

    public CompositeProduct() {
        food = new ArrayList<>();
    }

    public CompositeProduct(String title, List<MenuItem> morefood) {
        this.title = title;
        food = morefood;
    }

    ;

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "\n title='" + title + '\'' +
                ", \nfood=" + food.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeProduct that = (CompositeProduct) o;
        return Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food);
    }
}
