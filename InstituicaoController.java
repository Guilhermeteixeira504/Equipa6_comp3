package lp.Equipa6_Comp2.controller;

import org.springframework.web.bind.annotation.*;

import DTO.InstituicaoDTO;
import Mapper.InstituicaoMapper;
import lp.Equipa6_Comp2.entity.Instituicao;
import lp.Equipa6_Comp2.service.InstituicaoService;

import java.util.List;

@RestController
@RequestMapping("/voluntariado/instituicoes")
public class InstituicaoController {

    private final InstituicaoService instituicaoService;

    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @GetMapping
    public List<InstituicaoDTO> getAll() {
        return instituicaoService.getAllInstituicoes()
                .stream()
                .map(InstituicaoMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public InstituicaoDTO getById(@PathVariable Long id) {
        return InstituicaoMapper.toDTO(
                instituicaoService.getInstituicao(id)
        );
    }

    @PostMapping
    public InstituicaoDTO create(@RequestBody InstituicaoDTO dto) {
        Instituicao inst =
                instituicaoService.createInstituicao(
                        InstituicaoMapper.toEntity(dto)
                );
        return InstituicaoMapper.toDTO(inst);
    }

    @PutMapping("/{id}")
    public InstituicaoDTO update(@PathVariable Long id,
                                 @RequestBody InstituicaoDTO dto) {

        Instituicao updated =
                instituicaoService.updateInstituicao(
                        id,
                        InstituicaoMapper.toEntity(dto)
                );

        return InstituicaoMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        instituicaoService.deleteInstituicao(id);
    }
}