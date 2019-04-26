package by.trusevich.house.user.service

import by.trusevich.house.core.service.AbstractCrudService
import by.trusevich.house.user.model.User
import by.trusevich.house.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(userRepository: UserRepository) : AbstractCrudService<User>(userRepository)