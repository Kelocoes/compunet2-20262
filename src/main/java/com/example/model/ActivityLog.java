package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ActivityLog {
    private int id;
    private int userId;
    private int routineId;
    private Date startedAt;
    private Date completedAt;
    private Date createdAt;
    private List<ActivityExercise> activityExercises;

    public ActivityLog() {
        this.activityExercises = new ArrayList<>();
    }

    public ActivityLog(int id, int userId, int routineId, Date startedAt, Date completedAt, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.routineId = routineId;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.activityExercises = new ArrayList<>();
    }

    public ActivityLog(int id, int userId, int routineId, Date startedAt, Date completedAt, Date createdAt, List<ActivityExercise> activityExercises) {
        this.id = id;
        this.userId = userId;
        this.routineId = routineId;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.activityExercises = activityExercises != null ? activityExercises : new ArrayList<>();
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

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<ActivityExercise> getActivityExercises() {
        return activityExercises;
    }

    public void setActivityExercises(List<ActivityExercise> activityExercises) {
        this.activityExercises = activityExercises;
    }

    public void addActivityExercise(ActivityExercise activityExercise) {
        if (activityExercise != null) {
            this.activityExercises.add(activityExercise);
            activityExercise.setActivityLogId(this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLog that = (ActivityLog) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", routineId=" + routineId +
                ", startedAt=" + startedAt +
                ", completedAt=" + completedAt +
                ", createdAt=" + createdAt +
                ", activityExercisesCount=" + (activityExercises != null ? activityExercises.size() : 0) +
                '}';
    }
}
