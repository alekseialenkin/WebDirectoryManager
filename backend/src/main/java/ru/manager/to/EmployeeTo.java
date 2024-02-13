package ru.manager.to;

public class EmployeeTo {
    private String name;
    private String note;
    private Integer id;
    private String profession;
    private String department;

    public EmployeeTo() {
    }

    public EmployeeTo(String name, String note, Integer id, String profession, String department) {
        this.name = name;
        this.note = note;
        this.id = id;
        this.profession = profession;
        this.department = department;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "profession=" + profession +
                ", department=" + department +
                '}';
    }
}
