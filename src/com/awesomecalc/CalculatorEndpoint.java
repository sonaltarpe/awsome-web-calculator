package com.awesomecalc;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint(value = "/websocket/calculate")
public class CalculatorEndpoint {

    Session session;
    static List<CalculatorEndpoint> calculatorEndpointList = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session =session;
        calculatorEndpointList.add(this);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Socket Closed:"+reason.getReasonPhrase());
        calculatorEndpointList.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
       broadcast(message);
    }

    private void broadcast(String message)  {
        for (CalculatorEndpoint client : calculatorEndpointList) {
            try {
            	Gson g = new Gson(); 
            	InputData inputData = g.fromJson(message, InputData.class);
                System.out.println(inputData.getResult());
                client.session.getBasicRemote().sendText(inputData.getResult());
            } catch (IOException e) {
                calculatorEndpointList.remove(this);
                try {
                    client.session.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}
