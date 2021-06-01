/**
 * 响应实体
 */
export interface ReturnForm<T> {
  success: boolean;
  status: number;
  message: string;
  data: T;
}

/**
 * 分页响应
 */
export interface Paging<T> {
  total: number;
  pageNum: number;
  pageSize: number;
  records: Array<T>;
}
