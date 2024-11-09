public class Trash {
    private Boolean isRecycle;     // Type of trash, e.g., "Plastic", "Paper", "Metal"
    private String correctBin; // The correct bin for this item, e.g., "Recycling" or "Garbage"
    private int Xposition;    // Xposition on the screen for timing
    private boolean sorted;  // Whether the item has been sorted or not
    private int Yposition = 700;

    // Constructor
    public Trash(Boolean isRecycle, String correctBin, int Xposition) {
        this.isRecycle = isRecycle;
        this.correctBin = correctBin;
        this.Xposition = Xposition;
        this.sorted = false;
    }

    // Check if the item is sorted correctly
    public boolean checkSorting(String chosenBin) {
        if (this.correctBin.equalsIgnoreCase(chosenBin)) {
            this.sorted = true;
            System.out.println("Correct! " + isRecycle + " goes in " + correctBin);
            return true;
        } else {
            System.out.println("Incorrect! " + isRecycle + " doesn't belong in " + chosenBin);
            return false;
        }
    }

    // Update Xposition over time to simulate movement
    public void updateXposition(int speed) {
        this.Xposition += speed;  // Adjust Xposition according to game speed
    }

    // Getters and Setters
    public Boolean getIsRecycle() {
        return isRecycle;
    }

    public String getCorrectBin() {
        return correctBin;
    }

    public int getXposition() {
        return Xposition;
    }

    public boolean isSorted() {
        return sorted;
    }
}
