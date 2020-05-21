package com.aikan.util;

import java.io.Serializable;

public class PageUtil implements Serializable {
	// 当前第几页，默认第一页
		private int pageNo = 1;
		// 每页显示几条记录，默认显示5条
		private int pageSize = 6;
		// 符合条件的总记录数
		private int totalCount = 0;
		// 总页码数
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
			// 通过总记录数和每页的记录数，算出总页码
			if (this.totalCount % this.pageSize == 0) {
				this.totalPages = this.totalCount / this.pageSize;
			} else {
				this.totalPages = this.totalCount / this.pageSize + 1;
			}
			return totalPages;
		}

}
