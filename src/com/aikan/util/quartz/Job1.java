package com.aikan.util.quartz;

//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;


public class Job1 //extends QuartzJobBean 
{

	private int timeout;
	private static int i = 0;

	// ���ȹ���ʵ�����󣬾���timeoutʱ�俪ʼִ�е���
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Ҫ���ȵľ�������
	 */
//	@Override
//	protected void executeInternal(JobExecutionContext context)
//			throws JobExecutionException {
//		System.out.println("��ʱ����ִ���С�");
//	}
}