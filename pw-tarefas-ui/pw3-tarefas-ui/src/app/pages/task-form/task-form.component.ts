import { Task } from './../../model/task';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../../service/task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent {

  constructor(private router: Router) {}

  private fb = inject(FormBuilder);
  private taskService = inject(TaskService);

  id: string | null = null;

  taskForm = this.fb.group({
    id: [null as number | null],
    titulo: ['', Validators.required],
    descricao: [''],
    responsavel: ['', Validators.required],
    dataLimite: ['', Validators.required],
    status: ['PENDING', Validators.required],
  });

  onSubmit(): void {
    if (this.taskForm.invalid) return;

    const task: Task = this.taskForm.value as Task;

    if (this.id) {
      console.log("Executa a atualização");
    } else {
      this.taskService.criarTask(task).subscribe({
        next: (novaTask) => {
          alert("Tarefa criada com sucesso");
          this.taskForm.reset();
          this.router.navigate(['/tasks']);
        }, error: (e) => {
          alert("Não foi possível cadastrar a Tarefa.");
        }
      });

    }
  }
}
