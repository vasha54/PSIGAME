package cu.innovat.psigplus.cim.questions;

public class Sentence {
    private String text;
    private String slug;
    private String id;

    private boolean isCorrect;
    private boolean isSelect;

    public Sentence(String text, String slug, String id) {
        this.text = text;
        this.slug = slug;
        this.id = id;
        this.isCorrect = false;
        this.isSelect = false;
    }

    public Sentence(String text, String slug, String id, boolean isCorrect) {
        this.text = text;
        this.slug = slug;
        this.id = id;
        this.isCorrect = isCorrect;
        this.isSelect = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isSelect() { return isSelect; }

    public void setSelect(boolean select) { isSelect = select; }

    @Override
    public String toString() {
        return "Sentence{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
