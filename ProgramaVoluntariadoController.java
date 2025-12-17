package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.ProgramaVoluntariadoDTO;
import Mapper.ProgramaVoluntariadoMapper;
import lp.Equipa6_Comp2.entity.ProgramaVoluntariado;
import lp.Equipa6_Comp2.service.ProgramaVoluntariadoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/programasvoluntariado")
public class ProgramaVoluntariadoController {
	
	private final ProgramaVoluntariadoService programaVoluntariadoService;
	
	public ProgramaVoluntariadoController(ProgramaVoluntariadoService programaVoluntariadoService) {
		this.programaVoluntariadoService = programaVoluntariadoService;
	}
	
	@GetMapping
	public List<ProgramaVoluntariadoDTO> getAll() {
		return programaVoluntariadoService.getAllProgramasVoluntariado()
				.stream().map(ProgramaVoluntariadoMapper::toDTO).toList();
	}
	
	@GetMapping("/{id}")
	public ProgramaVoluntariadoDTO getById(@PathVariable Long id) {
		return ProgramaVoluntariadoMapper.toDTO(programaVoluntariadoService.getProgramaVoluntariado(id));
	}
	
	@PostMapping
	public ProgramaVoluntariadoDTO create(@RequestBody ProgramaVoluntariadoDTO dto) {
		ProgramaVoluntariado programa = programaVoluntariadoService.createProgramaVoluntariado(ProgramaVoluntariadoMapper.toEntity(dto));
		return ProgramaVoluntariadoMapper.toDTO(programa);
	}
	
	@PutMapping("/{id}")
	public ProgramaVoluntariadoDTO update(@PathVariable Long id, @RequestBody ProgramaVoluntariadoDTO dto) {
		ProgramaVoluntariado updated = programaVoluntariadoService.updateProgramaVoluntariado(id, ProgramaVoluntariadoMapper.toEntity(dto));
		return ProgramaVoluntariadoMapper.toDTO(updated);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		programaVoluntariadoService.deleteProgramaVoluntariado(id);
	}

}
