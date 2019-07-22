package com.qdu.websocket;

import com.qdu.beans.CUser;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;


import javax.websocket.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Alpha-LGC on 2019/4/29.
 */
//configurator = WebsocketConfig.class 该属性就是我上面配置的信息
@ServerEndpoint(value = "/websocket", configurator = SpringWebSocketConfig.class)
@Component    //此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
public class MyWebSocket {

        //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
        private static int onlineCount = 0;

        //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
       // private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, MyWebSocket> webSocketSet = new ConcurrentHashMap<String, MyWebSocket>();
        //与某个客户端的连接会话，需要通过它来给客户端发送数据
        private Session session;
        private String sessid;
        private String  uname;

        /**
         * 连接建立成功调用的方法
         * <p>
         * config用来获取WebsocketConfig中的配置信息
         */
        @OnOpen
        public void onOpen(Session session, EndpointConfig config) {

            //获取WebsocketConfig.java中配置的“sessionId”信息值
            org.apache.shiro.session.Session httpSessionId = (org.apache.shiro.session.Session)config.getUserProperties().get("sessionId");
            String username = ((CUser)httpSessionId.getAttribute("USER_SESSION")).getUsername();
            sessid = httpSessionId.getId().toString();
            this.session = session;
            int count = -1;
            System.out.println(sessid);
            //System.out.println(webSocketSet.get(username).sessid);
            if(!webSocketSet.isEmpty() && webSocketSet.containsKey(username) && !sessid.equals(webSocketSet.get(username).sessid) ){
                MyWebSocket item = webSocketSet.get(username);
                //!!!!!!!!!!!!!1
                try {
                    System.out.println("有人重复登录");
                   count =  onlineCount-1;
                    count++;
                    item.sendMessage("12138");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            uname = username;
            webSocketSet.put(username,this);     //加入set中


            addOnlineCount();           //在线数加1
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

            try {
                if(count != -1) {
                    if(webSocketSet.size()>1) {
                        for (Iterator<Map.Entry<String, MyWebSocket>> car = webSocketSet.entrySet().iterator(); car.hasNext(); ) {
                            Map.Entry<String, MyWebSocket> entry = car.next();
                            MyWebSocket mys = entry.getValue();
                            mys.sendMessage("当前在线人数为:" + count);
                        }
                    }


                }else {

                    if(webSocketSet.size()>0) {
                        for (Iterator<Map.Entry<String, MyWebSocket>> car = webSocketSet.entrySet().iterator(); car.hasNext(); ) {
                            Map.Entry<String, MyWebSocket> entry = car.next();
                            MyWebSocket mys = entry.getValue();
                            mys.sendMessage("当前在线人数为:" + getOnlineCount());
                           /* if(!sessid.equals(mys.sessid) ){
                                mys.sendMessage("当前在线人数为:" + getOnlineCount());
                            }*/
                        }
                    }

                }
            } catch (IOException e) {
                System.out.println("IO异常");
            }
        }

        /**
         * 连接关闭调用的方法
         */
        @OnClose
        public void onClose() {
            webSocketSet.remove(this.uname);  //从set中删除
            subOnlineCount();           //在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
            if(webSocketSet.size()>=1) {
                for (Iterator<Map.Entry<String, MyWebSocket>> car = webSocketSet.entrySet().iterator(); car.hasNext(); ) {
                    Map.Entry<String, MyWebSocket> entry = car.next();
                    MyWebSocket mys = entry.getValue();
                    System.out.println(mys.sessid+"!!!!!!!!!!!!!!!!!!!");
                    try {

                         mys.sendMessage("当前在线人数为:" + getOnlineCount());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        /**
         * 收到客户端消息后调用的方法
         *
         * @param message 客户端发送过来的消息
         */
        @OnMessage
        public void onMessage(String message, Session session) {
            System.out.println("来自客户端的消息:" + message);

            //群发消息
            for (MyWebSocket item : webSocketSet.values()) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 发生错误时调用
         */
        @OnError
        public void onError(Session session, Throwable error) {
            System.out.println("发生错误");
            error.printStackTrace();
        }


        public void sendMessage(String message) throws IOException {
            this.session.getBasicRemote().sendText(message);
            //this.session.getAsyncRemote().sendText(message);
        }


        /**
         * 群发自定义消息
         */
        public static void sendInfo(String message) throws IOException {
            for (MyWebSocket item : webSocketSet.values()) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    continue;
                }
            }
        }

        public static synchronized int getOnlineCount() {
            return onlineCount;
        }

        public static synchronized void addOnlineCount() {
            MyWebSocket.onlineCount++;
        }

        public static synchronized void subOnlineCount() {
            MyWebSocket.onlineCount--;
        }

}
