/**
 * Created by yushi on 8/30/14.
 */
public enum GameMode {
    STORY("1"),
    FIGHT("2");

    private final String modeNumber;

    GameMode(String modeNumber){
        this.modeNumber = modeNumber;
    }

    public String getModeNumber() {
        return modeNumber;
    }
}
