public class Score {
    private int score;

    public Score(int score){
        this.score = score;
    }

    //prints score
    public void printScore(){
        System.out.println(score);
    }

    //adds to score
    public void setScore(int score){
        this.score = score;
    }

    //sets score
    public int getScore(){
        return score;
    }
}
