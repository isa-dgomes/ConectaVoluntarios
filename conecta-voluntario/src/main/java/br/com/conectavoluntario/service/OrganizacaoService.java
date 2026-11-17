package br.com.conectavoluntario.service;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.conectavoluntario.dto.request.OrganizacaoRequest;
import br.com.conectavoluntario.dto.request.OrganizacaoUpdateRequest;
import br.com.conectavoluntario.dto.response.OrganizacaoResponse;
import br.com.conectavoluntario.entity.Endereco;
import br.com.conectavoluntario.entity.Organizacao;
import br.com.conectavoluntario.exception.RecursoNaoEncontradoException;
import br.com.conectavoluntario.exception.RegrasDeNegocioException;
import br.com.conectavoluntario.mapper.OrganizacaoMapper;
import br.com.conectavoluntario.repository.OrganizacaoRepository;
import br.com.conectavoluntario.repository.UsuarioRepository;

@Service
public class OrganizacaoService {

    private final OrganizacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizacaoMapper mapper;

    public OrganizacaoService(
            OrganizacaoRepository repository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            OrganizacaoMapper mapper) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public OrganizacaoResponse criar(OrganizacaoRequest dto) {
        if (repository.existsByCnpj(dto.cnpj())) {
            throw new RegrasDeNegocioException("CNPJ já cadastrado");
        }

        Organizacao org = mapper.ToEntidade(dto);
        Organizacao salva = repository.save(org);
        return ToResponse(salva);
    }

    public List<OrganizacaoResponse> listar() {
        return repository.findAll()
                .stream()
                .map(org -> (OrganizacaoResponse) mapper.ToResponse(org))
                .toList();
    }

    @Transactional(readOnly = true)
    public OrganizacaoResponse buscarPorId(Long id) {
        Organizacao org = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada"));
        return ToResponse(org);
    }

    @Transactional
    public OrganizacaoResponse cadastrar(OrganizacaoRequest req) {
        if (!req.senha().equals(req.confirmarSenha())) {
            throw new RegrasDeNegocioException("Senhas não conferem");
        }

        String email = req.email().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new RegrasDeNegocioException("Já existe uma conta com este e-mail");
        }

        if (repository.existsByCnpj(req.cnpj())) {
            throw new RegrasDeNegocioException("Já existe uma organização com este CNPJ");
        }

        Endereco endereco = new Endereco();
        endereco.setCidade(req.cidade());
        endereco.setEstado(req.estado());
        endereco.setLogradouro(req.logradouro());
        endereco.setNumero(req.numero());
        endereco.setCep(req.cep());
        endereco.setBairro(req.bairro());

        Organizacao organizacao = new Organizacao(
                req.nome(),
                email,
                passwordEncoder.encode(req.senha()),
                req.telefone(),
                req.aceitouTermos(),
                Instant.now(),
                endereco,
                req.razaoSocial() != null ? req.razaoSocial() : req.nome(),
                req.cnpj(),
                req.descricao(),
                req.tipoOrganizacao(),
                req.responsavel(),
                null
        );

        Organizacao salva = repository.save(organizacao);

        return ToResponse(salva);
    }

    @Transactional(readOnly = true)
    public Page<OrganizacaoResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(this::ToResponse);
    }

    @Transactional
    public OrganizacaoResponse atualizar(Long id, OrganizacaoUpdateRequest req) {
        Organizacao organizacao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada"));

        organizacao.setNome(req.nome());
        organizacao.setRazaoSocial(req.razaoSocial() != null ? req.razaoSocial() : req.nome());
        organizacao.setTelefone(req.telefone());
        organizacao.setDescricao(req.descricao());
        organizacao.setResponsavel(req.responsavel());
        organizacao.setTipoOrganizacao(req.tipoOrganizacao());

        Endereco endereco = organizacao.getEndereco();
        endereco.setCidade(req.cidade());
        endereco.setEstado(req.estado());
        endereco.setLogradouro(req.logradouro());
        endereco.setNumero(req.numero());
        endereco.setCep(req.cep());
        endereco.setBairro(req.bairro());

        return ToResponse(organizacao);
    }

    @Transactional
    public void remover(Long id) {
        Organizacao organizacao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada"));
        repository.delete(organizacao);
    }

    private OrganizacaoResponse ToResponse(Organizacao o) {
        Endereco e = o.getEndereco();
        String enderecoTexto = e.getLogradouro() + ", " + e.getNumero()
                + " - " + e.getCidade() + "/" + e.getEstado();

        return new OrganizacaoResponse(
                o.getIdUsuario(),
                o.getNome(),
                o.getDescricao(),
                o.getCnpj(),
                o.getEmail(),
                o.getTelefone(),
                enderecoTexto,
                null
        );
    }
}
