package com.example.troli.testeaplicacaofirebase.Classes;

/**
 * Created by troli on 02/09/2018.
 */

public enum NivelGraduacao {
    ENSINO_FUNDAMENTAL("Ensino fundamental"),
    ENSINO_MEDIO("Ensino Médio"),
    ENSINO_SUPERIOR("Ensino Superior"),
    POS_GRADUACAO("Pós-graduação"),
    MESTRADO("Mestrado");
    /*ENSINO_MEDIO,
    ENSINO_SUPERIOR,
    POS_GRADUACAO,
    MESTRADO,
    DOUTORADO */

    private String nomeNivel;

    NivelGraduacao(String s) {
        nomeNivel = s;
    }
    @Override public String toString() {
        return nomeNivel;
    }

}
