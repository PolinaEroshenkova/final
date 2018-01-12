package entity;

public class SectionEntry {
    private long idsection;
    private long identry;

    public SectionEntry(long idsection, long identry) {
        this.idsection = idsection;
        this.identry = identry;
    }

    public long getIdSection() {
        return idsection;
    }

    public void setIdSection(long idsection) {
        this.idsection = idsection;
    }

    public long getIdEntry() {
        return identry;
    }

    public void setIdEntry(long identry) {
        this.identry = identry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionEntry that = (SectionEntry) o;

        if (idsection != that.idsection) return false;
        return identry == that.identry;
    }

    @Override
    public int hashCode() {
        int result = (int) (idsection ^ (idsection >>> 32));
        result = 31 * result + (int) (identry ^ (identry >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SectionEntry{" +
                "idsection=" + idsection +
                ", identry=" + identry +
                '}';
    }
}
