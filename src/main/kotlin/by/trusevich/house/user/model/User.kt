package by.trusevich.house.user.model

import by.trusevich.house.core.model.BaseEntity
import by.trusevich.house.core.model.enumeration.Role
import by.trusevich.house.user.validation.NameUnique
import by.trusevich.house.user.validation.RoleValid
import by.trusevich.house.user.validation.SurnameUnique
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY
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
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "unique_name", columnNames = ["name"]),
        UniqueConstraint(name = "unique_surname", columnNames = ["surname"])
    ]
)
data class User(

    @get:NotBlank
    @get:NameUnique
    @get:Length(max = 255)
    @Column(nullable = false)
    var name: String? = null,

    @get:NotBlank
    @get:SurnameUnique
    @get:Length(max = 255)
    @Column(nullable = false)
    var surname: String? = null,

    @get:RoleValid
    @get:NotNull
    @Column(nullable = false)
    var role: Role? = null

) : BaseEntity() {

    companion object {

        private const val serialVersionUID = 79835162396908742L
    }
}
