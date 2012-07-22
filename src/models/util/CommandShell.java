package models.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandShell {
	private String[] request;
	private String commanderPath;

	public CommandShell() {
	}
	
	/**
	 * Sets the commander path
	 * 
	 * @param path
	 */
	public void setCommanderPath(String path) {
		this.commanderPath = path;
	}

	/**
	 * Execute command to shell.
	 * 
	 * @param cmd
	 * @throws IOException
	 */
	final public void run(String cmd) throws IOException {	
		//System.out.println(cmd);
		List<String> outputErr = new ArrayList<String>();
		List<String> output = new ArrayList<String>();
		String outStream = null;
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(this.commanderPath + cmd);
		
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
		while ((outStream = stdInput.readLine()) != null) {
			//System.out.println(outStream);
			if (outStream != "" && outStream != "\n" && outStream != " " && outStream != "\r\n" && outStream != null && outStream.length() != 0)
				output.add(outStream);
		}

		if (output.size() > 0) {
			this.responseParse(output);
			return;
		}
		
		while ((outStream = stdError.readLine()) != null) {
			System.out.println(outStream);
			outputErr.add(outStream);
		}
		
		this.responseParse(outputErr);
	}

	/**
	 * Returns the command response.
	 * 
	 * @return
	 */
	public String[] getResponse() {
		return this.request;
	}
	
	private void responseParse(List<String> obj) {
		this.request = new String[obj.size()];
		for(int i = 0 ; i < request.length ; i++)
			this.request[i] = obj.get(i);
	}
}
