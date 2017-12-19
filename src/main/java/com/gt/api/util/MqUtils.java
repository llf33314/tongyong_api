package com.gt.api.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.gt.api.bean.mq.MqBean;
import com.rabbitmq.client.*;
import net.sf.json.JSONObject;

/**
 * MQ消息队列
 * @author Administrator
 *
 */
public class MqUtils {
	public static ConnectionFactory factory;
	public static Connection connection;
	public static Channel channel;
	static Address[]  add ;
	public MqUtils(List<MqBean> bean, String user, String password) throws IOException, TimeoutException{
		if(channel.equals(null)||channel==null){
			for(int i=0;i<bean.size();i++){
				add[i] = new Address(bean.get(i).getMqIp(),bean.get(i).getPort());
			}
			this.MqContent(user,password);
		}
	}
	public void MqContent(String user,String password) throws IOException, TimeoutException{
		factory = new ConnectionFactory();  
		factory.setUsername(user);
		factory.setPassword(password);
		factory.setAutomaticRecoveryEnabled(true);	//自动从网络错误恢复
		factory.setConnectionTimeout(500000);	//连接超时时间，单位毫秒
		connection = factory.newConnection(add);
		channel = connection.createChannel();  //创建消息弹到
	}
	//商城内部的消息队列 exchange转换器，queueName队列
	public static void MqMessage(String exchange,String queueName,String message) throws IOException, TimeoutException{
		channel.exchangeDeclare(exchange, "direct", true);//声明转发器和类型
		channel.queueDeclare(queueName, true, false, false, null);  
		channel.basicPublish("", queueName, null, message.getBytes());
	}
}
