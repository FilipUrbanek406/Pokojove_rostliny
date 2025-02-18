package com.engeto.pokojoveRostliny;

import java.time.LocalDate;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum zálivky nesmí být před datem zasazení rostliny. Zadáno: " + watering + "před datem zasazení: " + planted + "!");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky nesmí být menší nebo rovno 0. Zadáno: " + frequencyOfWatering + "!");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo() {
        return "Název rostliny: " + name + ", Datum poslední zálivky: " + watering + ", Datum doporučené další zálivky: " + watering.plusDays(frequencyOfWatering);
    }

    public void doWateringNow() {
        watering = LocalDate.now();
    }

    @Override
    public int compareTo(Plant o) {
        return this.name.compareTo(o.name);
    }
}
