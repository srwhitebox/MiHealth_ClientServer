package com.mitac.mihealth.client;

import javax.swing.SwingWorker;

import com.mitac.mihealth.client.view.MainFrame;

import john.MiHEALTH.MiHEALTHproperties;

public class AnswerWorker extends SwingWorker<Integer, Integer>
{
	private MainFrame f;
	public AnswerWorker(MainFrame f){
		this.f = f;
	}
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)*/
    protected Integer doInBackground() throws Exception
    {
        // Do a time-consuming task.
    	john.EDU.Student.exportExcel(MiHEALTHproperties.getExcelPath());
        return 42;
    }

    protected void done()
    {
        try
        {
        	get();
        	f.done();
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage().toString());
            f.error(e);
        }
    }
}