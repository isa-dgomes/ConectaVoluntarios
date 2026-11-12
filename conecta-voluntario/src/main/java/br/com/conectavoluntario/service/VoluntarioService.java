package br.com.conectavoluntario.service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.conectavoluntario.dto.request.VoluntarioRequest;
import br.com.conectavoluntario.dto.response.VoluntarioResponse;
import br.com.conectavoluntario.entity.AreaAtuacao;
import br.com.conectavoluntario.entity.Endereco;
import br.com.conectavoluntario.entity.Voluntario;

import br.com.conectavoluntario.exception.RegrasDeNegocioException;
import br.com.conectavoluntario.mapper.VoluntarioMapper;
import br.com.conectavoluntario.repository.AreaAtuacaoRepository;
import br.com.conectavoluntario.repository.UsuarioRepository;
import br.com.conectavoluntario.repository.VoluntarioRepository;
import jakarta.transaction.Transactional;

@Service
public class VoluntarioService {
	
	private final VoluntarioRepository voluntarioRepository;
	private final UsuarioRepository usuarioRepository;
	private final AreaAtuacaoRepository areaAtuacaoRepository;
	private final PasswordEncoder passwordEncoder;
	private final VoluntarioMapper voluntarioMapper;
	
	public VoluntarioService(VoluntarioRepository voluntarioRepository,
			UsuarioRepository usuarioRepository,
			AreaAtuacaoRepository areaAtuacaoRepository,
			PasswordEncoder passwordEncoder,
			VoluntarioMapper voluntarioMapper) {
		
		this.voluntarioRepository = voluntarioRepository;
		this.usuarioRepository = usuarioRepository;
		this.areaAtuacaoRepository = areaAtuacaoRepository;
		this.passwordEncoder = passwordEncoder; 
		this.voluntarioMapper = voluntarioMapper;
	}
	
	
//METODO SALVAR
	
	@Transactional			//grava uma transacao unica, se algo falhar, o banco desfaz
	public VoluntarioResponse cadastrar(VoluntarioRequest req) {

	//verificacoes de email, senha, e ids das areas de atuacao
		if(!req.senha().equals(req.confirmarSenha())) {
			throw new RegrasDeNegocioException("As senhas nao sao iguais");
		}
		
		String email = req.email().trim().toLowerCase();
		
		if(usuarioRepository.existsByEmail(email)){
			throw new RegrasDeNegocioException("Ja existe uma conta com esse e-mail");
		}
		
		/* aqui verifica se os ids que forma mandados correspondem
		 * aos que estao no banco, então, comparando os tamanhos, 
		 * verifica-se se tem incompatibilidades*/
		List<AreaAtuacao> areas = areaAtuacaoRepository.findAllById(req.areasIds());
		if(req.areasIds().size() != areas.size()) {
			throw new RegrasDeNegocioException("Area de atuacao nao encontrada");
		}
	
	//criar endereco
		Endereco endereco = new Endereco();
		endereco.setCidade(req.endereco().cidade().trim());		//trim(), para retirar espacaos brancos do inicio e fim
		endereco.setEstado(req.endereco().estado().trim());
		endereco.setLogradouro(req.endereco().logradouro().trim());
		endereco.setNumero(req.endereco().numero().trim());
		
		// \Dretira o que n e numero, como o traco no cep
		if(req.endereco().cep() != null) {
			endereco.setCep(req.endereco().cep().replaceAll("\\D", ""));

		}
		endereco.setBairro(req.endereco().bairro());
		
	//criar voluntario com o endereco
		Voluntario voluntario = new Voluntario();
		voluntario.setNome(req.nome().trim());
		voluntario.setEmail(email);
		voluntario.setSenhaHash(passwordEncoder.encode(req.senha()));
		voluntario.setTelefone(req.telefone().trim());
		voluntario.setAceitouTermos(true);
		voluntario.setDataAceiteTermos(Instant.now());
		voluntario.setEndereco(endereco);
		voluntario.setHabilidades(req.habilidades());
		voluntario.setAreasDeInteresse(new HashSet<>(areas));
		voluntario.setDisponibilidade(new HashSet<>(req.disponibilidade()));
		voluntario.setPeriodo(new HashSet<>(req.periodos()));
		voluntario.setExperiencia(req.experiencia());
		
		
	//salvar
		Voluntario salvo = voluntarioRepository.save(voluntario);
		return voluntarioMapper.toResponse(salvo);
			
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
