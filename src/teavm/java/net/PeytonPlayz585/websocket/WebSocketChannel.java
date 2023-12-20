package net.PeytonPlayz585.websocket;

import org.teavm.jso.JSBody;
import org.teavm.jso.ajax.*;
import org.teavm.jso.dom.events.MessageEvent;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.DataView;
import org.teavm.jso.typedarrays.Int8Array;
import org.teavm.jso.websocket.WebSocket;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

public class WebSocketChannel {
    private final WebSocket socket;
    private final Queue<MessageEvent> messageQueue;
    private final ByteBuffer readBuffer;
    private boolean reading;
    private boolean isConnected = false;

    public WebSocketChannel(String url) {
        socket = WebSocket.create(url);
        messageQueue = new LinkedList<>();
        readBuffer = ByteBuffer.allocate(4096);
        reading = false;

        socket.onMessage(event -> {
            isConnected = true;
            messageQueue.offer(event);
        });

        socket.onOpen(event -> {
            isConnected = true;
            messageQueue.clear();
        });

        socket.onClose((event) -> {
            isConnected = false;
            messageQueue.clear();
        });

        socket.onError((event) -> {
            isConnected = false;
            socket.close();
        });
    }

    public void read(ByteBuffer buffer) {
        if (reading || messageQueue.isEmpty()) {
            return;
        }
        MessageEvent event = messageQueue.peek();
        readData(buffer, event);
        if (!event.isBubbles() && !event.isCancelable()) {
            messageQueue.poll();
        }
    }

    private void readData(ByteBuffer buffer, MessageEvent event) {
        DataView data = DataView.create(event.getDataAsArray());
        int length = Math.min(buffer.remaining(), data.getByteLength());
        for (int i = 0; i < length; i++) {
            buffer.put((byte) data.getInt8(i));
        }
        if (reading) {
            return;
        }
        if (buffer.hasRemaining()) {
            readBuffer.clear();
            readBuffer.put(buffer);
            reading = true;
        } else {
            buffer.flip();
        }
    }
    
    @JSBody(params = { "sock", "buffer" }, script = "sock.send(buffer);")
	private static native void nativeBinarySend(WebSocket sock, ArrayBuffer buffer);

    public void write(ByteBuffer buffer) {
        if (socket.getReadyState() == 3) {
            return;
        }
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        Int8Array array = Int8Array.create(data.length);
        for (int i = 0; i < data.length; i++) {
            array.set(i, data[i]);
        }
        nativeBinarySend(socket, array.getBuffer());
    }

    public boolean connectionOpen() {
        return isConnected && socket.getReadyState() != 3;
    }

    public void endConnection() {
        socket.close();
    }
}
