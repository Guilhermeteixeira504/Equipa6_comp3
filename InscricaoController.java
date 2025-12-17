package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.InscricaoDTO;
import Mapper.InscricaoMapper;
import lp.Equipa6_Comp2.entity.Inscricao;
import lp.Equipa6_Comp2.service.InscricaoService;
import java.util.List;

@RestController
@RequestMapping("/voluntariado/inscricoes")
public class InscricaoController {
	
	private final InscricaoService inscricaoService;
	
	public InscricaoController(InscricaoService inscricaoService) {
	    this.inscricaoService = inscricaoService;
	}
	
	@GetMapping
	public List<InscricaoDTO> getAll() {
		return inscricaoService.getAllInscricoes()
				.stream().map(InscricaoMapper::toDTO).toList();
	}
	
	@GetMapping("/{id}")
	public InscricaoDTO getById(@PathVariable Long id) {
		return InscricaoMapper.toDTO(inscricaoService.getInscricao(id));
	}
	
	@PostMapping
	public InscricaoDTO create(@RequestBody InscricaoDTO insc) {
		Inscricao inscricao = inscricaoService.createInscricao(insc.getProgramaId(), InscricaoMapper.toEntity(insc));
		return InscricaoMapper.toDTO(inscricao);
	}
	
	@PutMapping("/{id}")
	public InscricaoDTO update(@PathVariable Long id, @RequestBody InscricaoDTO insc) {
		Inscricao updated = inscricaoService.updateInscricao(id, InscricaoMapper.toEntity(insc));
		return InscricaoMapper.toDTO(updated);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		inscricaoService.deleteInscricao(id);
	}
	   

	@PostMapping("/inscrever")
    public InscricaoDTO inscrever(@RequestBody InscricaoDTO dto) {
		Inscricao insc = inscricaoService.inscrever(dto.getProgramaId(),dto.getVoluntarioId() );
		return InscricaoMapper.toDTO(insc);
    }
	
	
}
