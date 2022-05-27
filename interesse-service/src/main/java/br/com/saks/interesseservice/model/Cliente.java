
package br.com.saks.interesseservice.model;

import java.util.Date;

public class Cliente {
    private Long id;
    private Long idTipoImovel;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Float valor;
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipoImovel() {
        return idTipoImovel;
    }

    public void setIdTipoImovel(Long idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
