package br.com.conectavoluntario.service;

import java.time.Instant;

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
import br.com.conectavoluntario.repository.OportunidadeRepository;
import br.com.conectavoluntario.repository.OrganizacaoRepository;
import br.com.conectavoluntario.repository.UsuarioRepository;

@Service
public class OrganizacaoService {

    private final OrganizacaoRepository organizacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public OrganizacaoService(OrganizacaoRepository organizacaoRepository,
            OportunidadeRepository oportunidadeRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.organizacaoRepository = organizacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //metodo salvar
    @Transactional
    public OrganizacaoResponse cadastrar(OrganizacaoRequest req) {

        if (!req.senha().equals(req.confirmarSenha())) {
            throw new RegrasDeNegocioException("Senhas não conferem");
        }

        String email = req.email().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new RegrasDeNegocioException("Já existe uma conta com este e-mail");
        }

        if (organizacaoRepository.existsByCnpj(req.cnpj())) {
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
                null // logo
        );

        Organizacao salva = organizacaoRepository.save(organizacao);

        return ToResponse(salva);
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
                null // site: a entidade ainda não tem esse campo
        );
    }

    @Transactional(readOnly = true)
    public OrganizacaoResponse buscarPorId(Long id) {
        Organizacao organizacao = organizacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada"));

        return ToResponse(organizacao);
    }

    @Transactional(readOnly = true)
    public Page<OrganizacaoResponse> listar(Pageable pageable) {
        return organizacaoRepository.findAll(pageable).map(this::ToResponse);
    }

    //metodo atualizar organizacao
    @Transactional
    public OrganizacaoResponse atualizar(Long id, OrganizacaoUpdateRequest req) {
        Organizacao organizacao = organizacaoRepository.findById(id)
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
        Organizacao organizacao = organizacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada"));
        organizacaoRepository.delete(organizacao);
    }
}
