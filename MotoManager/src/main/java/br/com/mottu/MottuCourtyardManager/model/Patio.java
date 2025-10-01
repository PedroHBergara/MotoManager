package br.com.mottu.MottuCourtyardManager.model;

import jakarta.persistence.*;

@Entity
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qtdMotos;
    // nomeFilial removido
    private int numPatio;

    @ManyToOne(fetch = FetchType.LAZY) // Adicionado FetchType.LAZY
    @JoinColumn(name = "filial_id", nullable = false) // Adicionado nullable = false
    private br.com.mottu.MottuCourtyardManager.model.Filial filial;

    // Getters e Setters (sem nomeFilial)

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

    public br.com.mottu.MottuCourtyardManager.model.Filial getFilial() {
        return filial;
    }

    public void setFilial(br.com.mottu.MottuCourtyardManager.model.Filial filial) {
        this.filial = filial;
    }
}
