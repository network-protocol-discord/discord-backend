<!doctype html>
<html lang="en">
<head>
    <title>Websocket Chat</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>
<body>
<div class="container" id="app" style="display: none;">
    <div class="row">
        <div class="col-md-12">
            <h3>채팅방 리스트</h3>
        </div>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">방제목</label>
        </div>
        <input type="text" class="form-control" id="room_name">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" onclick="createRoom()">채팅방 개설</button>
        </div>
    </div>
    <ul class="list-group" id="chatroom_list">
    </ul>
</div>
<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    window.onload = function() {
        document.getElementById('app').style.display = 'block';
        findAllRoom();
    }

    function findAllRoom() {
        axios.get('/chat/rooms')
            .then(function(response) {
                var chatrooms = response.data;
                var listElement = document.getElementById('chatroom_list');
                listElement.innerHTML = '';
                chatrooms.forEach(function(room) {
                    var listItem = document.createElement('li');
                    listItem.className = 'list-group-item list-group-item-action';
                    listItem.innerText = room.name;
                    listItem.addEventListener('click', function() {
                        enterRoom(room.roomId);
                    });
                    listElement.appendChild(listItem);
                });
            })
            .catch(function(error) {
                console.error('Error fetching chatrooms:', error);
            });
    }

    function createRoom() {
        var roomName = document.getElementById('room_name').value;
        if (roomName === '') {
            alert('방 제목을 입력해 주세요.');
            return;
        }
        var params = new URLSearchParams();
        params.append('name', roomName);
        axios.post('/chat/room', params)
            .then(function(response) {
                alert(response.data.name + '방 개설에 성공하였습니다.');
                document.getElementById('room_name').value = '';
                findAllRoom();
            })
            .catch(function(error) {
                alert('채팅방 개설에 실패하였습니다.');
                console.error('Error creating room:', error);
            });
    }

    function enterRoom(roomId) {
        var sender = prompt('대화명을 입력해 주세요.');
        if (sender !== null && sender.trim() !== '') {
            localStorage.setItem('wschat.sender', sender);
            localStorage.setItem('wschat.roomId', roomId);
            location.href = '/chat/room/enter/' + roomId;
        }
    }
</script>
</body>
</html>
