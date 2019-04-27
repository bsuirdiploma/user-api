package by.trusevich.house.user.service

import by.trusevich.house.core.exception.UnauthorizedException
import by.trusevich.house.core.service.BaseAuthenticationService
import by.trusevich.house.user.repository.UserRepository
import io.jsonwebtoken.Jwts.builder
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.apache.commons.lang3.time.DateUtils.addHours
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : BaseAuthenticationService() {

    fun createToken(login: String, enteredPassword: String) = userRepository.findByLogin(login)?.run {

        if (!passwordEncoder.matches(enteredPassword, password))
            throw UnauthorizedException()

        builder().setClaims(
            mapOf(
                NAME to name,
                SURNAME to surname,
                ROLE to role,
                EXPIRATION to addHours(Date(), 1).time.toString()
            )
        ).signWith(HS512, SECRET_KEY).compact()
    } ?: throw UnauthorizedException()
}
