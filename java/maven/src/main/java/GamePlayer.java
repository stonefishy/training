/**
 * Created by yushi on 8/30/14.
 */
public class GamePlayer {
    private String userName;
    private int remainTimes;

    public GamePlayer(String userName, int remainTimes){
        this.userName = userName;
        this.setRemainTimes(remainTimes);
    }

    public int getRemainTimes() {
        return remainTimes;
    }

    public void setRemainTimes(int remainTimes) {
        this.remainTimes = remainTimes;
    }

    public String getUserName() {
        return userName;
    }

    public void subtractOneTime(){
        this.remainTimes--;
    }
}
