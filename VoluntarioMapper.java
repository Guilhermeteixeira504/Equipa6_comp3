package Mapper;

import java.util.stream.Collectors;

import DTO.VoluntarioDTO;
import lp.Equipa6_Comp2.entity.Voluntario;

public class VoluntarioMapper {
	// ENTITY → DTO
    public static VoluntarioDTO toDTO(Voluntario v) {

        return new VoluntarioDTO(
                v.getId(),
                v.getNome(),
                v.getEmail(),
                v.getPassword(),
                v.getMorada(),
                v.getTelemovel(),
                v.getTipoUtilizador(),
                v.getInscricoes().stream()
                .map(insc -> insc.getId())
                .collect(Collectors.toList())
        );
    }


    // DTO → ENTITY
    public static Voluntario toEntity(VoluntarioDTO dto) {

        if (dto == null) return null;

        Voluntario v = new Voluntario();

        v.setId(dto.getId());
        v.setNome(dto.getNome());
        v.setEmail(dto.getEmail());
        v.setPassword(dto.getPassword());
        v.setMorada(dto.getMorada());
        v.setTelemovel(dto.getTelemovel());
        v.setTipoUtilizador(dto.getTipoUtilizador());

        return v;
    }

}
