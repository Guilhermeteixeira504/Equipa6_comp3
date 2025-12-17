package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.LoginResponseDTO;
import DTO.UtilizadorDTO;
import Mapper.LoginResponseMapper;
import Mapper.UtilizadorMapper;
import lp.Equipa6_Comp2.entity.Utilizador;
import lp.Equipa6_Comp2.service.UtilizadorService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/utilizadores")
@CrossOrigin("*")
public class UtilizadorController {
	
	private final UtilizadorService utilizadorService;
	
	public UtilizadorController(UtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}
	@GetMapping("/utilizadores/email/{email}")
	public UtilizadorDTO getByEmail(@PathVariable String email) {
	    return UtilizadorMapper.toDTO(utilizadorService.findByEmail(email));
	}
	@GetMapping
	public List<Utilizador> getAll() {
		return utilizadorService.getAllUtilizadores();
	}
	
	@PostMapping
    public Utilizador createUtilizador(@RequestBody Utilizador utilizador) {
        return utilizadorService.createUtilizador(utilizador);
    }

    @GetMapping("/{id}")
    public Utilizador getUtilizador(@PathVariable int id) {
        return utilizadorService.getUtilizadorById(id);
    }

	
	@PutMapping("/{id}")
	public Utilizador update(@PathVariable Long id, @RequestBody Utilizador u) {
		return utilizadorService.updateUtilizador(id, u);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		utilizadorService.deleteUtilizador(id);
	}

	
	//login
		@PostMapping("/login")
		public LoginResponseDTO login(@RequestBody Loginrequest request) {
		    Utilizador u = utilizadorService.login(request.email, request.password);
		    return LoginResponseMapper.toDTO(u);
		}
		private static class Loginrequest {
		    public String email;
		    public String password;
		}
		
		//alterar password
		@PutMapping("/alterarpassword")
		public Utilizador alterarPassword(@RequestBody PasswordRequest request) {
		    return utilizadorService.alterarPassword(request.email, request.novaPassword);
		}

		private static class PasswordRequest {
		    public String email;
		    public String novaPassword;
		}
		
}
	

