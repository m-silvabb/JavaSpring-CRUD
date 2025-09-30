package com.java.aula.lista_tarefas.infrastructure.repository;

import com.java.aula.lista_tarefas.infrastructure.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    List<Tarefa> findByStatus(String status);

    List<Tarefa> findByTituloContainingIgnoreCase(String keyword);

}