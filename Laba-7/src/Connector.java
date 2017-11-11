import java.io.*;
import java.util.LinkedList;

public class Connector {
	
	private String filename;
	
	public Connector( String filename ) {
		this.filename = filename;
	}
	
	public void write( LinkedList<Product> list) throws IOException {
		FileOutputStream fos = new FileOutputStream (filename);
		try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
			oos.writeInt( list.size() );
			for ( Product pd: list) {
				oos.writeObject( pd );
			}
			oos.flush();
		}
	}
	
	public LinkedList<Product> read() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
			int length = oin.readInt();
			LinkedList<Product> result = new LinkedList<Product>();
			for ( int i = 0; i < length; i++ ) {
				result.add((Product) oin.readObject());
			}
			return result;	
		}
	}
	
}
