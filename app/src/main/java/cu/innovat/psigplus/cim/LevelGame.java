package cu.innovat.psigplus.cim;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/11/23
 */
public class LevelGame {
    private String uuid;
    private String name;
    private String slug;
    private int cardinal;

    private GameLevel level;

    public LevelGame(String uuid, String name, String slug, int cardinal) {
        this.uuid = uuid;
        this.name = name;
        this.slug = slug;
        this.cardinal = cardinal;
        this.level = GameLevel.values()[cardinal];
    }

    public LevelGame(String uuid, String name, String slug, int cardinal, GameLevel level) {
        this.uuid = uuid;
        this.name = name;
        this.slug = slug;
        this.cardinal = cardinal;
        this.level = level;
    }

    public LevelGame(String uuid, String name, String slug, GameLevel level) {
        this.uuid = uuid;
        this.name = name;
        this.slug = slug;
        this.level = level;
        this.cardinal = level.ordinal();
    }

    public LevelGame() {
        this.uuid = null;
        this.name = null;
        this.slug = null;
        this.cardinal = Integer.MIN_VALUE;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getCardinal() {
        return cardinal;
    }

    public void setCardinal(int cardinal) {
        this.cardinal = cardinal;
    }

    public GameLevel getLevel() { return level; }

    public void setLevel(GameLevel level) { this.level = level; }

    @Override
    public String toString() {
        return "LevelGame{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", cardinal=" + cardinal +
                '}';
    }
}
