package jee.jpa;

import jee.jpa.IdClass.Project;
import jee.jpa.IdClass.ProjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

public class CompositeKeyTest {

    private EntityManager entityManager;
    private EntityManagerFactory projet;

    @BeforeEach
    public void init() {
        projet = Persistence.createEntityManagerFactory("projet");

        createEntityManager();
    }

    private void createEntityManager() {
        entityManager = projet.createEntityManager();
        entityManager.setFlushMode(FlushModeType.COMMIT);
    }

    @AfterEach
    public void close() {
        projet.close();
    }

    @Test
    public void testSimplePersist() {
        // GIVEN
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final Project toSave = new Project();
        toSave.setDepartmentId(0);
        toSave.setProjectId(0);
        toSave.setName("Mon projet");

        // WHEN
        entityManager.persist(toSave);
        final int departmentId = toSave.getDepartmentId();
        final long projectId = toSave.getProjectId();
        transaction.commit();

        // THEN
        // L'id doit avoir changé
        assertThat(departmentId).isNotEqualTo(0);
        assertThat(projectId).isNotEqualTo(0);

        // WHEN
        final ProjectId key = new ProjectId();
        key.setDepartmentId(departmentId);
        key.setProjectId(projectId);
        final Project foundProject = entityManager.find(Project.class, key);

        // THEN
        assertThat(foundProject.getDepartmentId()).isEqualTo(departmentId);
        assertThat(foundProject.getProjectId()).isEqualTo(projectId);
        assertThat(foundProject.getName()).isEqualTo("Mon projet");
    }


}
