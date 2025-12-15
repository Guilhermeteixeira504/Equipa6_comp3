package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.VoluntarioDTO;
import Mapper.VoluntarioMapper;
import lp.Equipa6_Comp2.entity.Voluntario;
import lp.Equipa6_Comp2.service.VoluntarioService;

import java.util.List;

@RestController
@RequestMapping("/voluntariado/voluntarios")
public class VoluntarioController {
	
	private final VoluntarioService voluntarioService;
	
	public VoluntarioController(VoluntarioService voluntarioService) {
		this.voluntarioService = voluntarioService;
	}
	
	@GetMapping
	public List<VoluntarioDTO> getAllVoluntarios() {
		return voluntarioService.getAllVoluntarios().stream().map(VoluntarioMapper::toDTO).toList();
	}
	
	@GetMapping("/{id}")
	public VoluntarioDTO getById(@PathVariable Long id) {
		return VoluntarioMapper.toDTO(voluntarioService.getVoluntario(id));
	}
	
	@PostMapping
	public VoluntarioDTO create(@RequestBody VoluntarioDTO dto) {
		Voluntario v = VoluntarioMapper.toEntity(dto);
		return VoluntarioMapper.toDTO(voluntarioService.createVoluntario(v));
	}
	
	@PutMapping("/{id}")
	public VoluntarioDTO update(@PathVariable Long id, @RequestBody VoluntarioDTO v) {
		Voluntario updated = voluntarioService.updateVoluntario(id, VoluntarioMapper.toEntity(v));
		return VoluntarioMapper.toDTO(updated);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		voluntarioService.deleteVoluntario(id);
	}

	
}
