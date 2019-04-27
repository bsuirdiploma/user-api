package by.trusevich.house.user.service

import by.trusevich.house.core.service.AbstractCrudService
import by.trusevich.house.user.model.User
import by.trusevich.house.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) :
    AbstractCrudService<User>(userRepository) {

    override fun create(model: User) =
        super.create(model.apply { password = passwordEncoder.encode(password) })
}