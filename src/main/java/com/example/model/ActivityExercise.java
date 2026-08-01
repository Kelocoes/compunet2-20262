package com.example.model;

import java.util.Date;
import java.util.Objects;

public class ActivityExercise {
    private int id;
    private int activityLogId;
    private int routineExerciseId;
    private int actualSets;
    private int actualReps;
    private float actualWeightKg;
    private int actualDurationMin;
    private float caloriesBurned;
    private float distanceCoveredKm;
    private Date startedAt;
    private Date completedAt;

    public ActivityExercise() {
    }

    public ActivityExercise(int id, int activityLogId, int routineExerciseId, int actualSets, int actualReps, float actualWeightKg, int actualDurationMin, float caloriesBurned, float distanceCoveredKm, Date startedAt, Date completedAt) {
        this.id = id;
        this.activityLogId = activityLogId;
        this.routineExerciseId = routineExerciseId;
        this.actualSets = actualSets;
        this.actualReps = actualReps;
        this.actualWeightKg = actualWeightKg;
        this.actualDurationMin = actualDurationMin;
        this.caloriesBurned = caloriesBurned;
        this.distanceCoveredKm = distanceCoveredKm;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityLogId() {
        return activityLogId;
    }

    public void setActivityLogId(int activityLogId) {
        this.activityLogId = activityLogId;
    }

    public int getRoutineExerciseId() {
        return routineExerciseId;
    }

    public void setRoutineExerciseId(int routineExerciseId) {
        this.routineExerciseId = routineExerciseId;
    }

    public int getActualSets() {
        return actualSets;
    }

    public void setActualSets(int actualSets) {
        this.actualSets = actualSets;
    }

    public int getActualReps() {
        return actualReps;
    }

    public void setActualReps(int actualReps) {
        this.actualReps = actualReps;
    }

    public float getActualWeightKg() {
        return actualWeightKg;
    }

    public void setActualWeightKg(float actualWeightKg) {
        this.actualWeightKg = actualWeightKg;
    }

    public int getActualDurationMin() {
        return actualDurationMin;
    }

    public void setActualDurationMin(int actualDurationMin) {
        this.actualDurationMin = actualDurationMin;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public float getDistanceCoveredKm() {
        return distanceCoveredKm;
    }

    public void setDistanceCoveredKm(float distanceCoveredKm) {
        this.distanceCoveredKm = distanceCoveredKm;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityExercise that = (ActivityExercise) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ActivityExercise{" +
                "id=" + id +
                ", activityLogId=" + activityLogId +
                ", routineExerciseId=" + routineExerciseId +
                ", actualSets=" + actualSets +
                ", actualReps=" + actualReps +
                ", actualWeightKg=" + actualWeightKg +
                ", actualDurationMin=" + actualDurationMin +
                ", caloriesBurned=" + caloriesBurned +
                ", distanceCoveredKm=" + distanceCoveredKm +
                ", startedAt=" + startedAt +
                ", completedAt=" + completedAt +
                '}';
    }
}
