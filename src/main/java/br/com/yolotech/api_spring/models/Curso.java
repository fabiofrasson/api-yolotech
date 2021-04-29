package br.com.yolotech.api_spring.models;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Curso {
    private int id;
    private String nome;
    private String descricao;
    private String instrutor;
    private Categoria categoria;
    private String site;
    private double duracao;
    private String slug;
    private Date dataCad;
    private boolean isEditado;
    private boolean isAtivo;

    public Curso() {
    }

    public Curso(String nome, String descricao, String instrutor, Categoria categoria, String site, double duracao, String slug, Date dataCad, boolean isEditado, boolean isAtivo) {
        this.nome = nome;
        this.descricao = descricao;
        this.instrutor = instrutor;
        this.categoria = categoria;
        this.site = site;
        this.duracao = duracao;
        this.slug = slug;
        this.dataCad = dataCad;
        this.isEditado = isEditado;
        this.isAtivo = isAtivo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public boolean isEditado() {
        return isEditado;
    }

    public void setEditado(boolean editado) {
        isEditado = editado;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instrutor='" + instrutor + '\'' +
                ", categorias=" + categoria +
                ", site='" + site + '\'' +
                ", duracao=" + duracao +
                ", slug='" + slug + '\'' +
                ", dataCad=" + dataCad +
                ", isEditado=" + isEditado +
                ", isAtivo=" + isAtivo +
                '}';
    }
}
