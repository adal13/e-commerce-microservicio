package com.commerce.pedidos.services;

import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.services.CommonService;
import com.commerce.pedidos.dto.PedidoDTO;

public interface PedidoService extends CommonService<Pedidos> {
	Pedidos actualizar(PedidoDTO pedidoDTO, Long id);
	Pedidos createPedidoDTO(PedidoDTO pedidoDTO);
}
