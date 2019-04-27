package by.trusevich.house.user.model.swagger

import by.trusevich.house.core.model.enumeration.Role
import io.swagger.annotations.ApiModelProperty

@Suppress("unused")
abstract class CreateUser(

    val name: String? = null,

    val surname: String? = null,

    @ApiModelProperty(allowableValues = "USER,ADMIN")
    val role: Role? = null,

    val login: String? = null,

    val password: String? = null
)