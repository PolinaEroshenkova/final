package db.dao.sectionentry.entity;

public class SectionEntry {
    private Long idsection;
    private Long identry;

    public SectionEntry(Long identry, Long idsection) {
        this.identry = identry;
        this.idsection = idsection;
    }

    public Long getIdsection() {
        return idsection;
    }

    public void setIdsection(Long idsection) {
        this.idsection = idsection;
    }

    public long getIdentry() {
        return identry;
    }

    public void setIdentry(Long identry) {
        this.identry = identry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionEntry that = (SectionEntry) o;

        if (idsection != null ? !idsection.equals(that.idsection) : that.idsection != null) return false;
        return identry != null ? identry.equals(that.identry) : that.identry == null;
    }

    @Override
    public int hashCode() {
        int result = idsection != null ? idsection.hashCode() : 0;
        result = 31 * result + (identry != null ? identry.hashCode() : 0);
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
