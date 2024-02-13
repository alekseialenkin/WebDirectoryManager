package ru.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class NamedNoteEntity extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;
    @NotBlank
    @Column(name = "note", nullable = false)
    protected String note;

    protected NamedNoteEntity() {
    }

    protected NamedNoteEntity(Integer id, String name, String note) {
        super(id);
        this.name = name;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '[' + name + " " + note + ']';
    }
}
