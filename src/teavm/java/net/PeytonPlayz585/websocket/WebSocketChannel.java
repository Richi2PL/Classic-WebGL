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
    private boolean isConnected = false;
    private Queue<ByteBuffer> bufferQueue;

    public WebSocketChannel(String url) {
        socket = WebSocket.create(url);
        bufferQueue = new LinkedList<ByteBuffer>();

        socket.onMessage(event -> {
            ArrayBuffer arrayBuffer = event.getData().cast();
            Int8Array int8Array = Int8Array.create(arrayBuffer);
            ByteBuffer byteBuffer = ByteBuffer.allocate(int8Array.getLength());
            for (int i = 0; i < int8Array.getLength(); i++) {
                byteBuffer.put(i, (byte) int8Array.get(i));
            }
            bufferQueue.offer(byteBuffer);
        });

        socket.onOpen(event -> {
            isConnected = true;
            bufferQueue.clear();
        });

        socket.onClose((event) -> {
            isConnected = false;
            bufferQueue.clear();
        });

        socket.onError((event) -> {
            isConnected = false;
            socket.close();
        });
    }

    public int read(ByteBuffer destination) {
    	int totalBytesWritten = 0;
        
        while (!bufferQueue.isEmpty()) {
            ByteBuffer sourceBuffer = bufferQueue.poll();
            int remaining = sourceBuffer.remaining();
            int spaceRemaining = destination.remaining();
            
            if (spaceRemaining < remaining) {
                break;
            }
            
            destination.put(sourceBuffer);
            totalBytesWritten += remaining;
        }
        
        return totalBytesWritten;
    }
    
    @JSBody(params = { "sock", "buffer" }, script = "sock.send(buffer);")
	private static native void nativeBinarySend(WebSocket sock, ArrayBuffer buffer);
 
    public int write(ByteBuffer src) {
        int bytesWritten = src.remaining();
 
        if (bytesWritten > 0) {
            byte[] newByteArray = new byte[bytesWritten];
            src.get(newByteArray);
            Int8Array array = Int8Array.create(newByteArray.length);
            for (int i = 0; i < newByteArray.length; i++) {
                array.set(i, newByteArray[i]);
            }
            nativeBinarySend(socket, array.getBuffer());
        }
 
        return bytesWritten;
    }

    public boolean connectionOpen() {
        return isConnected && socket.getReadyState() != 3;
    }

    public void endConnection() {
        socket.close();
    }
}
