package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private List<Routine> routines;
    private List<ActivityLog> activityLogs;

    public User() {
        this.routines = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
    }

    public User(int id, String name, String email, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.routines = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
    }

    public User(int id, String name, String email, String password, Date createdAt, Date updatedAt, List<Routine> routines, List<ActivityLog> activityLogs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.routines = routines != null ? routines : new ArrayList<>();
        this.activityLogs = activityLogs != null ? activityLogs : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }

    public List<ActivityLog> getActivityLogs() {
        return activityLogs;
    }

    public void setActivityLogs(List<ActivityLog> activityLogs) {
        this.activityLogs = activityLogs;
    }

    public void addRoutine(Routine routine) {
        if (routine != null) {
            this.routines.add(routine);
            routine.setUserId(this.id);
        }
    }

    public void addActivityLog(ActivityLog activityLog) {
        if (activityLog != null) {
            this.activityLogs.add(activityLog);
            activityLog.setUserId(this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", routinesCount=" + (routines != null ? routines.size() : 0) +
                ", activityLogsCount=" + (activityLogs != null ? activityLogs.size() : 0) +
                '}';
    }
}
