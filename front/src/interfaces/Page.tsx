export default interface Page<T> {
  totalPages: number;
  totalElements: number;
  content: T[];
}
