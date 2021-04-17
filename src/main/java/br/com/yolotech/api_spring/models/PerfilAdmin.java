package br.com.yolotech.api_spring.models;

import javax.persistence.Entity;

@Entity
public class PerfilAdmin {
    private int idAdmin;
    private String nomeAdmin;
    private String sobrenomeAdmin;
    private String numCelularAdmin;
    private String dataCadastroAdmin;
    private boolean isContaAtivaAdmin;

    public PerfilAdmin() {
    }

    public PerfilAdmin(String nomeAdmin, String sobrenomeAdmin,
                       String numCelularAdmin, String dataCadastroAdmin,
                       boolean isContaAtivaAdmin) {
        this.nomeAdmin = nomeAdmin;
        this.sobrenomeAdmin = sobrenomeAdmin;
        this.numCelularAdmin = numCelularAdmin;
        this.dataCadastroAdmin = dataCadastroAdmin;
        this.isContaAtivaAdmin = isContaAtivaAdmin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getSobrenomeAdmin() {
        return sobrenomeAdmin;
    }

    public void setSobrenomeAdmin(String sobrenomeAdmin) {
        this.sobrenomeAdmin = sobrenomeAdmin;
    }

    public String getNumCelularAdmin() {
        return numCelularAdmin;
    }

    public void setNumCelularAdmin(String numCelularAdmin) {
        this.numCelularAdmin = numCelularAdmin;
    }

    public String getDataCadastroAdmin() {
        return dataCadastroAdmin;
    }

    public void setDataCadastroAdmin(String dataCadastroAdmin) {
        this.dataCadastroAdmin = dataCadastroAdmin;
    }

    public boolean isContaAtivaAdmin() {
        return isContaAtivaAdmin;
    }

    public void setContaAtivaAdmin(boolean contaAtivaAdmin) {
        isContaAtivaAdmin = contaAtivaAdmin;
    }

    @Override
    public String toString() {
        return "PerfilAdmin{" +
                "nomeAdmin='" + nomeAdmin + '\'' +
                ", sobrenomeAdmin='" + sobrenomeAdmin + '\'' +
                ", numCelularAdmin='" + numCelularAdmin + '\'' +
                ", dataCadastroAdmin=" + dataCadastroAdmin +
                ", isContaAtivaAdmin=" + isContaAtivaAdmin +
                '}';
    }
}
