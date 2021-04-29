package br.com.yolotech.api_spring.models;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Conta {
    private int id;
    private String nome;
    private String sobrenome;
    private String titulo;
    private String contato;
    private String username;
    private String biografia;
    private String github;
    private String linkedIn;
    private String senha;
    private int role;
    private Date dataCad;
    private Date ultLogin;
    private boolean isAtiva;

    public Conta() {
    }

    public Conta(String nome, String sobrenome, String titulo, String contato, String username, String biografia, String github, String linkedIn, String senha, int role, Date dataCad, boolean isAtiva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.titulo = titulo;
        this.contato = contato;
        this.username = username;
        this.biografia = biografia;
        this.github = github;
        this.linkedIn = linkedIn;
        this.senha = senha;
        this.role = role;
        this.dataCad = dataCad;
        this.isAtiva = isAtiva;
    }

    public Conta(String nome, String sobrenome, String titulo, String contato, String username, String biografia, String github, String linkedIn, String senha, int role, boolean isAtiva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.titulo = titulo;
        this.contato = contato;
        this.username = username;
        this.biografia = biografia;
        this.github = github;
        this.linkedIn = linkedIn;
        this.senha = senha;
        this.role = role;
        this.isAtiva = isAtiva;
    }

    public Conta(String nome, String sobrenome, String titulo, String contato, String username, String biografia, String github, String linkedIn, String senha, int role, Date dataCad, Date ultLogin, boolean isAtiva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.titulo = titulo;
        this.contato = contato;
        this.username = username;
        this.biografia = biografia;
        this.github = github;
        this.linkedIn = linkedIn;
        this.senha = senha;
        this.role = role;
        this.dataCad = dataCad;
        this.ultLogin = ultLogin;
        this.isAtiva = isAtiva;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    public Date getUltLogin() {
        return ultLogin;
    }

    public void setUltLogin(Date ultLogin) {
        this.ultLogin = ultLogin;
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public void setAtiva(boolean ativa) {
        isAtiva = ativa;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", titulo='" + titulo + '\'' +
                ", contato='" + contato + '\'' +
                ", username='" + username + '\'' +
                ", biografia='" + biografia + '\'' +
                ", github='" + github + '\'' +
                ", linkedIn='" + linkedIn + '\'' +
                ", senha='" + senha + '\'' +
                ", role=" + role +
                ", dataCad=" + dataCad +
                ", ultLogin=" + ultLogin +
                ", isAtiva=" + isAtiva +
                '}';
    }
}
