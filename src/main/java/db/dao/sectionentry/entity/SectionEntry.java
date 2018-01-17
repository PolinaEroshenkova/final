package db.dao.sectionentry.entity;

public class SectionEntry {
    private long idsection;
    private long identry;

    public SectionEntry(long identry, long idsection) {
        this.identry = identry;
        this.idsection = idsection;
    }

    public SectionEntry(long idsection) {
        this.idsection = idsection;
    }

    public long getIdsection() {
        return idsection;
    }

    public void setIdsection(long idsection) {
        this.idsection = idsection;
    }

    public long getIdentry() {
        return identry;
    }

    public void setIdentry(long identry) {
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
