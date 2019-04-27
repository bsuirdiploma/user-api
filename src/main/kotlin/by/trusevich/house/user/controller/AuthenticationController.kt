package by.trusevich.house.user.controller

import by.trusevich.house.core.exception.MalformedRequestDataException.Companion.MALFORMED_REASON
import by.trusevich.house.core.exception.UnauthorizedException.Companion.UNAUTHORIZED_REASON
import by.trusevich.house.core.exception.model.ErrorDetails
import by.trusevich.house.user.service.AuthenticationService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

@RestController
@RequestMapping("/auth")
@Api("Authorization Management", description = "Endpoints authorizing users")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"], methods = [POST])
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @ApiOperation("Authorize user", notes = "Authorizes new user")
    @ApiResponses(
        ApiResponse(code = SC_UNAUTHORIZED, message = UNAUTHORIZED_REASON, response = ErrorDetails::class),
        ApiResponse(code = SC_BAD_REQUEST, message = MALFORMED_REASON, response = ErrorDetails::class)
    )
    @PostMapping(produces = [APPLICATION_JSON_UTF8_VALUE])
    fun authenticate(@RequestHeader login: String, @RequestHeader password: String) =
        authenticationService.createToken(login, password)
}
