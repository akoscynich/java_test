package model;

public class Issue {
    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Project getProject() {
        return project;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

}
