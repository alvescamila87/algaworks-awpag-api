package com.algaworks.awpag.api.assembler;

import com.algaworks.awpag.api.model.ParcelamentoModelOutput;
import com.algaworks.awpag.api.model.input.ParcelamentoModelInput;
import com.algaworks.awpag.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoAssembler {

        private final ModelMapper modelMapper;

        public ParcelamentoModelOutput toModel(Parcelamento parcelamento) {
            return modelMapper.map(parcelamento, ParcelamentoModelOutput.class);
        }

        public List<ParcelamentoModelOutput> toCollectionModel(List<Parcelamento> parcelamentos) {
            // converter parcelamentos em parcelamento r.model
                return parcelamentos.stream()
                        .map(this::toModel)
                        .toList();
        }

        public Parcelamento toEntity(ParcelamentoModelInput parcelamentoModelInput) {
                return modelMapper.map(parcelamentoModelInput, Parcelamento.class);
        }
}
