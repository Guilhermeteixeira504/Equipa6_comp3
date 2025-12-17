package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.EdicaoDTO;
import Mapper.EdicaoMapper;
import lp.Equipa6_Comp2.entity.Edicao;
import lp.Equipa6_Comp2.service.EdicaoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/edicoes")
public class EdicaoController {
	
	private final EdicaoService edicaoService;
	
	public EdicaoController(EdicaoService edicaoService) {
		this.edicaoService = edicaoService;
	}
	
	@GetMapping
	public List<EdicaoDTO> getAll() {
		return edicaoService.getAllEdicoes()
				.stream().map(EdicaoMapper::toDTO).toList();
	}
	
	@GetMapping("/{id}")
	public EdicaoDTO getById(@PathVariable Long id) {
		return EdicaoMapper.toDTO(edicaoService.getEdicao(id));
	}
	
	@PostMapping
	public EdicaoDTO create(@RequestBody EdicaoDTO e) {
		Edicao edicao = edicaoService.createEdicao(EdicaoMapper.toEntity(e));
		return EdicaoMapper.toDTO(edicao);
	}
	
	@PutMapping("/{id}")
	public EdicaoDTO update(@PathVariable Long id, @RequestBody EdicaoDTO dto) {
		Edicao updated = edicaoService.updateEdicao(id, EdicaoMapper.toEntity(dto));
		return EdicaoMapper.toDTO(updated);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		edicaoService.deleteEdicao(id);
	}

}
