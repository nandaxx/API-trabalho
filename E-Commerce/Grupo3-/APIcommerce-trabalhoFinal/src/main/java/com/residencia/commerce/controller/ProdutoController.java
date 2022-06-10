package com.residencia.commerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.entity.Produto;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.interfaces.ResponseApiProduto;
import com.residencia.commerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto")
public class ProdutoController implements ResponseApiProduto {
	@Autowired
	ProdutoService produtoService;

	public ResponseEntity<List<ProdutoDTO>> findAllProduto() {
		List<ProdutoDTO> produtoList = produtoService.findAllProduto();
		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}

	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer id) {
		ProdutoDTO produtoDTO = produtoService.findProdutoById(id);
		if (null == produtoDTO)
			throw new NoSuchElementFoundException("Não foi encontrado Produto com id: " + id + " pois não existe");
		else
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
	}

	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
		ProdutoDTO novoProduto = produtoService.saveProduto(produtoDTO);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}

	public ResponseEntity<ProdutoDTO> saveCategoriaComfoto(@RequestPart("produto") Produto produto,
			@RequestPart("file") MultipartFile file) throws Exception {
		ProdutoDTO novoProduto = produtoService.saveProdutoComFoto(produto, file);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}

	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
		ProdutoDTO produtoAtualizado = produtoService.findProdutoById(produtoDTO.getIdProduto());
		if (null == produtoAtualizado)
			throw new NoSuchElementFoundException("Não foi possivel atualizar Produto com este id");
		else
			return new ResponseEntity<>(produtoService.updateProduto(produtoDTO), HttpStatus.OK);
	}

	public ResponseEntity<String> deleteProduto(@PathVariable Integer id) {
		ProdutoDTO ProdutoAtualizado = produtoService.findProdutoById(id);
		if (null == ProdutoAtualizado)
			throw new NoSuchElementFoundException(
					"Não foi possível deletar Produto com id: " + id + " pois não existe");
		else
			produtoService.deleteProdutoById(id);
		return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
	}
}
