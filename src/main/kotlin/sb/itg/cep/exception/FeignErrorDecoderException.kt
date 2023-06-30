package sb.itg.cep.exception

import feign.Response
import feign.codec.ErrorDecoder

class FeignErrorDecoderException(
    private val errorDecoder: ErrorDecoder = ErrorDecoder.Default()
) : ErrorDecoder {

    override fun decode(methodKey: String?, response: Response): Exception {
        when (response.status()) {
            400 -> throw BadRequestException("CEP inválido")
            404 -> throw NotFoundException("O CEP informado não foi encontrado")
            else -> throw Exception("Erro ao se comunicar com o WebService")
        }
    }
}