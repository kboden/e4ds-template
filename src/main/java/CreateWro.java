import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class CreateWro {

	/* Fiddling around with Ext.Loader.history 
	 * Output could be parsed with this programm and output pasted into wro.xml
	 * This way you could use ext.js
	 */
	
	public static void main(String[] args) throws IOException {
		InputStream is = CreateWro.class.getResourceAsStream("/js_dependencies.txt");
		List<String> lines = IOUtils.readLines(is);
		is.close();
		
		
		ObjectMapper mapper = new ObjectMapper();
		for (String line : lines) {
			System.out.println("<group name=\"...\">");
			List<String> depList = mapper.readValue(line, new TypeReference<List<String>>() {/*nothing here*/});
			
			for (String dep : depList) {
				
				System.out.print("<js>/");
				if (dep.startsWith("Ext.")) {
					System.out.print(dep.replace("Ext.", "ext.src.").replace(".", "/"));
				} else if (dep.startsWith("E4ds.")) {
					System.out.print(dep.replace("E4ds.", "app.").replace(".", "/"));
				} else {
					System.out.print("TODO: " + dep);
				}
				System.out.println(".js</js>");
				
			}

			System.out.println("</group>");
			System.out.println();
			
		}
				
		
		
		

	}

}
