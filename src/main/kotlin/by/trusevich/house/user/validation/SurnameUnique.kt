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
 * Validates, that the user surname is unique in database
 */
@Retention
@Target(PROPERTY_GETTER, FIELD)
@MustBeDocumented
@Constraint(validatedBy = [SurnameUniqueValidator::class])
annotation class SurnameUnique(
    @Suppress("unused") val message: String = "User with such surname already exists",
    @Suppress("unused") val groups: Array<KClass<*>> = [],
    @Suppress("unused") val payload: Array<KClass<out Payload>> = []
)

class SurnameUniqueValidator(private val userRepository: UserRepository) : ConstraintValidator<SurnameUnique, String?> {

    private val log by lazyLogger()

    override fun initialize(constraint: SurnameUnique) = Unit

    override fun isValid(surname: String?, context: ConstraintValidatorContext) =
        surname == null || !userRepository.existsBySurname(surname).also { log.info("SurnameUniqueValidator") }
}
