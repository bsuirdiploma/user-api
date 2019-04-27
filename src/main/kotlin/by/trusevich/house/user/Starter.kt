package by.trusevich.house.user

import by.trusevich.house.core.model.enumeration.Role.ADMIN
import by.trusevich.house.core.util.lazyLogger
import by.trusevich.house.user.model.User
import by.trusevich.house.user.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
class Starter

fun main(args: Array<String>) {
    runApplication<Starter>(*args)
}

@Component
class DbInitializer(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) : CommandLineRunner {

    private val log by lazyLogger()

    override fun run(vararg args: String?) {
        if (!userRepository.existsByLogin(DEFAULT_ADMIN)) {
            userRepository.save(User(DEFAULT_ADMIN, DEFAULT_ADMIN, ADMIN, DEFAULT_ADMIN, encoder.encode(DEFAULT_ADMIN)))
            log.info("Created root admin user")
        }
    }

    companion object {

        private const val DEFAULT_ADMIN = "admin"
    }
}