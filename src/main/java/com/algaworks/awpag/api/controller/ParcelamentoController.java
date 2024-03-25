package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.api.assembler.ParcelamentoAssembler;
import com.algaworks.awpag.api.model.ParcelamentoModelOutput;
import com.algaworks.awpag.api.model.input.ParcelamentoModelInput;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;

    private final ParcelamentoService parcelamentoService;

    //private final ModelMapper modelMapper;

    private final ParcelamentoAssembler parcelamentoAssembler;

    @GetMapping
    public List<ParcelamentoModelOutput> listar(){
        return parcelamentoAssembler.toCollectionModel(parcelamentoRepository.findAll());
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModelOutput> buscar(@PathVariable Long parcelamentoId){
        // usando reference method
        // se existir parcelamento com id, senao notfound
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelamentoModelOutput cadastrar(@Valid @RequestBody ParcelamentoModelInput parcelamentoInput) {
        Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);
        Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);
        return parcelamentoAssembler.toModel(parcelamentoCadastrado);
    }

    /*

    //movido para o ApiExceptionHandler

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturarErro(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

     */
}
