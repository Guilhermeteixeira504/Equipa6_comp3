package Mapper;

import DTO.InscricaoDTO;
import lp.Equipa6_Comp2.entity.Inscricao;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.entity.Voluntario;

public class InscricaoMapper {
	// ENTITY → DTO
	public static InscricaoDTO toDTO(Inscricao insc) {
		return new InscricaoDTO(
				insc.getId(),
			    insc.getPrograma().getId(),
			    insc.getVoluntario().getId(),
			    insc.getNHorasRealizadas());
	}
	
	// DTO → ENTITY
	public static Inscricao toEntity(InscricaoDTO dto) {
		if(dto == null) return null;
		
		Inscricao insc = new Inscricao();
		
		if(dto.getId() != null) {
			insc.setId(dto.getId());
		}
		ProgramaVoluntariado p = new ProgramaVoluntariado();
	    p.setId(dto.getProgramaId());
	    insc.setPrograma(p);

	    Voluntario v = new Voluntario();
	    v.setId(dto.getVoluntarioId());
	    insc.setVoluntario(v);
	    
		insc.setNHorasRealizadas(dto.getnHorasRealizadas());
		
		return insc;
	}

}