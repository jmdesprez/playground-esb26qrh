package jee.jpa.IdClass;

import java.io.Serializable;
import java.util.Objects;

public class ProjectId implements Serializable {
    private int departmentId;
    private long projectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId projectId1 = (ProjectId) o;
        return departmentId == projectId1.departmentId &&
                projectId == projectId1.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, projectId);
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
