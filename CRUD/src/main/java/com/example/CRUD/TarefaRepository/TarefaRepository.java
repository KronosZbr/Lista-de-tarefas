package com.example.CRUD.TarefaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRUD.Model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
