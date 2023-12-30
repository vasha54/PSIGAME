package cu.innovat.psigplus.cim.questions;

public class Sentence {
    private String text;
    private String slug;
    private String id;

    private boolean isCorrect;
    private boolean isSelect;

    /**
     *
     * @param text
     * @param slug
     * @param id
     */
    public Sentence(String text, String slug, String id) {
        this.text = text;
        this.slug = slug;
        this.id = id;
        this.isCorrect = false;
        this.isSelect = false;
    }

    /**
     *
     * @param text
     * @param slug
     * @param id
     * @param isCorrect
     */
    public Sentence(String text, String slug, String id, boolean isCorrect) {
        this.text = text;
        this.slug = slug;
        this.id = id;
        this.isCorrect = isCorrect;
        this.isSelect = false;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     *
     * @param correct
     */
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    /**
     *
     * @return
     */
    public boolean isSelect() { return isSelect; }

    /**
     *
     * @param select
     */
    public void setSelect(boolean select) { isSelect = select; }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Sentence{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
