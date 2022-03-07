package br.com.cadastro.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cadastro.entity.Cliente;
import br.com.cadastro.entity.Deposito;
import br.com.cadastro.entity.Endereco;
import br.com.cadastro.entity.OrderPedido;
import br.com.cadastro.entity.Produto;
import br.com.cadastro.models.OrderRequest;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.repositories.DepositoRepository;
import br.com.cadastro.repositories.EnderecoRepository;
import br.com.cadastro.repositories.OrderRepository;
import br.com.cadastro.repositories.ProdutoRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private DepositoRepository depositoRepository;

	private static final Logger logger = LogManager.getLogger(OrderService.class);

	@Value("${endpoint.cadastro.deposito}")
	private String endPointEndereco;

	/**
	 * @param prodRequest
	 * @return
	 */
	public ResponseEntity<OrderPedido> inclusao(OrderRequest prodRequest) {
		logger.info("Iniciando a chamada da Inclusao de Produto");

		OrderPedido orderPedido = new OrderPedido();
		orderPedido = addPedido(prodRequest, orderPedido);
		try {
			orderPedido = orderRepository.save(orderPedido);
		} catch (Exception e) {
			logger.error("Ocorreu um erro na persistencia de Dados");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderPedido);
		}

		if(orderPedido != null) {
			return ResponseEntity.ok().body(orderPedido);
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * @param codigo
	 * @return
	 */
	public ResponseEntity<OrderRequest> consultar(String order) {
		logger.info("Iniciando a chamada da Consulta de OrderService");
		OrderRequest orderRequest = new OrderRequest();
		Endereco endereco = new Endereco();

		Long id = Long.parseLong(order);

		try {

			Optional<OrderPedido> findById = this.orderRepository.findById(id);
			if(findById.isPresent()) {

				orderRequest.setId(findById.get().getId().toString() );
				orderRequest.setCodigoproduto(findById.get().getCodigoproduto() );
				orderRequest.setCpf(findById.get().getCpf());
				orderRequest.setDatapedido(findById.get().getDatapedido());

				List<Cliente> findByCpf = clienteRepository.findByCpf(findById.get().getCpf());
				for (Cliente cli : findByCpf) {

					endereco = enderecoRepository.findByClienteId(cli.getId());
					cli.setEndereco(endereco);
					orderRequest.setEndereco(endereco);
					orderRequest.setCliente(cli);

					Integer codigoproduto = Integer.valueOf(findById.get().getCodigoproduto());
					List<Produto> findByCodigo = produtoRepository.findByCodigo(codigoproduto);
					for (Produto prod : findByCodigo) {
						orderRequest.setProduto(prod);

						Optional<Deposito> findByIdDeposito = Optional.ofNullable(new Deposito());
						findByIdDeposito = depositoRepository.findById(Long.valueOf(prod.getDepositoid()));

						if(findByIdDeposito.isPresent()) {

							Long id2 = findByIdDeposito.get().getId();

							Endereco enderecoDeposito = new Endereco();
							enderecoDeposito.setClienteId(id2);
							enderecoDeposito = enderecoRepository.findByClienteId(enderecoDeposito.getClienteId());

							Deposito deposito = new Deposito();
							deposito.setEndereco(enderecoDeposito);

							orderRequest.setDeposito(deposito);

						}

					}

					return ResponseEntity.ok().body(orderRequest);

				}
			}

		} catch (Exception e) {
			logger.error("Iniciando a chamada da Inclusao de Produto");
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderRequest);
		}

		logger.info("Fim da chamada da consulta de Produto");
		return ResponseEntity.notFound().build();

	}

	/**
	 * @param prodRequest
	 * @param order
	 * @return
	 */
	private OrderPedido addPedido(OrderRequest prodRequest, OrderPedido order) {

		if(prodRequest.getId() != null) {
			order.setId(Long.valueOf(prodRequest.getId()));;
		}

		order.setCodigoproduto(prodRequest.getCodigoproduto());;
		order.setDatapedido(prodRequest.getDatapedido());;
		order.setCpf(prodRequest.getCpf());;
		return order;
	}


	private ResponseEntity<OrderRequest> callServiceDeposito(OrderRequest prodReques){
		logger.info("Inicio da chamada ao servico de Orquestracao de Deposito-java");

		RestTemplate template = new RestTemplate();
		UUID randomUUID = UUID.randomUUID();

		HttpHeaders headers = new HttpHeaders();
		headers.add("x-transaction-id", randomUUID.toString());
		headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		HttpEntity request = new HttpEntity<>(prodReques, headers);

		ResponseEntity<OrderRequest> exchange = template.exchange(this.endPointEndereco, HttpMethod.GET, request, OrderRequest.class );
		logger.info("Response Orchestrator: " + exchange.getBody() );
		logger.info("Response Orchestrator Status " + exchange.getStatusCode());

		return exchange;

	}


	/**
	 * @param codigo
	 * @return
	 */
	public ResponseEntity<List<OrderPedido>> consultarOrdens() {
		logger.info("Iniciando a chamada da Consulta de OrderService");

		try {

			List<OrderPedido> findAll = this.orderRepository.findAll();
			if(findAll.size() > 0 ) {
				return ResponseEntity.ok().body(findAll);
			}

		} catch (Exception e) {
			logger.error("Iniciando a chamada da Inclusao de Produto");
			return ResponseEntity.notFound().build();
		}

		logger.info("Fim da chamada da consulta de Produto");
		return ResponseEntity.notFound().build();

	}


	/**
	 * @param prodRequest
	 * @return
	 */
	public ResponseEntity<OrderPedido> atualizar(OrderRequest prodRequest) {
		logger.info("Iniciando a chamada da atualizar de Produto");

		OrderPedido orderPedido = new OrderPedido();
		orderPedido = addPedido(prodRequest, orderPedido);
		try {
			orderPedido = orderRepository.saveAndFlush(orderPedido);
		} catch (Exception e) {
			logger.error("Ocorreu um erro na persistencia de Dados");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderPedido);
		}

		if(orderPedido != null) {
			return ResponseEntity.ok().body(orderPedido);
		}

		return ResponseEntity.notFound().build();

	}



}
