package br.com.cadastro.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.entity.OrderPedido;
import br.com.cadastro.models.OrderRequest;
import br.com.cadastro.services.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);

	/**
	 * @param depositoRequest
	 * @return
	 */
	@PostMapping(path = "/incluir" , produces = {"application/json"})
	public ResponseEntity<OrderPedido> inclusao(@RequestBody OrderRequest depositoRequest, @RequestParam("token") String token) {
		logger.info("Iniciando a Inclusao do OrderService");
		return orderService.inclusao(depositoRequest );
	}


	/**
	 * @param order
	 * @return
	 */
	@GetMapping(path = "/consultar" , produces = {"application/json"})
	public ResponseEntity<OrderRequest> consulta(@RequestParam("order") String order, @RequestParam("token") String token) {
		logger.info("Iniciando a consulta do OrderService");
		return orderService.consultar(order);
	}

	
	/**
	 * @param order
	 * @return
	 */
	@GetMapping(path = "/consultarordens" , produces = {"application/json"})
	public ResponseEntity<List<OrderPedido>> consultaOrdens(@RequestParam("token") String token) {
		logger.info("Iniciando a consulta do OrderService");
		return orderService.consultarOrdens();
	}
	
	
	/**
	 * @param depositoRequest
	 * @return
	 */
	@PutMapping(path = "/atualizar" , produces = {"application/json"})
	public ResponseEntity<OrderPedido> atualizar(@RequestBody OrderRequest depositoRequest, @RequestParam("token") String token) {
		logger.info("Iniciando a Inclusao do OrderService");
		return orderService.atualizar(depositoRequest );
	}
}