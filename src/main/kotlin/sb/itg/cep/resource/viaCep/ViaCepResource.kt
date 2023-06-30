package sb.itg.cep.resource.viaCep

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sb.itg.cep.useCase.findAddressByZipCode.FindAddressByZipCodeInput
import sb.itg.cep.useCase.findAddressByZipCode.FindAddressByZipCodeUseCase


@RestController
@RequestMapping("/v1/cep")
class ViaCepResource(
    val findByZipCodeUseCase: FindAddressByZipCodeUseCase
) {
    @GetMapping("/{zip_code}")
    fun findByZipCode(
        @PathVariable("zip_code")
        @NotBlank(message = "O CEP deve ser informado")
        @Min(value = 8, message = "CEP inválido")
        @Max(value = 8, message = "CEP inválido")
        zipCode: String
    ): ResponseEntity<ViaCepOutput> {
        return ok(findByZipCodeUseCase.invoke(FindAddressByZipCodeInput(zipCode = zipCode)))
    }
}