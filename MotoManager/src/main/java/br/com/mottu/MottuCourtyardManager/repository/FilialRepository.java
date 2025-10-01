package br.com.mottu.MottuCourtyardManager.repository;

import org.springframework.data.domain.Pageable;
import br.com.mottu.MottuCourtyardManager.model.Filial;
import br.com.mottu.MottuCourtyardManager.model.Patio;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository  extends JpaRepository<Filial, Long> {

    Page<Filial> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
