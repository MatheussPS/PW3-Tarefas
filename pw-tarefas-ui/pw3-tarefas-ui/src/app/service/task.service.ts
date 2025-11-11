import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core'; // inject -> Injetar um objeto
import { Observable } from 'rxjs';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private apiUrl ='http://localhost:8080/tarefas'
  private http = inject(HttpClient)

  findAll(): Observable<Task[]>{ 
    return this.http.get<Task[]>(this.apiUrl);
  }

  deleteTask(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`); 
  }

  criarTask(task:Task): Observable<Task>{
    return this.http.post<Task>(this.apiUrl, task); 
  }

  constructor() { }
}
