package Mapper;

import DTO.UtilizadorDTO;
import lp.Equipa6_Comp2.entity.Utilizador;

public class UtilizadorMapper {
	// ENTITY → DTO
    public static UtilizadorDTO toDTO(Utilizador u) {
    	 return new UtilizadorDTO(
    			 u.getId(),
    			 u.getNome(),
    			 u.getEmail(),
    			 u.getPassword(),
    			 u.getMorada(),
    			 u.getTelemovel(),
    			 u.getTipoUtilizador());
    }
 // DTO → ENTITY
    public static Utilizador toEntity(UtilizadorDTO dto) {
    	if(dto==null) return null;
    	Utilizador u = new Utilizador();
    	u.setId(dto.getId());
    	u.setNome(dto.getNome());
    	u.setEmail(dto.getEmail());
    	u.setPassword(dto.getPassword());
    	u.setMorada(u.getMorada());
    	u.setTelemovel(u.getTelemovel());
    	u.setTipoUtilizador(u.getTipoUtilizador());
    	return u;
    }
}
