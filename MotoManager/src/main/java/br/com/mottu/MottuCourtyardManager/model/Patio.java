package br.com.mottu.MottuCourtyardManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qtdMotos;
    private int numPatio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtdMotos() {
        return qtdMotos;
    }

    public void setQtdMotos(int qtdMotos) {
        this.qtdMotos = qtdMotos;
    }

    public int getNumPatio() {
        return numPatio;
    }

    public void setNumPatio(int numPatio) {
        this.numPatio = numPatio;
    }
}
