package project.DataReader;
import java.io.*;

public class CountCharReader extends FilterReader {
	
	public int n_trains, nclasses=0;
	protected int N;
	public Xi base;
	
	public CountCharReader(Reader in){
		super(in);
		n_trains=0;
		N=0;
		base=null;
	}
	
	public int read() throws IOException {
		
		int c=0, a=0;
		Xi aux=base;
		String tmpname="";
		
		//reading the first line containing the attributes
loop1:	while( (c=super.read()) != '\r' )
		{
			// concatenates the characters of the attributes into a string
			if(c!=','){
				tmpname = new StringBuilder(tmpname).append((char)c).toString();
				continue loop1;
			}
			
			// 
			aux=base;
			
			System.out.println("New attribute (number "+(N+1)+"): "+tmpname);
			
			// if the attributes list is empty
			if(aux==null){
				base = new Xi(tmpname, null);
				nclasses++;
				System.out.println("Creates the base");
				
			// if the attributes list has already elements on it	
			}else{
				while(aux.next!=null)
					aux=aux.next;
			
				aux.next = new Xi(tmpname, null);
				nclasses++;				
			}
			
			// increments the number of attributes and clears the temporary string
			N++;
			tmpname="";
			
			// continue to iterate on the first line loop
			continue loop1;
		}
		
		// at this stage the tmpname has the class string
		
		System.out.println("Class attribute: "+tmpname);
		tmpname="";
		// What to do with the "Class" string ??????????
		
		// read the trains
		while(c!=-1){
loop2:		while( (c=super.read()) != '\r' )
			{
				// 
				if (c==-1){
					System.out.println("End of file");
					n_trains++;
					return 0;
				}
					
				
				// concatenates the characters of the attributes into a string
				if(c!=','){
					tmpname = new StringBuilder(tmpname).append((char)c).toString();
					continue loop2;
				}
				
				a=Integer.parseInt(tmpname);
				
				// process attributes here...
				
				// test the git hub ufjhgfju
				
				
				//... until here.
				
				// clears the temporary string
				tmpname="";
				
				// continue to iterate on the first line loop
				continue loop2;
			}
		
			n_trains++;
			
			// class attribute value (last value of the line)
			a=Integer.parseInt(tmpname);
			
			
			//System.out.println(" "+n_trains+" "+"a="+a);
		}
		
	return 0;
}

public static void main (String[] args){
	
	try{
		Reader src = new CountCharReader(new FileReader(args[0]));
		
		// reads the whole file
		src.read();
		
		// just a few tests 
		System.out.println("Number of trains found = " + ((CountCharReader)src).n_trains);
		System.out.println("Number of attributes found = " + ((CountCharReader)src).N);
		
		src.close();
	
	} catch(IOException e){
		
		// if file does not exist
		
	}
}


}