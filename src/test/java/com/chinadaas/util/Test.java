package com.chinadaas.util;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import javax.management.remote.rmi.RMIServer;

/**
 * projectName: chinadaas-data<br>
 * desc: TODO<br>
 * date: 2015年4月1日 下午4:52:01<br>
 * 
 * @author 开发者真实姓名[Andy]
 */
public class Test {

	public static MBeanServerConnection getc() {
		try {
			MBeanServerConnection mbs = null;
			Registry registry = LocateRegistry.getRegistry("192.168.11.11",
					1100);
			RMIServer stub = null;
			JMXConnector jmxc = null;
			if (stub == null) {
				stub = (RMIServer) registry.lookup("jmxrmi");
			}
			jmxc = new RMIConnector(stub, null);
			jmxc.connect();
			mbs = jmxc.getMBeanServerConnection();
			return mbs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static MBeanServerConnection getc1() throws IOException {
		String jmxURL = "service:jmx:rmi:///jndi/rmi://192.168.11.11:1100/jmxrmi";
		JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("jmx.remote.credentials", new String[] { "monitorRole", "QED" });
		JMXConnector connector = JMXConnectorFactory.connect(serviceURL, map);
		return connector.getMBeanServerConnection();
	}

	public static void main(String[] args) throws Exception {
//		BrokerFacade facade = new SingletonBrokerFacade();
//		SessionPool pool = new SessionPool();
//		ConnectionFactory factory = new org.apache.activemq.ActiveMQConnectionFactory("tcp://192.168.11.11:61616");
//		Connection connection = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
//				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.11.11:61616").createConnection();
//		ActiveMQConnection conn = ActiveMQConnection.makeConnection("admin", "admin", "tcp://192.168.11.11:61616");
//		conn.getDestinationSource();
//		conn.close();
	}

}
