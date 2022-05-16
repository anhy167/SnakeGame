
package rananmoi;


public class User implements Comparable<User>{
    private String name;
    private String level;
    private String score;
    
    public User(String name, String level, String score) {
        this.name = name;
        this.level = level;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    
    @Override
    public String toString() {
        return this.name + "  " + this.level + "  " + this.score;
    }

    @Override
    public int compareTo(User o) {
        int a = Integer.parseInt(this.score);
        int b = Integer.parseInt(o.score);
        if (a > b) {
            return -1;
        }
        else if (a == b) {
            return 0;
        }
        return 1;
    }
}
