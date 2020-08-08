"use strict";

const CalculatorClient =  {};
CalculatorClient.supportedOperation = ['+','-','/','*'];

CalculatorClient.isValidInput = (inputObj) =>{
  return inputObj.leftVal
      && inputObj.rightVal
      && inputObj.operator
      && CalculatorClient.supportedOperation.includes(inputObj.operator)
}

CalculatorClient.sendMessage = ()=> {
    const inputObj = {
        leftVal: $('#left-value').val(),
        rightVal: $('#right-value').val(),
        operator: $('#operator').val(),
        username: $('#joined-user').text()
    }
    if(CalculatorClient.isValidInput(inputObj)){
        CalculatorClient.socket.send(JSON.stringify(inputObj));
        $('#left-value').val('');
        $('#right-value').val('');
        $('#operator').val('+');
    }else{
        alert("Please enter valid input");
    }
};

CalculatorClient.connect = (host) =>{
    if ('WebSocket' in window) {
        CalculatorClient.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        CalculatorClient.socket = new MozWebSocket(host);
    } else {
        console.log('Error: WebSocket is not supported by this browser.');
        return;
    }

    CalculatorClient.socket.onopen = function(){
        console.log("Info: connection opened");
        $('#calculate').on("click", () => CalculatorClient.sendMessage());
    };

    CalculatorClient.socket.onclose = function(){
        console.log("Info: connection closed");
    };

    CalculatorClient.socket.onmessage = function(message){
    
        const li =`<li> ${message.data}</li>`;
        const totalResult = $('#result').children();
        if(totalResult.length===10){
            $('#result li:last-child').remove();
        }
        $('#result').prepend(li);
    };
};

CalculatorClient.initialize = () =>{
    const ep = '/awsome-web-calculator/websocket/calculate';
    if(window.location.protocol == 'http:'){
        CalculatorClient.connect('ws://' + window.location.host + ep);
    } else{
        CalculatorClient.connect('wss://' + window.location.host + ep);
    }
}
CalculatorClient.initialize();
