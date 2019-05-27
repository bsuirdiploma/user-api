package by.trusevich.house.user.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY
import java.io.Serializable

@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Token(

    var token: String? = null

) : Serializable {

    companion object {

        private const val serialVersionUID = 79835162396908761L
    }
}
