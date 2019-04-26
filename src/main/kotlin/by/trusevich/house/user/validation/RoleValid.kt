package by.trusevich.house.user.validation

import by.trusevich.house.core.model.enumeration.Role
import by.trusevich.house.core.model.enumeration.Role.UNDEFINED
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.reflect.KClass

/**
 * Validates, that the user role is in allowed list.
 */
@Retention
@Target(PROPERTY_GETTER, FIELD)
@MustBeDocumented
@Constraint(validatedBy = [RoleValidator::class])
annotation class RoleValid(
    @Suppress("unused") val message: String = "Role must be either ADMIN or USER",
    @Suppress("unused") val groups: Array<KClass<*>> = [],
    @Suppress("unused") val payload: Array<KClass<out Payload>> = []
)

class RoleValidator : ConstraintValidator<RoleValid, Role?> {

    override fun initialize(constraint: RoleValid) = Unit

    override fun isValid(role: Role?, context: ConstraintValidatorContext) = role == null || role != UNDEFINED
}
