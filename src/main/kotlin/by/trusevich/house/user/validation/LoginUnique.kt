package by.trusevich.house.user.validation

import by.trusevich.house.core.util.lazyLogger
import by.trusevich.house.user.repository.UserRepository
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.reflect.KClass

/**
 * Validates, that the user name is unique in database
 */
@Retention
@Target(PROPERTY_GETTER, FIELD)
@MustBeDocumented
@Constraint(validatedBy = [LoginUniqueValidator::class])
annotation class LoginUnique(
    @Suppress("unused") val message: String = "User with such login already exists",
    @Suppress("unused") val groups: Array<KClass<*>> = [],
    @Suppress("unused") val payload: Array<KClass<out Payload>> = []
)

class LoginUniqueValidator(private val userRepository: UserRepository) : ConstraintValidator<LoginUnique, String?> {

    private val log by lazyLogger()

    override fun initialize(constraint: LoginUnique) = Unit

    override fun isValid(login: String?, context: ConstraintValidatorContext) =
        login == null || !userRepository.existsByLogin(login).also { log.info("LoginUniqueValidator") }
}
