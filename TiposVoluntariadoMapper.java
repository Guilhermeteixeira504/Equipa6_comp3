package Mapper;

import java.util.stream.Collectors;

import DTO.TiposVoluntariadoDTO;
import lp.Equipa6_Comp2.entity.TiposVoluntariado;

public class TiposVoluntariadoMapper {

    // ENTITY → DTO
    public static TiposVoluntariadoDTO toDTO(TiposVoluntariado tipo) {

        return new TiposVoluntariadoDTO(
                tipo.getId(),
                tipo.getNome(),
                tipo.getProgramas().stream().map(tp -> tp.getId()).collect(Collectors.toList())
        );
    }

    // DTO → ENTITY
    public static TiposVoluntariado toEntity(TiposVoluntariadoDTO dto) {

        if (dto == null) return null;

        TiposVoluntariado tipo = new TiposVoluntariado();

        if (dto.getId() != null) {
            tipo.setId(dto.getId());
        }

        tipo.setNome(dto.getNome());
        
        return tipo;
    }

}
