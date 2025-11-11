export interface Task {
  id?: number; // Opcional, pode não ser preenchido
  titulo: string;
  descricao: string;
  dataLimite: string; // ISO format
  status: 'PENDING' | 'IN_PROGRESS' | 'COMPLETED';
  responsavel: string;
}
