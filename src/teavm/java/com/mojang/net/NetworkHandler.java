package com.mojang.net;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gamemode.CreativeGameMode;
import com.mojang.minecraft.gui.ErrorScreen;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.net.PacketType;

import net.PeytonPlayz585.websocket.WebSocketChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.teavm.jso.JSBody;

public final class NetworkHandler {

   public ByteBuffer in = ByteBuffer.allocate(1048576);
   public ByteBuffer out = ByteBuffer.allocate(1048576);
   public NetworkManager netManager;
   private boolean unused = false;
   private byte[] stringBytes = new byte[64];
   public WebSocketChannel channel;


   public NetworkHandler(String var1) {
	   Minecraft.getMinecraft().gamemode = new CreativeGameMode(Minecraft.getMinecraft());
	   String address = var1;
	   if(address.endsWith("/")) {
		   address = address.substring(0, address.length() - 1);
	   }
	   
	   if(address.startsWith("ws://") && isSSLPage()) {
		   address.replace("ws://", "wss://");
	   } else if(address.startsWith("wss://") && !isSSLPage()) {
		   address.replace("wss://", "ws://");
	   } else if(!address.contains("://")) {
		   if(isSSLPage()) {
			   address = "wss://" + address;
		   } else {
			   address = "ws://" + address;
		   }
	   } else if(address.contains("://") && !(address.contains("wss://") || address.contains("ws://"))) {
		   Minecraft.getMinecraft().setCurrentScreen(new ErrorScreen(":(", "Invalid URI protocol!"));
	   }
	   
	   channel = new WebSocketChannel(address);
   }

   public final void close() {
      try {
         if(this.out.position() > 0) {
            this.out.flip();
            this.channel.write(this.out);
            this.out.compact();
         }
      } catch (Exception var2) {
         ;
      }

      try {
    	  channel.endConnection();
      } catch (Exception var1) {
         ;
      }
   }

   public final void send(PacketType var1, Object ... var2) {
      if(channel.connectionOpen()) {
         this.out.put(var1.opcode);

         for(int var3 = 0; var3 < var2.length; ++var3) {
            Class<?> var10001 = var1.params[var3];
            Object var4 = var2[var3];
            Class<?> var5 = var10001;
            NetworkHandler var6 = this;
            if(channel.connectionOpen()) {
               try {
                  if(var5 == Long.TYPE) {
                     var6.out.putLong(((Long)var4).longValue());
                  } else if(var5 == Integer.TYPE) {
                     var6.out.putInt(((Number)var4).intValue());
                  } else if(var5 == Short.TYPE) {
                     var6.out.putShort(((Number)var4).shortValue());
                  } else if(var5 == Byte.TYPE) {
                     var6.out.put(((Number)var4).byteValue());
                  } else if(var5 == Double.TYPE) {
                     var6.out.putDouble(((Double)var4).doubleValue());
                  } else if(var5 == Float.TYPE) {
                     var6.out.putFloat(((Float)var4).floatValue());
                  } else {
                     byte[] var9;
                     if(var5 != String.class) {
                        if(var5 == byte[].class) {
                           if((var9 = (byte[])((byte[])var4)).length < 1024) {
                              var9 = Arrays.copyOf(var9, 1024);
                           }

                           var6.out.put(var9);
                        }
                     } else {
                    	if(var4 != null) {
                    		var9 = ((String)var4).getBytes("UTF-8");
                    		Arrays.fill(var6.stringBytes, (byte)32);

                    		int var8;
                    		for(var8 = 0; var8 < 64 && var8 < var9.length; ++var8) {
                    			var6.stringBytes[var8] = var9[var8];
                    		}

                    		for(var8 = var9.length; var8 < 64; ++var8) {
                    			var6.stringBytes[var8] = 32;
                    		}

                        	var6.out.put(var6.stringBytes);
                    	}
                     }
                  }
               } catch (Exception var7) {
                  this.netManager.error(var7);
               }
            }
         }

      }
   }

   public Object readObject(Class var1) {
      if(!channel.connectionOpen()) {
         return null;
      } else {
         try {
            if(var1 == Long.TYPE) {
               return Long.valueOf(this.in.getLong());
            } else if(var1 == Integer.TYPE) {
               return Integer.valueOf(this.in.getInt());
            } else if(var1 == Short.TYPE) {
               return Short.valueOf(this.in.getShort());
            } else if(var1 == Byte.TYPE) {
               return Byte.valueOf(this.in.get());
            } else if(var1 == Double.TYPE) {
               return Double.valueOf(this.in.getDouble());
            } else if(var1 == Float.TYPE) {
               return Float.valueOf(this.in.getFloat());
            } else if(var1 == String.class) {
               this.in.get(this.stringBytes);
               return (new String(this.stringBytes, "UTF-8")).trim();
            } else if(var1 == byte[].class) {
               byte[] var3 = new byte[1024];
               this.in.get(var3);
               return var3;
            } else {
               return null;
            }
         } catch (Exception var2) {
            this.netManager.error(var2);
            return null;
         }
      }
   }
   
   @JSBody(params = { }, script = "return window.location.href;")
   private static native String getLocationString();
	
   public static final boolean isSSLPage() {
	   return getLocationString().startsWith("https");
   }
}
