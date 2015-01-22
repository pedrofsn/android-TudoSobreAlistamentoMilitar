package alistamento.militar;

import java.io.Serializable;

/**
 * Created by Pedro on 25/10/2014.
 */
public class Duvida implements Serializable {

    private final String pergunta;
    private final String resposta;

    public Duvida(String pergunta, String resposta) {
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }
}
