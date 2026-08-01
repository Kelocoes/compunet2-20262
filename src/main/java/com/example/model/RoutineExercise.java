package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RoutineExercise {
    private int id;
    private int routineId;
    private int exerciseId;
    private int orderIndex;
    private int targetSets;
    private int targetReps;
    private float targetWeightKg;
    private int targetDurationMin;
    private Date createdAt;
    private List<ActivityExercise> activityExercises;

    public RoutineExercise() {
        this.activityExercises = new ArrayList<>();
    }

    public RoutineExercise(int id, int routineId, int exerciseId, int orderIndex, int targetSets, int targetReps, float targetWeightKg, int targetDurationMin, Date createdAt) {
        this.id = id;
        this.routineId = routineId;
        this.exerciseId = exerciseId;
        this.orderIndex = orderIndex;
        this.targetSets = targetSets;
        this.targetReps = targetReps;
        this.targetWeightKg = targetWeightKg;
        this.targetDurationMin = targetDurationMin;
        this.createdAt = createdAt;
        this.activityExercises = new ArrayList<>();
    }

    public RoutineExercise(int id, int routineId, int exerciseId, int orderIndex, int targetSets, int targetReps, float targetWeightKg, int targetDurationMin, Date createdAt, List<ActivityExercise> activityExercises) {
        this.id = id;
        this.routineId = routineId;
        this.exerciseId = exerciseId;
        this.orderIndex = orderIndex;
        this.targetSets = targetSets;
        this.targetReps = targetReps;
        this.targetWeightKg = targetWeightKg;
        this.targetDurationMin = targetDurationMin;
        this.createdAt = createdAt;
        this.activityExercises = activityExercises != null ? activityExercises : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getTargetSets() {
        return targetSets;
    }

    public void setTargetSets(int targetSets) {
        this.targetSets = targetSets;
    }

    public int getTargetReps() {
        return targetReps;
    }

    public void setTargetReps(int targetReps) {
        this.targetReps = targetReps;
    }

    public float getTargetWeightKg() {
        return targetWeightKg;
    }

    public void setTargetWeightKg(float targetWeightKg) {
        this.targetWeightKg = targetWeightKg;
    }

    public int getTargetDurationMin() {
        return targetDurationMin;
    }

    public void setTargetDurationMin(int targetDurationMin) {
        this.targetDurationMin = targetDurationMin;
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
            activityExercise.setRoutineExerciseId(this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineExercise that = (RoutineExercise) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RoutineExercise{" +
                "id=" + id +
                ", routineId=" + routineId +
                ", exerciseId=" + exerciseId +
                ", orderIndex=" + orderIndex +
                ", targetSets=" + targetSets +
                ", targetReps=" + targetReps +
                ", targetWeightKg=" + targetWeightKg +
                ", targetDurationMin=" + targetDurationMin +
                ", createdAt=" + createdAt +
                ", activityExercisesCount=" + (activityExercises != null ? activityExercises.size() : 0) +
                '}';
    }
}
