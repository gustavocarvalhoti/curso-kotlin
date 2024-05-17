package br.com.alura.alugames.modelo// Val não pode alterar
// Var pode alterar
// Construtor que recebe 2 parametros
// @SerializedName("title") como vem na request
// No exercio posterior eu removi o SerializedName

data class Jogo(
    val titulo: String,
    val capa: String,
    var descricao: String = ""
) {

    override fun toString(): String {
        return "\n***************************" +
                "\nTitle: '$titulo', " +
                "\nCover: '$capa', " +
                "\nDescription: '$descricao'" +
                "\n***************************\n"
    }

}