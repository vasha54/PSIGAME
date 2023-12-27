package cu.innovat.psigplus.cim;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 17/12/23
 */
public class Player {
    private String idUser;
    private String name;
    private String surname;
    private String ci;
    private String idGroup;
    private String slugGroup;
    private String IMEI;
    private String numberPhone;
    private boolean activate;

    public Player(String idUser, String name, String surname, String ci,
                  String slugGroup, String IMEI, String numberPhone, boolean activate) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.ci = ci;
        this.slugGroup = slugGroup;
        this.IMEI = IMEI;
        this.numberPhone = numberPhone;
        this.activate = activate;
    }

    public Player(){
        this.idUser = null;
        this.name = null;
        this.surname = null;
        this.ci = null;
        this.slugGroup = null;
        this.IMEI = null;
        this.numberPhone = null;
        this.idGroup = null;
        this.activate = false;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getSlugGroup() { return slugGroup;}

    public void setSlugGroup(String slugGroup) {this.slugGroup = slugGroup;}
}
