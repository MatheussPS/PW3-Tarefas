import { Component, OnInit, inject } from '@angular/core';
import { Task } from '../../model/task';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TaskService } from '../../service/task.service';

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {

  tasks: Task[] = [];
  private router = inject(Router);
  private taskService = inject(TaskService);

  ngOnInit(): void {
    this.loadTask();
  }

  newTask() {
    this.router.navigate(['/tasks/new']);
  }

  loadTask(): void{
    this.taskService.findAll().subscribe((tasks) =>{
      this.tasks = tasks;
    })
  }

  deleteTask(id: number | undefined) {
    if (id && confirm('Tem certeza que deseja excluir esta tarefa?')) {
      this.taskService.deleteTask(id).subscribe(() => this.loadTask());
      console.log("Exclusão realizada");
    }
  }

  editTask(id: number | undefined) {
    if (id) this.router.navigate(['/tasks', id]);
  }
}
