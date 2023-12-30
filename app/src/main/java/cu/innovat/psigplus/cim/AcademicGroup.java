package cu.innovat.psigplus.cim;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 30/10/23
 */
public class AcademicGroup {
    private String uuid;
    private String name;
    private String slug;

    /**
     *
     * @param uuid
     * @param name
     * @param slug
     */
    public AcademicGroup(String uuid, String name, String slug) {
        this.uuid = uuid;
        this.name = name;
        this.slug = slug;
    }

    /**
     *
     */
    public AcademicGroup() {
        this.uuid=null;
        this.name=null;
        this.slug=null;
    }

    /**
     *
     * @return
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
    @Override
    public String toString() {
        return "AcademicGroup{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
