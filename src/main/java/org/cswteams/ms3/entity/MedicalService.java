package org.cswteams.ms3.entity;

import lombok.Getter;
import lombok.Setter;
import org.cswteams.ms3.entity.soft_delete.SoftDeletableEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent a service offered by the hospital.
 * (E.g. In the hospital, we have that in the morning in the cardiology ward, we offer treatment)
 *
 * @see <a href="https://github.com/CSW-Teams/MS3/wiki#servizio">Glossary</a>.
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "medical_service")
public class MedicalService extends SoftDeletableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_service_id", nullable = false)
    private Long id;

    @NotNull
    @Setter
    private String label;

    /**
     * <i>Tasks</i> associated with this <i>Medical Service</i>.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    @Getter
    @Setter
    private List<Task> tasks = new ArrayList<>();
    // TODO: Load this information from a configuration file

    /**
     * Create a <i>Medical Service</i> with the specified parameters.
     *
     * @param tasks The list of the taskEnums offered in this service
     * @param label The medical service offered to the patient (oncology, cardiology, ecc...)
     */
    public MedicalService(List<Task> tasks, String label) {
        this.tasks = tasks;
        this.label = label;
    }

    /**
     * This constructor is useful for editing the instance in the persistence layer
     *
     * @param id    The id of the service
     * @param tasks The list of the taskEnums offered in this service
     * @param label The medical service offered to the patient (oncology, cardiology, ecc...)
     */
    public MedicalService(Long id, List<Task> tasks, String label) {
        this.id = id;
        this.tasks = tasks;
        this.label = label;
    }

    /**
     * Default constructor needed by Lombok
     */
    protected MedicalService() {
    }

    /**
     * Append new <i>tasks</i> for this <i>medical service</i>-
     *
     * @param tasks list of the new task to be added to the service
     */
    public void addTasks(List<Task> tasks) {
        for (Task t : tasks) {
            if (this.tasks.stream().noneMatch(o -> o.getTaskType().equals(t.getTaskType()))) {
                this.tasks.add(t);
            }
        }
    }
}
