package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Exercise {
    private int id;
    private String name;
    private String description;
    private String type;
    private float estimatedCalories;
    private float estimatedDistanceKm;
    private int estimatedDurationMin;
    private String icon;
    private Date createdAt;
    private List<RoutineExercise> routineExercises;

    public Exercise() {
        this.routineExercises = new ArrayList<>();
    }

    public Exercise(int id, String name, String description, String type, float estimatedCalories, float estimatedDistanceKm, int estimatedDurationMin, String icon, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.estimatedCalories = estimatedCalories;
        this.estimatedDistanceKm = estimatedDistanceKm;
        this.estimatedDurationMin = estimatedDurationMin;
        this.icon = icon;
        this.createdAt = createdAt;
        this.routineExercises = new ArrayList<>();
    }

    public Exercise(int id, String name, String description, String type, float estimatedCalories, float estimatedDistanceKm, int estimatedDurationMin, String icon, Date createdAt, List<RoutineExercise> routineExercises) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.estimatedCalories = estimatedCalories;
        this.estimatedDistanceKm = estimatedDistanceKm;
        this.estimatedDurationMin = estimatedDurationMin;
        this.icon = icon;
        this.createdAt = createdAt;
        this.routineExercises = routineExercises != null ? routineExercises : new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getEstimatedCalories() {
        return estimatedCalories;
    }

    public void setEstimatedCalories(float estimatedCalories) {
        this.estimatedCalories = estimatedCalories;
    }

    public float getEstimatedDistanceKm() {
        return estimatedDistanceKm;
    }

    public void setEstimatedDistanceKm(float estimatedDistanceKm) {
        this.estimatedDistanceKm = estimatedDistanceKm;
    }

    public int getEstimatedDurationMin() {
        return estimatedDurationMin;
    }

    public void setEstimatedDurationMin(int estimatedDurationMin) {
        this.estimatedDurationMin = estimatedDurationMin;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<RoutineExercise> getRoutineExercises() {
        return routineExercises;
    }

    public void setRoutineExercises(List<RoutineExercise> routineExercises) {
        this.routineExercises = routineExercises;
    }

    public void addRoutineExercise(RoutineExercise routineExercise) {
        if (routineExercise != null) {
            this.routineExercises.add(routineExercise);
            routineExercise.setExerciseId(this.id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", estimatedCalories=" + estimatedCalories +
                ", estimatedDistanceKm=" + estimatedDistanceKm +
                ", estimatedDurationMin=" + estimatedDurationMin +
                ", icon='" + icon + '\'' +
                ", createdAt=" + createdAt +
                ", routineExercisesCount=" + (routineExercises != null ? routineExercises.size() : 0) +
                '}';
    }
}
