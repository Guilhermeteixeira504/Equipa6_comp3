package lp.Equipa6_Comp2.service;

import org.springframework.stereotype.Service;

import lp.Equipa6_Comp2.entity.Voluntario;
import lp.Equipa6_Comp2.repository.VoluntarioRepository;
import java.util.List;

@Service
public class VoluntarioService{
	
	private final VoluntarioRepository voluntarioRepository;
	
	public VoluntarioService(VoluntarioRepository voluntarioRepository) {
		this.voluntarioRepository = voluntarioRepository;
	}
	
	public List<Voluntario> getAllVoluntarios(){
		return voluntarioRepository.findAll();
	}
	
	public Voluntario getVoluntario(Long id) {
		return voluntarioRepository.findById(id).orElse(null);
	}
	
	public Voluntario createVoluntario(Voluntario v) {
		return voluntarioRepository.save(v);
	}
	
	public Voluntario updateVoluntario(Long id, Voluntario v) {
		Voluntario existing = voluntarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Voluntário não encontrado"));
		existing.setNome(v.getNome());
		existing.setEmail(v.getEmail());
		existing.setPassword(v.getPassword());
		existing.setMorada(v.getMorada());
		existing.setTelemovel(v.getTelemovel());
		
		return voluntarioRepository.save(existing);
	}
	
	public void deleteVoluntario(Long id) {
		voluntarioRepository.deleteById(id);
	}
	

}
