package med.voll.vollapirest.controllers;

import jakarta.validation.Valid;
import med.voll.vollapirest.domain.usuarios.Usuario;
import med.voll.vollapirest.domain.usuarios.dto.AutenticacionUsuarioDTO;
import med.voll.vollapirest.infra.security.JwtDTO;
import med.voll.vollapirest.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager atm, TokenService token) {
        this.authenticationManager = atm;
        this.tokenService = token;
    }


    @PostMapping
    public ResponseEntity<JwtDTO> autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.login(), datosAutenticacion.password());
        //En este punto, AuthManager intenta autenticar el token utilizando el UserDetailsService proporcionado por
        //mi clase AutenticacionService. AuthManager fue definido en SecurityConfiguration.
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        String jwt = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JwtDTO(jwt));
    }
}
