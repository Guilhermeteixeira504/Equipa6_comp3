package Mapper;

import java.util.stream.Collectors;

import DTO.ProgramaVoluntariadoDTO;
import lp.Equipa6_Comp2.entity.Instituicao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.entity.TiposVoluntariado;


public class ProgramaVoluntariadoMapper {
	// ENTITY → DTO
    public static ProgramaVoluntariadoDTO toDTO(ProgramaVoluntariado pv) {

        return new ProgramaVoluntariadoDTO(
                pv.getId(),
                pv.getTitulo(),
                pv.getDescricao(),
                pv.getLocalizacao(),
                pv.getNumeroVagas(),
                pv.getDataInicio(),
                pv.getDataFim(),
                pv.getTotalHoras(),
                pv.getInscricoes().stream()
                .map(insc -> insc.getId())
                .collect(Collectors.toList()),
                pv.getEdicoes().stream()
                .map(e -> e.getId())
                .collect(Collectors.toList()),
                pv.getTiposVoluntariado() != null ? pv.getTiposVoluntariado().getId() : null,
                pv.getInstituicao() != null ? pv.getInstituicao().getId() : null
        );
    }


    public static ProgramaVoluntariado toEntity(ProgramaVoluntariadoDTO dto) {

        if (dto == null) return null;

        ProgramaVoluntariado pv = new ProgramaVoluntariado();

        pv.setId(dto.getId()); 

        pv.setTitulo(dto.getTitulo());
        pv.setDescricao(dto.getDescricao());
        pv.setLocalizacao(dto.getLocalizacao());
        pv.setNumeroVagas(dto.getNumeroVagas());
        pv.setDataInicio(dto.getDataInicio());
        pv.setDataFim(dto.getDataFim());
        pv.setTotalHoras(dto.getTotalHoras());

        if (dto.getTiposVoluntariadoId() != null) {
            TiposVoluntariado tp = new TiposVoluntariado();
            tp.setId(dto.getTiposVoluntariadoId());
            pv.setTiposVoluntariado(tp);
        }

        if (dto.getInstituicaoId() != null) {
            Instituicao inst = new Instituicao();
            inst.setId(dto.getInstituicaoId());
            pv.setInstituicao(inst);
        }

        return pv;
    }


}
