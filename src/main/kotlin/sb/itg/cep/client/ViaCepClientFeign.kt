package sb.itg.cep.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    url = "\${feign.addresses.ws-via-cep}",
    name = "ws-via-cep"
)
interface ViaCepClientFeign {
    @GetMapping("/{zip_code}/json")
    fun findAddressByZipCode(@PathVariable("zip_code") zipCode: String?): ResponseEntity<String>
}
