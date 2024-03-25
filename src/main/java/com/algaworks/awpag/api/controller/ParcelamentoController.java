package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.api.model.ParcelamentoRepresentationModel;
import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;

    private final ParcelamentoService parcelamentoService;

    @GetMapping
    public List<Parcelamento> listar(){
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoRepresentationModel> buscar(@PathVariable Long parcelamentoId){
        // usando reference method
        // se existir parcelamento com id, senao notfound
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamento -> {
                    var parcelamentoModel = new ParcelamentoRepresentationModel();
                    parcelamentoModel.setId(parcelamento.getId());
                    parcelamentoModel.setNomeCliente(parcelamento.getCliente().getNome());
                    parcelamentoModel.setDescricao(parcelamento.getDescricao());
                    parcelamentoModel.setValorTotal(parcelamento.getValorTotal());
                    parcelamentoModel.setParcelas(parcelamento.getQuantidadeParcelas());
                    parcelamentoModel.setDataCriacao(parcelamento.getDataCriacao());

                    return ResponseEntity.ok(parcelamentoModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento) {
        return parcelamentoService.cadastrar(parcelamento);
    }

    /*

    //movido para o ApiExceptionHandler

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturarErro(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

     */
}
