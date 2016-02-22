package mx.com.cceo.gymapp.Model;

/**
 * Created by Hugo on 10/28/2015.
 */
public class GymList {



    private int imageResource;
    private String name;

    public GymList(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
