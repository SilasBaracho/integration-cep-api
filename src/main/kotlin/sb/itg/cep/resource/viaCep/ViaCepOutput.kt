package sb.itg.cep.resource.viaCep

import com.fasterxml.jackson.annotation.JsonProperty

data class ViaCepOutput(
    @JsonProperty("cep")
    val cep: String,
    @JsonProperty("logradouro")
    val logradouro: String,
    @JsonProperty("complemento")
    val complemento: String,
    @JsonProperty("bairro")
    val bairro: String,
    @JsonProperty("localidade")
    val localidade: String,
    @JsonProperty("uf")
    val uf: String,
    @JsonProperty("ibge")
    val ibge: String,
    @JsonProperty("gia")
    val gia: String,
    @JsonProperty("ddd")
    val ddd: String,
    @JsonProperty("siafi")
    val siafi: String
): java.io.Serializable{}