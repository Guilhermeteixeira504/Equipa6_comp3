package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.TiposVoluntariadoDTO;
import Mapper.TiposVoluntariadoMapper;
import lp.Equipa6_Comp2.entity.TiposVoluntariado;
import lp.Equipa6_Comp2.service.TiposVoluntariadoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/tiposvoluntariados")
public class TiposVoluntariadoController {
	
	private final TiposVoluntariadoService tiposVoluntariadoService;
	
	public TiposVoluntariadoController(TiposVoluntariadoService tiposVoluntariadoService) {
		this.tiposVoluntariadoService = tiposVoluntariadoService;
	}
	
	@GetMapping
	public List<TiposVoluntariadoDTO> getAll() {
		return tiposVoluntariadoService.getAllTiposVoluntariados()
				.stream().map(TiposVoluntariadoMapper::toDTO).toList();
	}
	
	@GetMapping("/{id}")
	public TiposVoluntariadoDTO getById(@PathVariable Long id) {
		return TiposVoluntariadoMapper.toDTO(tiposVoluntariadoService.getTiposVoluntariado(id));
	}
	
	@PostMapping
	public TiposVoluntariadoDTO create(@RequestBody TiposVoluntariadoDTO dto) {
		TiposVoluntariado tipo = TiposVoluntariadoMapper.toEntity(dto);
		return TiposVoluntariadoMapper.toDTO(tiposVoluntariadoService.createTiposVoluntariado(tipo));
	}
	
	@PutMapping("/{id}")
	public TiposVoluntariadoDTO update(@PathVariable Long id, @RequestBody TiposVoluntariadoDTO dto) {
		TiposVoluntariado updated = tiposVoluntariadoService.updateTiposVoluntariado(id, TiposVoluntariadoMapper.toEntity(dto));
		return TiposVoluntariadoMapper.toDTO(updated);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		tiposVoluntariadoService.deleteTiposVoluntariado(id);
	}

}
