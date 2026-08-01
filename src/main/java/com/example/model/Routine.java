package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Routine {
    private int id;
    private int userId;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private List<RoutineExercise> routineExercises;
    private List<ActivityLog> activityLogs;

    public Routine() {
        this.routineExercises = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
    }

    public Routine(int id, int userId, String name, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.routineExercises = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
    }

    public Routine(int id, int userId, String name, String description, Date createdAt, Date updatedAt, List<RoutineExercise> routineExercises, List<ActivityLog> activityLogs) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.routineExercises = routineExercises != null ? routineExercises : new ArrayList<>();
        this.activityLogs = activityLogs != null ? activityLogs : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<RoutineExercise> getRoutineExercises() {
        return routineExercises;
    }

    public void setRoutineExercises(List<RoutineExercise> routineExercises) {
        this.routineExercises = routineExercises;
    }

    public List<ActivityLog> getActivityLogs() {
        return activityLogs;
    }

    public void setActivityLogs(List<ActivityLog> activityLogs) {
        this.activityLogs = activityLogs;
    }

    public void addRoutineExercise(RoutineExercise routineExercise) {
        if (routineExercise != null) {
            this.routineExercises.add(routineExercise);
            routineExercise.setRoutineId(this.id);
        }
    }

    public void addActivityLog(ActivityLog activityLog) {
        if (activityLog != null) {
            this.activityLogs.add(activityLog);
            activityLog.setRoutineId(this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routine routine = (Routine) o;
        return id == routine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", routineExercisesCount=" + (routineExercises != null ? routineExercises.size() : 0) +
                ", activityLogsCount=" + (activityLogs != null ? activityLogs.size() : 0) +
                '}';
    }
}
