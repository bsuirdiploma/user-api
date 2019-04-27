package by.trusevich.house.user.model

import by.trusevich.house.core.model.BaseEntity
import by.trusevich.house.core.model.enumeration.Role
import by.trusevich.house.user.validation.LoginUnique
import by.trusevich.house.user.validation.RoleValid
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY
import io.swagger.annotations.ApiModelProperty
import org.hibernate.envers.Audited
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Audited
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints = [UniqueConstraint(name = "unique_name", columnNames = ["login"])])
data class User(

    @get:NotBlank
    @get:Length(max = 255)
    @Column(nullable = false)
    var name: String? = null,

    @get:NotBlank
    @get:Length(max = 255)
    @Column(nullable = false)
    var surname: String? = null,

    @get:RoleValid
    @get:NotNull
    @Column(nullable = false)
    var role: Role? = null,

    @JsonProperty(access = WRITE_ONLY)
    @ApiModelProperty(readOnly = true)
    @get:LoginUnique
    var login: String? = null,

    @JsonProperty(access = WRITE_ONLY)
    @ApiModelProperty(readOnly = true)
    var password: String? = null

) : BaseEntity() {

    companion object {

        private const val serialVersionUID = 79835162396908742L
    }
}
