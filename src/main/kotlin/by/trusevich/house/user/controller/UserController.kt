package by.trusevich.house.user.controller

import by.trusevich.house.core.exception.EntityNoContentException.Companion.NO_CONTENT_REASON
import by.trusevich.house.core.exception.EntityNotFoundException.Companion.NOT_FOUND_REASON
import by.trusevich.house.core.exception.MalformedRequestDataException.Companion.MALFORMED_REASON
import by.trusevich.house.core.exception.UnauthorizedException.Companion.UNAUTHORIZED_REASON
import by.trusevich.house.core.exception.model.ErrorDetails
import by.trusevich.house.core.util.SC_UNPROCESSABLE_ENTITY
import by.trusevich.house.core.util.VALIDATION_REASON
import by.trusevich.house.user.model.User
import by.trusevich.house.user.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.hibernate.validator.constraints.Range
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.PATCH
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST
import javax.servlet.http.HttpServletResponse.SC_NOT_FOUND
import javax.servlet.http.HttpServletResponse.SC_NO_CONTENT
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/users")
@Api("User Management", description = "Endpoints for managing users")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"], methods = [GET, POST, PATCH, DELETE])
class UserController(private val userService: UserService) {

    @ApiOperation("Create user", notes = "Creates a new user")
    @ApiResponses(
        ApiResponse(code = SC_UNAUTHORIZED, message = UNAUTHORIZED_REASON, response = ErrorDetails::class),
        ApiResponse(code = SC_UNPROCESSABLE_ENTITY, message = VALIDATION_REASON, response = ErrorDetails::class),
        ApiResponse(code = SC_BAD_REQUEST, message = MALFORMED_REASON, response = ErrorDetails::class)
    )
    @PostMapping(consumes = [APPLICATION_JSON_UTF8_VALUE], produces = [APPLICATION_JSON_UTF8_VALUE])
    @ApiImplicitParams(
        ApiImplicitParam(paramType = "body", dataType = "by.trusevich.house.user.model.swagger.CreateUser")
    )
    fun createUser(@Valid @ApiParam(hidden = true) @RequestBody user: User) = userService.create(user)

    @ApiOperation("Get user by id", notes = "Gets user by id")
    @ApiResponses(
        ApiResponse(code = SC_UNAUTHORIZED, message = UNAUTHORIZED_REASON, response = ErrorDetails::class),
        ApiResponse(code = SC_NO_CONTENT, message = NO_CONTENT_REASON, response = ErrorDetails::class)
    )
    @GetMapping("/{id}", produces = [APPLICATION_JSON_UTF8_VALUE])
    fun getUserById(@ApiParam("10") @PathVariable id: Long) = userService.find(id)

    @ApiOperation("Delete user by id", notes = "Deletes user from database.")
    @ApiResponses(
        ApiResponse(code = SC_UNAUTHORIZED, message = UNAUTHORIZED_REASON, response = ErrorDetails::class),
        ApiResponse(code = SC_NOT_FOUND, message = NOT_FOUND_REASON, response = ErrorDetails::class)
    )
    @DeleteMapping("/{id}", produces = [APPLICATION_JSON_UTF8_VALUE])
    fun deleteUserById(@ApiParam("10") @PathVariable id: Long) = userService.delete(id)

    @ApiOperation("Find all users", notes = "Retrieves a list of User (supports pagination and ordering)")
    @ApiResponses(
        ApiResponse(code = SC_UNAUTHORIZED, message = UNAUTHORIZED_REASON, response = ErrorDetails::class)
    )
    @GetMapping(produces = [APPLICATION_JSON_UTF8_VALUE])
    fun findAllUsers(
        @Valid @Min(0L, message = "Page number should not be negative")
        @ApiParam("0") @RequestParam(required = false, defaultValue = "0") page: Int,
        @Valid @Range(min = 0L, max = 30L, message = "Page limit should not be  from 0 to 30")
        @ApiParam("0") @RequestParam(required = false, defaultValue = "\${house.defaults.page.size}") limit: Int
    ) = userService.findAll(page, limit)
}
