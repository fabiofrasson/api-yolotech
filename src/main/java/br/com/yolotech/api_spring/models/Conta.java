package br.com.yolotech.api_spring.models;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Conta {
    private int id;
    private String nome;
    private String sobrenome;
    private String titulo;
    private Date dataCad;

    public Conta() {
    }

    public Conta(String nome, String sobrenome, String titulo, Date dataCad) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.titulo = titulo;
        this.dataCad = dataCad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", titulo='" + titulo + '\'' +
                ", dataCad=" + dataCad +
                '}';
    }
}
