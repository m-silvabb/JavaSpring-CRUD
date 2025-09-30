package com.java.aula.lista_tarefas.business;

import com.java.aula.lista_tarefas.infrastructure.entity.Tarefa;
import com.java.aula.lista_tarefas.infrastructure.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Transactional
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodasAsTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Integer id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o id: " + id));
    }

    @Transactional
    public Tarefa editarTarefa(Integer id, Tarefa tarefaComNovosDados) {
        Tarefa tarefaExistente = buscarTarefaPorId(id);

        tarefaExistente.setTitulo(
                tarefaComNovosDados.getTitulo() != null ? tarefaComNovosDados.getTitulo() : tarefaExistente.getTitulo()
        );
        tarefaExistente.setDescricao(
                tarefaComNovosDados.getDescricao() != null ? tarefaComNovosDados.getDescricao() : tarefaExistente.getDescricao()
        );
        tarefaExistente.setStatus(
                tarefaComNovosDados.getStatus() != null ? tarefaComNovosDados.getStatus() : tarefaExistente.getStatus()
        );

        return tarefaRepository.save(tarefaExistente);
    }

    @Transactional
    public void excluirTarefa(Integer id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada com o id: " + id);
        }
        tarefaRepository.deleteById(id);
    }
}