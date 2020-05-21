package com.aikan.util;

import java.io.Serializable;

public class PageUtil implements Serializable {
	// ��ǰ�ڼ�ҳ��Ĭ�ϵ�һҳ
		private int pageNo = 1;
		// ÿҳ��ʾ������¼��Ĭ����ʾ5��
		private int pageSize = 6;
		// �����������ܼ�¼��
		private int totalCount = 0;
		// ��ҳ����
		private int totalPages = 0;

		public PageUtil() {

		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public int getTotalPages() {
			// ͨ���ܼ�¼����ÿҳ�ļ�¼���������ҳ��
			if (this.totalCount % this.pageSize == 0) {
				this.totalPages = this.totalCount / this.pageSize;
			} else {
				this.totalPages = this.totalCount / this.pageSize + 1;
			}
			return totalPages;
		}

}
