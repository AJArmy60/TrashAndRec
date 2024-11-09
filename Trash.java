public class Trash {
    private Boolean isRecycle;     // Type of trash, e.g., "Plastic", "Paper", "Metal"
    private String correctBin; // The correct bin for this item, e.g., "Recycling" or "Garbage"
    private int position;    // Position on the screen for timing
    private boolean sorted;  // Whether the item has been sorted or not

    // Constructor
    public Trash(Boolean isRecycle, String correctBin, int position) {
        this.isRecycle = isRecycle;
        this.correctBin = correctBin;
        this.position = position;
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

    // Update position over time to simulate movement
    public void updatePosition(int speed) {
        this.position += speed;  // Adjust position according to game speed
    }

    // Getters and Setters
    public Boolean getIsRecycle() {
        return isRecycle;
    }

    public String getCorrectBin() {
        return correctBin;
    }

    public int getPosition() {
        return position;
    }

    public boolean isSorted() {
        return sorted;
    }
}
