public class Client {
	
	Integer x1 = 1;
	String x2 = "Ales";
	Double x3 = -1.33;
	Boolean x4 = false;
	
	private void send(Server server) {		
		Message response = server.receive(Message.init()
												 .put(ParameterName.JOE, x1)
												 .put(ParameterName.SHANNON, x2)
												 .put(ParameterName.KYLE, x3)
												 .put(ParameterName.ALES, x4));
		
		System.out.println(response.toString());
	}

	public static void main(String[] args) {
		Server server = new Server(false);
		Client client = new Client();
		
		client.send(server);
	}
}
