package Mapper;

import DTO.LoginResponseDTO;
import lp.Equipa6_Comp2.entity.Utilizador;

public class LoginResponseMapper {
	// ENTITY → DTO
    public static LoginResponseDTO toDTO(Utilizador u) {

        return new LoginResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getTipoUtilizador()
        );
    }


    // DTO → ENTITY
    public static Utilizador toEntity(LoginResponseDTO dto) {

        if (dto == null) return null;

        Utilizador u = new Utilizador();

        u.setId(dto.getId());
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setTipoUtilizador(dto.getTipoUtilizador());

        return u;
    }

}
