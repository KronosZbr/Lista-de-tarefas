package com.example.CRUD.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUD.Model.Tarefa;
import com.example.CRUD.TarefaRepository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/assessment")
    public String index() {
        return "index";
    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        tarefa.setDataCriacao(new Date());
        return tarefaRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizado) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setTitulo(tarefaAtualizado.getTitulo());
            tarefa.setDescricao(tarefaAtualizado.getDescricao());
            tarefa.setConcluida(tarefaAtualizado.isConcluida());
            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}
