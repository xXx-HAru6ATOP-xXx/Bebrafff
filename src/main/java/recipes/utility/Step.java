package recipes.utility;

import java.util.List;

public class Step {
    public List<String> Steps;
    public List<String> imageUrls;

    public Step(List<String> steps, List<String> imageUrls) {
        this.Steps = steps;
        this.imageUrls = imageUrls;
    }

    public List<String> getSteps() {
        return Steps;
    }

    public void setSteps(List<String> steps) {
        Steps = steps;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
