	$("#sendBtn").click(function() {
		sendMessage();
		$('#message').val('')
	});

	let sock = new SockJS("http://localhost:8888/farmocean/echo/");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	// �޽��� ����
	function sendMessage() {
		sock.send($("#message").val());
	}
	// �����κ��� �޽����� �޾��� ��
	function onMessage(msg) {
		var data = msg.data;
		const messageArea = document.getElementById('messageArea');
		$("#messageArea").append(data + "<br/>");
        messageArea.innerText.append(data+"<br>");
	}
	// ������ ������ ������ ��
	function onClose(evt) {
		$("#messageArea").append("���� ����");

	}
