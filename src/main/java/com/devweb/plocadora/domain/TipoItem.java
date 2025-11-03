package com.devweb.plocadora.domain;

public enum TipoItem {

    DVD("dvd"),
    BLUERAY("BlueRay"),
    FITA("fita");

    private final String valor;

    TipoItem(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
