package entity;

public class Section implements Entity {
    private long idsection;
    private long idconference;
    private String title;

    public Section(long idsection, long idconference, String title) {
        this.idsection = idsection;
        this.idconference = idconference;
        this.title = title;
    }

    public long getIdSection() {
        return idsection;
    }

    public void setIdSection(long idsection) {
        this.idsection = idsection;
    }

    public long getIdConference() {
        return idconference;
    }

    public void setIdConference(long idconference) {
        this.idconference = idconference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (idsection != section.idsection) return false;
        if (idconference != section.idconference) return false;
        return title != null ? title.equals(section.title) : section.title == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idsection ^ (idsection >>> 32));
        result = 31 * result + (int) (idconference ^ (idconference >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Section{" +
                "idsection=" + idsection +
                ", idconference=" + idconference +
                ", title='" + title + '\'' +
                '}';
    }
}
