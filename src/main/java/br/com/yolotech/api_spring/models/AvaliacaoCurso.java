package br.com.yolotech.api_spring.models;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class AvaliacaoCurso {
    private int id;
    private int curso;
    private int usuario;
    private double nota;
    private String comentario;
    private Date dataCad;
    private boolean isEditada;
    private boolean isAtiva;

    public AvaliacaoCurso() {
    }

    public AvaliacaoCurso(int curso, int usuario, double nota, String comentario, Date dataCad, boolean isEditada, boolean isAtiva) {
        this.curso = curso;
        this.usuario = usuario;
        this.nota = nota;
        this.comentario = comentario;
        this.dataCad = dataCad;
        this.isEditada = isEditada;
        this.isAtiva = isAtiva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public boolean isEditada() {
        return isEditada;
    }

    public void setEditada(boolean editada) {
        isEditada = editada;
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public void setAtiva(boolean ativa) {
        isAtiva = ativa;
    }

    @Override
    public String toString() {
        return "AvaliacaoCurso{" +
                "id=" + id +
                ", curso=" + curso +
                ", usuario=" + usuario +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", dataCad=" + dataCad +
                ", isEditada=" + isEditada +
                ", isAtiva=" + isAtiva +
                '}';
    }
}
