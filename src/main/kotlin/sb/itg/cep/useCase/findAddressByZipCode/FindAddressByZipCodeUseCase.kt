package sb.itg.cep.useCase.findAddressByZipCode

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sb.itg.cep.client.ViaCepClientFeign
import sb.itg.cep.exception.NotFoundException
import sb.itg.cep.resource.viaCep.ViaCepOutput

@Service
class FindAddressByZipCodeUseCase {

    @Autowired
    private val viaCepInterfaceFeign: ViaCepClientFeign? = null

    operator fun invoke(input: FindAddressByZipCodeInput): ViaCepOutput? {
        val response = viaCepInterfaceFeign!!.findAddressByZipCode(input.zipCode)

        return treatAnswer(response)
    }

    fun treatAnswer(response: ResponseEntity<String>): ViaCepOutput? {
        if (response.body!!.contains("\"erro\": true")) {
            throw NotFoundException("Não foi encontrado nenhum CEP")
        }else{
            return ObjectMapper().readValue(response.body, ViaCepOutput::class.java)
        }
    }
}