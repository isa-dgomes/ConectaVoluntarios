package br.com.conectavoluntario.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.conectavoluntario.dto.request.LoginRequest;
import br.com.conectavoluntario.dto.response.LoginResponse;
import br.com.conectavoluntario.entity.Organizacao;
import br.com.conectavoluntario.entity.Usuario;
import br.com.conectavoluntario.repository.UsuarioRepository;
import br.com.conectavoluntario.security.JwtService;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AutenticacaoService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest req) {
        Usuario usuario = usuarioRepository
                .findByEmail(req.email().trim().toLowerCase())
                .orElseThrow(() -> new BadCredentialsException("Email ou senha invalidos"));

        if (!passwordEncoder.matches(req.senha(), usuario.getSenhaHash())) {
            throw new BadCredentialsException("Email ou senha invalidos");
        }

        String papel = (usuario instanceof Organizacao) ? "ORGANIZACAO" : "VOLUNTARIO";
        return new LoginResponse(jwtService.gerarToken(usuario), papel, usuario.getIdUsuario());
    }
}