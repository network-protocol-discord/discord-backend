<!doctype html>
<html lang="en">
<head>
    <title>Websocket ChatRoom</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app">
    <div>
        <h2 id="room-name"></h2>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">내용</label>
        </div>
        <input type="text" class="form-control" id="message-input">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" onclick="sendMessage()">보내기</button>
        </div>
    </div>
    <ul class="list-group" id="message-list">
    </ul>
    <div></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script>
    var sock = new SockJS("/ws-stomp");
    var ws = Stomp.over(sock);
    var reconnect = 0;
    var roomId = '${roomId}'; // 서버에서 전달된 roomId
    var serverId = '${serverId}';
    var sender = localStorage.getItem('wschat.sender');
    var token = localStorage.getItem('authToken');
    var room = {};
    var messages = [];

    document.addEventListener('DOMContentLoaded', function () {
        findRoom();
        connect();
        document.getElementById('message-input').addEventListener('keypress', function (e) {
            if (e.key === 'Enter') {
                sendMessage();
            }
        });
    });

    function findRoom() {
        console.log('Finding room with serverId:', serverId, 'and roomId:', roomId);
        axios.get('/server/'+ serverId + '/chat/room/' + roomId, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        }).then(function (response) {
            room = response.data;
            document.getElementById('room-name').textContent = room.name;
        }).catch(function (error) {
            if (error.response.status === 401) {
                window.location.href = '/server/'+ serverId + '/chat/room';
            } else {
                console.error('Error fetching room data:', error);
            }
        });
    }

    function sendMessage() {
        var messageInput = document.getElementById('message-input');
        var message = messageInput.value;
        if (message.trim() !== '') {
            ws.send("/pub/chat/message", {}, JSON.stringify({
                type: 'TALK',
                roomId: roomId,
                sender: sender,
                message: message
            }));
            messageInput.value = '';
        }
    }

    function recvMessage(recv) {
        console.log('recv object:', recv)
        if (recv.type === 'ENTER') {
            messages.unshift({
                type: recv.type,
                sender: '[알림]', // 'type'이 'ENTER'일 때 '[알림]'으로 설정
                message: recv.message
            });
        } else {
            // 'type'이 'ENTER'가 아닌 경우, 'sender'에 'recv.sender'를 그대로 사용
            messages.unshift({
                type: recv.type,
                sender: recv.sender,
                message: recv.message
            });
        }
        var messageList = document.getElementById('message-list');
        var listItem = document.createElement('li');
        listItem.className = 'list-group-item';
        listItem.textContent = recv.sender + ' - ' + recv.message;
        messageList.prepend(listItem);
    }

    function connect() {
        ws.connect({ Authorization: 'Bearer ' + token }, function (frame) {
            ws.subscribe("/sub/chat/room/" + roomId, function (message) {
                var recv = JSON.parse(message.body);
                recvMessage(recv);
            });
            ws.send("/pub/chat/message", {}, JSON.stringify({
                type: 'ENTER',
                roomId: roomId,
                sender: sender
            }));
        }, function (error) {
            if (reconnect++ <= 5) {
                setTimeout(function () {
                    console.log("connection reconnect");
                    sock = new SockJS("/ws-stomp");
                    ws = Stomp.over(sock);
                    connect();
                }, 10 * 1000);
            }
        });
    }
</script>
</body>
</html>